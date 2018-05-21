package cn.edu.bupt.sice.vo;

import lombok.Data;

@Data
public class RuleVO implements Comparable<RuleVO>{

    private String ruleName;
    private int num;
    @Override
    public int compareTo(RuleVO ruleVO) {
        if (this.num > ruleVO.getNum()) {
            return 1;
        } else if (this.num == ruleVO.getNum()) {
            return 0;
        } else {
            return -1;
        }
    }

}