package cn.edu.bupt.sice.vo;

import lombok.Data;

import java.util.List;

@Data
public class TaskDetailVO {
    private long taskId;
    private String taskName;
    private int checkTool;
    private int high;
    private int mid;
    private int low;
    private int classNum;
    private int packageNum;
    private int lineNum;
    private List<RuleVO> ruleVOList;
}