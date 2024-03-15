package com.example.ticketsystem.file.service;

import com.example.ticketsystem.file.config.MinioConfig;
import io.minio.*;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class MinioService {

    @SneakyThrows
    public String upload(MultipartFile file) {
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://minio-api.fucota.com")
                .credentials("JmrxfenmEU6iQ1tpAoCe", "x03xRLMVoV2H0zz2EhzMC2BN6ZoWVvbMTPEHpUVA")
                .build();

        // Kiểm tra nếu bucket không tồn tại, tạo mới
        log.info("buket check begin");
        boolean checkBuket = minioClient.bucketExists(BucketExistsArgs.builder()
                        .bucket("ticketsystem1")
                .build());
        log.info("buket {} existed: {}", "ticket", checkBuket);
        if(!checkBuket) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket("ticketsystem1")
                    .build());
        }
        // Upload file
        ObjectWriteResponse response = minioClient.putObject(PutObjectArgs.builder()
                        .bucket("ticketsystem1")
                        .object(file.getOriginalFilename())
                        .stream(file.getInputStream(), file.getSize(),26214400)
                        .contentType(file.getContentType())
                .build());
        log.info("Response minio {}", response.toString());
        return file.getOriginalFilename();
    }

}
