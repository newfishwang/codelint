package cn.edu.bupt.sice.service.impl;

import cn.edu.bupt.sice.service.IReportService;
import cn.edu.bupt.sice.util.FileUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ReportService implements IReportService {
    private static final String prefix = "E:/open/results/";
    @Override
    public void download(HttpServletResponse response, String fileName) throws Exception {
        File file = new File(prefix);
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
        FileUtil.downloadFile(filePath,response);
    }
}