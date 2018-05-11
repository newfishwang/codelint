package cn.edu.bupt.sice.vo;

import lombok.Data;

import java.util.List;

@Data
public class CompareDetailVO {
    private long taskId;
    private String taskName;
    private int highF;
    private int highP;
    private int midF;
    private int midP;
    private int lowF;
    private int lowP;
    private int totalF;
    private int totalP;
    private List<RuleVO> ruleVOListF;
    private List<RuleVO> ruleVOListP;
}