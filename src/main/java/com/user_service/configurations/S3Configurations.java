package com.user_service.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws.s3")
@Data
public class S3Configurations {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
