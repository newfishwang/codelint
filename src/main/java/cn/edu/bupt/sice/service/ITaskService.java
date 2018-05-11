package cn.edu.bupt.sice.service;

import cn.edu.bupt.sice.vo.TaskDetailVO;
import cn.edu.bupt.sice.vo.TaskVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITaskService {
    void handleUpload(MultipartFile file) throws Exception;
    List<TaskVO> getTaskList() throws Exception;
    TaskDetailVO getTaskDetail(long taskId) throws Exception;
    TaskVO queryTask(long taskId) throws Exception;
}