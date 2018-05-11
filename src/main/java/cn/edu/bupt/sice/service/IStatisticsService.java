package cn.edu.bupt.sice.service;

import cn.edu.bupt.sice.vo.StatisticsExVO;
import cn.edu.bupt.sice.vo.StatisticsVO;

import java.util.List;

public interface IStatisticsService {
    void insertStatistics(StatisticsVO statisticsVO) throws Exception;
    StatisticsVO queryStatistics(long statisticsId) throws Exception;
    void insertIntoEx(List<StatisticsExVO> statisticsExVOList) throws Exception;

}