package cn.edu.bupt.sice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "multipart")
@Data
public class FileUploadConfig {
    private String maxFileSize;
    private String maxRequestSize;
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大
        factory.setMaxFileSize(maxFileSize);
        // 设置总上传数据总大小
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }
}