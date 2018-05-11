package cn.edu.bupt.sice.mapper;

import cn.edu.bupt.sice.vo.StatisticsExVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStatisticsExMapper {
    void insertIntoEx(List<StatisticsExVO> statisticsExVOList);
    List<StatisticsExVO> queryEx(long statisticsId);
}