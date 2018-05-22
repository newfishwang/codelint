package cn.edu.bupt.sice.web;

import cn.edu.bupt.sice.service.ICompareTaskService;
import cn.edu.bupt.sice.vo.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/compare")
public class CompareTaskController {
    @Autowired
    private ICompareTaskService compareTaskService;

    @RequestMapping("/upload")
    public String uploadCompare() {
        return "uploadCompare";
    }

    @RequestMapping("/handleUpload")
    public String handleUploadCompare(@RequestParam("file") MultipartFile file,@RequestParam("name") String name, Model model) {
        List<CompareTaskListVO> compareTaskVOs;
        try {
            compareTaskService.handleUploadCompare(file,name);
            compareTaskVOs = compareTaskService.getCompareTaskList();
            model.addAttribute("compare",compareTaskVOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "compareTask";
    }
    @RequestMapping("/list")
    public String getCompareList(Model model) {
        List<CompareTaskListVO> compareTaskVOs;
        try {
            compareTaskVOs = compareTaskService.getCompareTaskList();
            model.addAttribute("compare",compareTaskVOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "compareTask";
    }
    @GetMapping("/detail")
    @ResponseBody
    public CompareDetailVO getCompareDetail(@RequestParam("taskId") long taskId) {
        CompareDetailVO compareDetailVO = null;
        try {
            compareDetailVO = compareTaskService.getCompareDetail(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compareDetailVO;
    }

    @GetMapping("/direct")
    public String directTo(@RequestParam("taskId") long taskId,Model model) {
        model.addAttribute("taskId",taskId);
        CompareDetailVO compareDetailVO = null;
        try {
            compareDetailVO = compareTaskService.getCompareDetail(taskId);
            model.addAttribute("detail",compareDetailVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "compareDetail";
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam("taskId") long taskId,Model model) {
        List<CompareTaskListVO> taskVOs;
        try {
            compareTaskService.deleteCompare(taskId);
            taskVOs = compareTaskService.getCompareTaskList();
            model.addAttribute("compare",taskVOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "compareTask";
    }

}