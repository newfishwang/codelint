package cn.edu.bupt.sice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "codelint")
@Data
public class CodelintConfig {
    private String reportPath;
    private String zipPath;
    private String findbugsPath;
    private String pmdPath;
    private String rulePath;
}