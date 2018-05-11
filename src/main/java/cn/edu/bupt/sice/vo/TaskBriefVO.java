package cn.edu.bupt.sice.vo;

import lombok.Data;

@Data
public class TaskBriefVO {
    private long taskId;
    private String taskName;
    private int tool;
    private int bugNum;
}