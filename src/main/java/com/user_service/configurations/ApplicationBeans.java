package com.user_service.configurations;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Component
@AllArgsConstructor
public class ApplicationBeans {

    S3Configurations s3Configurations;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .endpointOverride(URI.create(s3Configurations.getEndpoint()))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(s3Configurations.getAccessKey(), s3Configurations.getSecretKey())))
                .region(Region.US_EAST_1)  // Use any region, MinIO ignores it
                .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
                .build();
    }
}
