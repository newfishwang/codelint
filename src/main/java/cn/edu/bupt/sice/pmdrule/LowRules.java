package cn.edu.bupt.sice.pmdrule;

import java.util.HashMap;
import java.util.Map;

public class LowRules {
    private static Map<String,Integer> lowRulesMap;
    private static Integer low = 1;
    private static volatile LowRules lowRules;
    private LowRules(){

    }
    public static LowRules getInstance() {
        if (lowRules == null) {
            synchronized (LowRules.class) {
                if (lowRules == null) {
                    lowRules = new LowRules();
                    lowRulesMap = new HashMap<>();
                    lowRulesMap.put("InstantiationToGetClass",low);
                    lowRulesMap.put("InvalidSlf4jMessageFormat",low);
                    lowRulesMap.put("DontCallThreadRun",low);
                }
            }
        }
        return lowRules;
    }
    public Map<String,Integer> getLowRulesMap() {
        return lowRulesMap;
    }
}