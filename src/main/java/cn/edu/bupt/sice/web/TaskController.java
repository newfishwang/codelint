package cn.edu.bupt.sice.web;


import cn.edu.bupt.sice.service.ITaskService;
import cn.edu.bupt.sice.vo.TaskDetailVO;
import cn.edu.bupt.sice.vo.TaskListVO;
import cn.edu.bupt.sice.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String handleFile(@RequestParam("file") MultipartFile file,@RequestParam("name") String name,@RequestParam("tool") String tool,Model model) {
        List<TaskListVO> taskVOs;
        try {
            taskService.handleUpload(file,name,Integer.valueOf(tool));
            taskVOs = taskService.getTaskList();
            model.addAttribute("tasks",taskVOs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "task";
    }
    @GetMapping("/list")
    public String getTaskList(Model model) {
        List<TaskListVO> taskVOs;
        try  {
           taskVOs = taskService.getTaskList();
           model.addAttribute("tasks",taskVOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "task";
    }
    @GetMapping("/detail")
    @ResponseBody
    public TaskDetailVO getTaskDetail(@RequestParam("taskId") long taskId) {
        TaskDetailVO taskDetailVO = null;
        try {
            taskDetailVO = taskService.getTaskDetail(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskDetailVO;
    }
    @GetMapping("/direct")
    public String directTo(@RequestParam("taskId") long taskId,Model model) {
        model.addAttribute("taskId",taskId);
        TaskDetailVO taskDetailVO = null;
        try {
            taskDetailVO = taskService.getTaskDetail(taskId);
            model.addAttribute("detail",taskDetailVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "taskDetail";
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam("taskId") long taskId,Model model) {
        List<TaskListVO> taskVOs;
        try {
            taskService.deleteTask(taskId);
            taskVOs = taskService.getTaskList();
            model.addAttribute("tasks",taskVOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "task";
    }
}