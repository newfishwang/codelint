package cn.edu.bupt.sice.service;

import cn.edu.bupt.sice.po.CompareDetailPO;
import cn.edu.bupt.sice.vo.CompareDetailVO;
import cn.edu.bupt.sice.vo.CompareTaskListVO;
import cn.edu.bupt.sice.vo.CompareTaskVO;
import cn.edu.bupt.sice.vo.TaskDetailVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICompareTaskService {
    void handleUploadCompare(MultipartFile file,String name) throws Exception;
    CompareDetailVO getCompareDetail(long taskId) throws Exception;
    CompareTaskVO queryCompareTask(long taskId) throws Exception;
    List<CompareTaskListVO> getCompareTaskList() throws Exception;
    void deleteCompare(long taskId) throws Exception;
}