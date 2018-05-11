package cn.edu.bupt.sice.web;

import cn.edu.bupt.sice.service.ICompareTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/tasks")
public class CompareTaskController {
    @Autowired
    private ICompareTaskService compareTaskService;

    @RequestMapping("/uploadCompare")
    public String uploadCompare() {
        return "uploadCompare";
    }

    @RequestMapping("handleUploadCompare")
    public String handleUploadCompare(@RequestParam("file") MultipartFile file) {
        try {
            compareTaskService.handleUploadCompare(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "compareTask";
    }

}