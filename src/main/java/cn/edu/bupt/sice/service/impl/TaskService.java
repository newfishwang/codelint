package cn.edu.bupt.sice.service.impl;

import cn.edu.bupt.sice.execute.CheckService;
import cn.edu.bupt.sice.mapper.ITaskMapper;
import cn.edu.bupt.sice.po.TaskDetailPO;
import cn.edu.bupt.sice.service.ITaskService;
import cn.edu.bupt.sice.util.CheckTool;
import cn.edu.bupt.sice.util.FileUtil;
import cn.edu.bupt.sice.vo.RuleVO;
import cn.edu.bupt.sice.vo.TaskDetailVO;
import cn.edu.bupt.sice.vo.TaskVO;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Override
    public void handleUpload(MultipartFile file) throws Exception {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        TaskVO taskVO = new TaskVO();
        taskVO.setTaskName(fileName);
        taskVO.setCheckStatus(1);
        taskVO.setCheckTool(CheckTool.FINDBUGS.getToolCode());
        taskVO.setLaunchTime(new Date());
        taskVO.setResultPath("");
        taskVO.setZipPath(UUID.randomUUID().toString());
        taskMapper.insertIntoTask(taskVO);
        FileUtil.uploadFile(file.getBytes(),preFilePath,taskVO.getZipPath()+".zip");
        checkService.check(taskVO);
    }
    @Override
    public List<TaskVO> getTaskList() throws Exception {
        TaskVO taskVO = new TaskVO();
        taskVO.setTaskName("haha");
        taskVO.setCheckStatus(2);
        taskVO.setTaskId(1);
        List<TaskVO> taskVOList = new ArrayList<>();
        taskVOList.add(taskVO);
        return taskVOList;
    }
    @Override
    public TaskDetailVO getTaskDetail(long taskId) throws Exception {
        List<TaskDetailPO> taskDetailPOs= taskMapper.getTaskDetail(taskId);
        TaskDetailVO taskDetailVO = new TaskDetailVO();
        if (taskDetailPOs != null && taskDetailPOs.size() > 0) {
            taskDetailVO.setTaskId(taskId);
            taskDetailVO.setTaskName(taskDetailPOs.get(0).getTaskName());
            taskDetailVO.setCheckTool(taskDetailPOs.get(0).getCheckTool());
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

}