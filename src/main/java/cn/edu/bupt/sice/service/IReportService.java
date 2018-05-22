package cn.edu.bupt.sice.service;

import javax.servlet.http.HttpServletResponse;

public interface IReportService {
    void download(HttpServletResponse response,String fileName,String reportName) throws Exception;
}