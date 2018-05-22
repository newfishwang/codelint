package cn.edu.bupt.sice.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CompareTaskListVO {
    private long taskId;
    private String taskName;
    private int checkStatus;
    private String resultPathFindBugs;
    private String resultPathPMD;
    private String zipPath;
    private String launchTime;
    public CompareTaskListVO(CompareTaskVO compareTaskVO) {
        this.taskId = compareTaskVO.getTaskId();
        this.taskName = compareTaskVO.getTaskName();
        this.checkStatus = compareTaskVO.getCheckStatus();
        this.resultPathFindBugs = compareTaskVO.getResultPathFindBugs();
        this.resultPathPMD = compareTaskVO.getResultPathPMD();
        this.zipPath = compareTaskVO.getZipPath();
    }
}