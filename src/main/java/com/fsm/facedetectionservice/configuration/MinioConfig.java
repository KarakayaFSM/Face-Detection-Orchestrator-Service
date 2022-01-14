package com.fsm.facedetectionservice.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MinioConfig {

    @Value("${MINIO_ACCESS_NAME}")
    private String minioAccessName;

    @Value("${MINIO_ACCESS_SECRET}")
    private String minioAccessSecret;

    @Value("${MINIO_URL}")
    private String minioURL;
}
