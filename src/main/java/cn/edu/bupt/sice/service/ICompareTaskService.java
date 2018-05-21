package cn.edu.bupt.sice.service;

import cn.edu.bupt.sice.po.CompareDetailPO;
import cn.edu.bupt.sice.vo.CompareDetailVO;
import cn.edu.bupt.sice.vo.CompareTaskVO;
import cn.edu.bupt.sice.vo.TaskDetailVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICompareTaskService {
    void handleUploadCompare(MultipartFile file) throws Exception;
    CompareDetailVO getCompareDetail(long taskId) throws Exception;
    CompareTaskVO queryCompareTask(long taskId) throws Exception;
    List<CompareTaskVO> getCompareTaskList() throws Exception;
}