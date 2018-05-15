package cn.edu.bupt.sice.service.impl;

import cn.edu.bupt.sice.mapper.ICompareTaskMapper;
import cn.edu.bupt.sice.mapper.IStatisticsMapper;
import cn.edu.bupt.sice.mapper.ITaskMapper;
import cn.edu.bupt.sice.service.IHomeService;
import cn.edu.bupt.sice.util.CheckTool;
import cn.edu.bupt.sice.util.TaskType;
import cn.edu.bupt.sice.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService implements IHomeService {

    @Autowired
    private ICompareTaskMapper compareTaskMapper;
    @Autowired
    private ITaskMapper taskMapper;
    @Autowired
    private IStatisticsMapper statisticsMapper;
    @Override
    public CheckBriefVO getCheckBrief() throws Exception{
        List<TaskVO> lastTasks = taskMapper.queryLastTasks();
        List<CompareTaskVO> lastCompareTasks = compareTaskMapper.queryLastTasks();
        List<TaskBriefVO> taskBriefVOList = new ArrayList<>();
        List<CompareTaskBrief> compareTaskBriefList = new ArrayList<>();
        for (TaskVO taskVO : lastTasks) {
            StatisticsVO statisticsVO = statisticsMapper.queryStatistics(taskVO.getTaskId(), TaskType.CHECK_TASK.getCode()).get(0);
            TaskBriefVO taskBriefVO  = new TaskBriefVO();
            taskBriefVO.setTaskName(taskVO.getTaskName());
            taskBriefVO.setBugNum(statisticsVO.getTotalNum());
            taskBriefVO.setHighNum(statisticsVO.getHighNum());
            taskBriefVO.setMidNum(statisticsVO.getMidNum());
            taskBriefVO.setLowNum(statisticsVO.getLowNum());
            taskBriefVO.setTaskId(taskVO.getTaskId());
            taskBriefVO.setTool(taskVO.getCheckTool());
            taskBriefVOList.add(taskBriefVO);
        }
        for (CompareTaskVO compareTaskVO : lastCompareTasks) {
            List<StatisticsVO> statisticsVOs = statisticsMapper.queryStatistics(compareTaskVO.getTaskId(),TaskType.COMPARE_TASK.getCode());
            CompareTaskBrief compareTaskBrief = new CompareTaskBrief();
            compareTaskBrief.setTaskId(compareTaskVO.getTaskId());
            compareTaskBrief.setTaskName(compareTaskVO.getTaskName());
            for (StatisticsVO item : statisticsVOs) {
                if (item.getTool() == CheckTool.FINDBUGS.getToolCode()) {
                    compareTaskBrief.setBugNumF(item.getTotalNum());
                    compareTaskBrief.setHighNumF(item.getHighNum());
                    compareTaskBrief.setMidNumF(item.getMidNum());
                    compareTaskBrief.setLowNumF(item.getLowNum());
                }
                if (item.getTool() == CheckTool.PMD.getToolCode()) {
                    compareTaskBrief.setBugNumP(item.getTotalNum());
                    compareTaskBrief.setHighNumP(item.getHighNum());
                    compareTaskBrief.setMidNumP(item.getMidNum());
                    compareTaskBrief.setLowNumP(item.getLowNum());
                }
            }
            compareTaskBriefList.add(compareTaskBrief);
        }
        CheckBriefVO checkBriefVO = new CheckBriefVO();
        checkBriefVO.setCompareTaskBriefList(compareTaskBriefList);
        checkBriefVO.setTaskBriefVOList(taskBriefVOList);
        int compareNum = compareTaskMapper.getAllCompareNum();
        int taskNum = taskMapper.getAllTaskNum();
        checkBriefVO.setTaskNum(compareNum+taskNum);
        return checkBriefVO;
    }
}