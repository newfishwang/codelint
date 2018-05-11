package cn.edu.bupt.sice.pmdrule;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

public class HighRules {
    private static Map<String,Integer> highRulesMap;
    private static Integer high = 4;
    private static volatile HighRules highRules;
    private HighRules() {

    }
    public static HighRules getInstance() {
        if (highRules == null) {
            synchronized (HighRules.class) {
                if (highRules == null) {
                    highRules = new HighRules();
                    highRulesMap = new HashMap<>();
                    highRulesMap.put("SystemPrintln",high);
                    highRulesMap.put("AvoidUsingNativeCode",high);
                    highRulesMap.put("AbstractClassWithoutAnyMethod",high);
                    highRulesMap.put("AvoidThrowingNullPointerException",high);
                    highRulesMap.put("AvoidThrowingRawExceptionTypes",high);
                    highRulesMap.put("ClassWithOnlyPrivateConstructorsShouldBeFinal",high);
                    highRulesMap.put("AvoidBranchingStatementAsLastInLoop",high);
                    highRulesMap.put("AvoidEnumAsIdentifier",high);
                    highRulesMap.put("AvoidLosingExceptionInformation",high);
                    highRulesMap.put("AvoidMultipleUnaryOperators",high);
                    highRulesMap.put("BrokenNullCheck",high);
                    highRulesMap.put("ConstructorCallsOverridableMethod",high);
                    highRulesMap.put("DoNotCallGarbageCollectionExplicitly",high);
                    highRulesMap.put("EqualsNull",high);
                    highRulesMap.put("LoggerIsNotStaticFinal",high);
                    highRulesMap.put("MoreThanOneLogger",high);
                    highRulesMap.put("ProperCloneImplementation",high);
                    highRulesMap.put("SingleMethodSingleton",high);
                    highRulesMap.put("SingletonClassReturningNewInstance",high);
                    highRulesMap.put("SuspiciousEqualsMethodName",high);
                    highRulesMap.put("DoubleCheckedLocking",high);
                    highRulesMap.put("AvoidFileStream",high);
                    highRulesMap.put("AvoidUsingShortType",high);
                    highRulesMap.put("BooleanInstantiation",high);
                    highRulesMap.put("ByteInstantiation",high);
                    highRulesMap.put("IntegerInstantiation",high);
                    highRulesMap.put("LongInstantiation",high);
                    highRulesMap.put("ShortInstantiation",high);
                    highRulesMap.put("StringInstantiation",high);
                }
            }
        }
        return highRules;

    }
    public Map<String,Integer> getHighRules() {
        return highRulesMap;
    }
}