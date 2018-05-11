package cn.edu.bupt.sice.vo;

import lombok.Data;

@Data
public class StatisticsVO {
    private long statisticsId;
    private long statisticsTaskId;
    private long lineNum;
    private int classNum;
    private int packageNum;
    private int highNum;
    private Double highDensity;
    private int midNum;
    private Double midDensity;
    private int lowNum;
    private Double lowDensity;
    private int totalNum;
    private Double totalDensity;
    private int taskType;
    private int tool;
}