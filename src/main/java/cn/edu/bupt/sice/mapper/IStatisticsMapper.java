package cn.edu.bupt.sice.mapper;

import cn.edu.bupt.sice.vo.StatisticsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IStatisticsMapper {
    void insertStatistics(StatisticsVO statisticsVO);
    List<StatisticsVO> queryStatistics(@Param("taskId") long taskId, @Param("taskType") int taskType);
}