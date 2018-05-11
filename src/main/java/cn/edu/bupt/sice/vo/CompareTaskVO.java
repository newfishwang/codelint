package cn.edu.bupt.sice.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CompareTaskVO {
    private long taskId;
    private String taskName;
    private int checkStatus;
    private String resultPathFindBugs;
    private String resultPathPMD;
    private String zipPath;
    private Date launchTime;

}