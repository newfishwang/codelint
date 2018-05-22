package cn.edu.bupt.sice.vo;

import lombok.Data;

@Data
public class TaskListVO {
    private long taskId;
    private String taskName;
    private int checkStatus;
    private String resultPath;
    private String zipPath;
    private String launchTime;
    private int checkTool;
    public TaskListVO(TaskVO taskVO) {
        this.taskId = taskVO.getTaskId();
        this.taskName = taskVO.getTaskName();
        this.checkStatus = taskVO.getCheckStatus();
        this.resultPath = taskVO.getResultPath();
        this.zipPath = taskVO.getZipPath();
        this.checkTool = taskVO.getCheckTool();
    }
}