package cn.edu.bupt.sice.po;

import lombok.Data;

@Data
public class CompareDetailPO {
    private long taskId;
    private String taskName;
    private int high;
    private int low;
    private int mid;
    private int total;
    private String ruleName;
    private int num;
}