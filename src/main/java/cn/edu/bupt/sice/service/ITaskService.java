package cn.edu.bupt.sice.service;

import cn.edu.bupt.sice.vo.TaskDetailVO;
import cn.edu.bupt.sice.vo.TaskListVO;
import cn.edu.bupt.sice.vo.TaskVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITaskService {
    void handleUpload(MultipartFile file,String name,int tool) throws Exception;
    List<TaskListVO> getTaskList() throws Exception;
    TaskDetailVO getTaskDetail(long taskId) throws Exception;
    TaskVO queryTask(long taskId) throws Exception;
    void deleteTask(long taskId) throws Exception;
}