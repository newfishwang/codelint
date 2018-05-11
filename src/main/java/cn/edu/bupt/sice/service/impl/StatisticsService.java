package cn.edu.bupt.sice.service.impl;

import cn.edu.bupt.sice.mapper.IStatisticsExMapper;
import cn.edu.bupt.sice.mapper.IStatisticsMapper;
import cn.edu.bupt.sice.service.IStatisticsService;
import cn.edu.bupt.sice.vo.StatisticsExVO;
import cn.edu.bupt.sice.vo.StatisticsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService implements IStatisticsService {

    @Autowired
    private IStatisticsMapper statisticsMapper;
    @Autowired
    private IStatisticsExMapper statisticsExMapper;
    @Override
    public void insertStatistics(StatisticsVO statisticsVO) throws Exception {
        statisticsMapper.insertStatistics(statisticsVO);
    }
    @Override
    public StatisticsVO queryStatistics(long statisticsId) throws Exception {
        return new StatisticsVO();
    }
    @Override
    public void insertIntoEx(List<StatisticsExVO> statisticsExVOList) throws Exception{
        statisticsExMapper.insertIntoEx(statisticsExVOList);
    }
}