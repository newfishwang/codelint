package cn.edu.bupt.sice.web;

import cn.edu.bupt.sice.service.ICompareTaskService;
import cn.edu.bupt.sice.service.IReportService;
import cn.edu.bupt.sice.service.ITaskService;
import cn.edu.bupt.sice.service.impl.CompareTaskService;
import cn.edu.bupt.sice.service.impl.TaskService;
import cn.edu.bupt.sice.util.CheckTool;
import cn.edu.bupt.sice.util.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ICompareTaskService compareTaskService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IReportService reportService;
    @RequestMapping("/download")
    public String downloadReport(HttpServletResponse response, @RequestParam("taskId") long taskId,@RequestParam("taskType") int taskType,@RequestParam("tool") int tool) {
        String reportPath = null;
        String reportName = "";
        try {
            if (taskType == TaskType.CHECK_TASK.getCode()) {
                reportPath = taskService.queryTask(taskId).getResultPath();
                reportName = taskService.queryTask(taskId).getTaskName() + ".html";
            } else {
                if (tool == CheckTool.FINDBUGS.getToolCode()) {
                    reportPath = compareTaskService.queryCompareTask(taskId).getResultPathFindBugs();
                    reportName = compareTaskService.queryCompareTask(taskId).getTaskName() + "-findbugs.html";
                } else {
                    reportPath = compareTaskService.queryCompareTask(taskId).getResultPathPMD();
                    reportName = compareTaskService.queryCompareTask(taskId).getTaskName() + "-pmd.html";
                }
            }
            reportService.download(response,reportPath,reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "report";
    }
}