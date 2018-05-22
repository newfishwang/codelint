package cn.edu.bupt.sice.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+"/"+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
    public static void downloadFile(String outputFile,HttpServletResponse response,String reportName) throws IOException {
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + reportName);// 设置文件名
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(outputFile));
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}