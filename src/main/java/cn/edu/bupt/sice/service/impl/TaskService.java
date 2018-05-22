package cn.edu.bupt.sice.service.impl;

import cn.edu.bupt.sice.config.CodelintConfig;
import cn.edu.bupt.sice.execute.CheckService;
import cn.edu.bupt.sice.mapper.ITaskMapper;
import cn.edu.bupt.sice.po.TaskDetailPO;
import cn.edu.bupt.sice.service.ITaskService;
import cn.edu.bupt.sice.util.CheckTool;
import cn.edu.bupt.sice.util.FileUtil;
import cn.edu.bupt.sice.vo.RuleVO;
import cn.edu.bupt.sice.vo.TaskDetailVO;
import cn.edu.bupt.sice.vo.TaskListVO;
import cn.edu.bupt.sice.vo.TaskVO;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class TaskService implements ITaskService{
    private static final String preFilePath = "../zip";
    @Autowired
    private ITaskMapper taskMapper;
    @Autowired
    private CheckService checkService;
    @Autowired
    private CodelintConfig codelintConfig;
    @Override
    public void handleUpload(MultipartFile file,String name,int tool) throws Exception {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        TaskVO taskVO = new TaskVO();
        taskVO.setTaskName(name);
        taskVO.setCheckStatus(1);
        taskVO.setCheckTool(tool);
        taskVO.setLaunchTime(new Date());
        taskVO.setResultPath("");
        taskVO.setZipPath(UUID.randomUUID().toString());
        taskMapper.insertIntoTask(taskVO);
        FileUtil.uploadFile(file.getBytes(),codelintConfig.getZipPath(),taskVO.getZipPath()+".zip");
        checkService.check(taskVO);
    }
    @Override
    public List<TaskListVO> getTaskList() throws Exception {
        List<TaskVO> taskVOList = taskMapper.queryAllTasks();
        List<TaskListVO> taskListVOList = new ArrayList<>();
        if (taskVOList != null && taskVOList.size() > 0) {
            for (TaskVO taskVO : taskVOList) {
                TaskListVO taskListVO = new TaskListVO(taskVO);
                taskListVO.setLaunchTime(dateFormatToSecond(taskVO.getLaunchTime()));
                taskListVOList.add(taskListVO);
            }
        }
        return taskListVOList;
    }
    @Override
    public TaskDetailVO getTaskDetail(long taskId) throws Exception {
        List<TaskDetailPO> taskDetailPOs= taskMapper.getTaskDetail(taskId);
        TaskDetailVO taskDetailVO = new TaskDetailVO();
        if (taskDetailPOs != null && taskDetailPOs.size() > 0) {
            taskDetailVO.setTaskId(taskId);
            taskDetailVO.setTaskName(taskDetailPOs.get(0).getTaskName());
            taskDetailVO.setCheckTool(taskDetailPOs.get(0).getCheckTool());
            if (taskDetailVO.getCheckTool() == CheckTool.FINDBUGS.getToolCode()) {
                taskDetailVO.setClassNum(taskDetailPOs.get(0).getClassNum());
                taskDetailVO.setPackageNum(taskDetailPOs.get(0).getPackageNum());
                taskDetailVO.setLineNum(taskDetailPOs.get(0).getLineNum());
            }
            taskDetailVO.setHigh(taskDetailPOs.get(0).getHigh());
            taskDetailVO.setMid(taskDetailPOs.get(0).getMid());
            taskDetailVO.setLow(taskDetailPOs.get(0).getLow());
        }
        List<RuleVO> ruleVOList = new ArrayList<>();
        for (TaskDetailPO taskDetailPO : taskDetailPOs) {
            RuleVO ruleVO = new RuleVO();
            ruleVO.setRuleName(taskDetailPO.getRuleName());
            ruleVO.setNum(taskDetailPO.getNum());
            ruleVOList.add(ruleVO);
        }
        taskDetailVO.setRuleVOList(ruleVOList);
        return taskDetailVO;
    }
    @Override
    public TaskVO queryTask(long taskId) throws Exception {
        return taskMapper.getTask(taskId);
    }

    @Override
    public void deleteTask(long taskId) throws Exception {
        TaskVO taskVO = taskMapper.getTask(taskId);
        taskMapper.deleteTask(taskId);
        // TODO: 2018/5/21 级联删除报告文件
    }
    private String dateFormatToSecond(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

}