package cn.edu.bupt.sice.mapper;

import cn.edu.bupt.sice.po.TaskDetailPO;
import cn.edu.bupt.sice.vo.TaskDetailVO;
import cn.edu.bupt.sice.vo.TaskVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ITaskMapper {
    void insertIntoTask(TaskVO taskVO) throws Exception;
    void updateTask(TaskVO taskVO) throws Exception;
    List<TaskDetailPO> getTaskDetail(long taskId) throws Exception;
    List<TaskVO> queryLastTasks();
    int getAllTaskNum();
    TaskVO getTask(long taskId);
    List<TaskVO> queryAllTasks();
    void deleteTask(long taskId);
}