package cn.edu.bupt.sice.vo;

import lombok.Data;

import java.util.Date;

@Data
public class TaskVO {
    private long taskId;
    private String taskName;
    private int checkStatus;
    private String resultPath;
    private String zipPath;
    private Date launchTime;
    private int checkTool;

}