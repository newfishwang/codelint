package cn.edu.bupt.sice.vo;

import lombok.Data;

@Data
public class CompareTaskBrief {
    private long taskId;
    private String taskName;
    private int bugNumP;
    private int bugNumF;
}