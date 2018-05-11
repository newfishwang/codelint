package cn.edu.bupt.sice.mapper;

import cn.edu.bupt.sice.po.CompareDetailPO;
import cn.edu.bupt.sice.vo.CompareTaskVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ICompareTaskMapper {
    void insertIntoTask(CompareTaskVO compareTaskVO) throws Exception;
    void updateTask(CompareTaskVO compareTaskVO) throws Exception;
    List<CompareDetailPO> getCompareDetail(@Param("taskId") long taskId, @Param("tool") int tool);
    List<CompareDetailPO> getCompareToolInfo(int tool);
    List<CompareTaskVO> queryLastTasks();
    int getAllCompareNum();
    CompareTaskVO queryCompareTask(long taskId);
}