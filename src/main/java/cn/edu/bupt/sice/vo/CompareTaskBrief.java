package cn.edu.bupt.sice.vo;

import lombok.Data;

@Data
public class CompareTaskBrief {
    private long taskId;
    private String taskName;
    private int bugNumP;
    private int highNumP;
    private int midNumP;
    private int lowNumP;
    private int bugNumF;
    private int highNumF;
    private int midNumF;
    private int lowNumF;
}