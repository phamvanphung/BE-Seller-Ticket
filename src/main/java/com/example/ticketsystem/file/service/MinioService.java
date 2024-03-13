package com.example.ticketsystem.file.service;

import com.example.ticketsystem.file.config.MinioConfig;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
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

    @Autowired
    private MinioConfig minioConfig;


    @SneakyThrows
    public String upload(MultipartFile file) {
        minioConfig.getMinioClient().uploadObject(
                UploadObjectArgs.builder()
                        .bucket("my-bucketname")
                        .object("my-objectname")
                        .filename(file.getOriginalFilename())

                        .build());
    }

}
