package com.sunzq.config;

import com.sunzq.utils.MinioUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio配置类
 * @author sunzq
 */
@Configuration
@Data
public class MinioConfig {

    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.fileHost}")
    private String fileHost;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.imgSize}")
    private Integer imgSize;
    @Value("${minio.fileSize}")
    private Integer fileSize;

    @Bean
    public MinioUtils creatMinioClient() {
        return new MinioUtils(endpoint, bucketName, accessKey, secretKey, imgSize, fileSize);
    }
}
