package cn.edu.bupt.sice.web;


import cn.edu.bupt.sice.service.ITaskService;
import cn.edu.bupt.sice.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    ITaskService taskService;
    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }
    @PostMapping("/handleUpload")
    @ResponseBody
    public String handleFile(@RequestParam("file") MultipartFile file) {
        try {
            taskService.handleUpload(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "task";
    }
    @GetMapping("/list")
    public String getTaskList(Map<String,Object> map) {
        List<TaskVO> taskVOs;
        try  {
           taskVOs = taskService.getTaskList();
           map.put("tasks",taskVOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "task";
    }
}