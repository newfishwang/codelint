package cn.edu.bupt.sice.vo;

import lombok.Data;

import java.util.List;

@Data
public class CheckBriefVO {
    private int taskNum;
    private List<CompareTaskBrief> compareTaskBriefList;
    private List<TaskBriefVO> taskBriefVOList;
}