package com.example.ticketsystem.file.config;


import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@Getter
@Setter
public class MinioConfig {
    public  static MinioClient minioClient;

    @Bean(name = "config-minio")
    public String config(){
        try {
            minioClient =
                    MinioClient.builder()
                            .endpoint("157.10.52.152:9000")
                            .credentials("JmrxfenmEU6iQ1tpAoCe", "x03xRLMVoV2H0zz2EhzMC2BN6ZoWVvbMTPEHpUVA")
                            .build();
            log.info("config minio success");
        } catch (Exception e) {
            log.error("Connect minio failed.");
        }
        return "";

    }
}
