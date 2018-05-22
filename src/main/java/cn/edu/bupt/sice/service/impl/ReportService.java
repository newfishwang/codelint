package cn.edu.bupt.sice.service.impl;

import cn.edu.bupt.sice.config.CodelintConfig;
import cn.edu.bupt.sice.service.IReportService;
import cn.edu.bupt.sice.util.FileUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ReportService implements IReportService {
    @Autowired
    private CodelintConfig codelintConfig;
    private static final String prefix = "E:/open/results/";
    @Override
    public void download(HttpServletResponse response, String fileName,String reportName) throws Exception {
        File file = new File(codelintConfig.getReportPath());
        if (!file.exists()) {
            boolean ismake = file.mkdirs();
            if (!ismake) {
                throw new IOException("创建rtfDir失败,请检查目录");
            }
        }
        String filePath = file.getCanonicalPath() +"/"+ fileName + ".html";
        File reportFile = new File(filePath);
        /*if (!Files.isSameFile(reportFile.getParentFile().toPath(), reportFile.toPath())) {
            throw new IOException("报告路径异常");
        }*/
        FileUtil.downloadFile(filePath,response,reportName);
    }
}