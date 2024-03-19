package com.example.ticketsystem.file.service;


import com.example.ticketsystem.enums.ResponseCode;
import com.example.ticketsystem.exception.BusinessException;
import com.example.ticketsystem.file.dto.common.FileDto;
import com.example.ticketsystem.file.dto.common.MimeTypes;
import com.example.ticketsystem.file.dto.response.FileResponse;
import io.minio.*;
import io.minio.errors.MinioException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {


    @Value("${minio.host}")
    private String host;

    @Value("${minio.access_key}")
    private String accessKey;

    @Value("${minio.secret_key}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucket;

    @Value("${minio.partsize}")
    private Long partsize;

    private static MinioClient minioClient;


    @PostConstruct
    private String config (){
        try {
            log.info("Config minion start");
            minioClient = MinioClient.builder()
                    .endpoint(host)
                    .credentials(accessKey, secretKey)
                    .build();


            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            } else {
                log.info("Buket {} existed", bucket);
            }
            log.info("Config Minio success");
        } catch (MinioException e ) {
            log.error("Error occurred: " + e);
            log.error("HTTP trace: " + e.httpTrace());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return "";
    }




    public FileResponse upload (MultipartFile file) {
        try {
            String objectName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmssddMMyyyy"));
            log.info("Content type of file is: {}", file.getContentType());
            objectName = objectName + "." +MimeTypes.getDefaultExt(file.getContentType());
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .object(objectName)
                            .contentType(file.getContentType())
                            .stream(file.getInputStream(), file.getSize(), partsize)
                            .bucket(bucket)
                            .build()
            );
            log.info("Put success file : {}", file.getOriginalFilename());

            return new FileResponse(file.getOriginalFilename(), objectName);
        } catch (MinioException e) {
            log.error("Error occurred: " + e);
            log.error("HTTP trace: " + e.httpTrace());
            throw new BusinessException(ResponseCode.FILE_MINIO_ERROR);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResponseCode.FILE_UPLOAD_FAILED);
        }
    }


    public FileDto getFile(String objectName) {
        try {
            log.info("Object name : {}", objectName);
            InputStream stream = minioClient.getObject(
                            GetObjectArgs.builder()
                                    .bucket(bucket)
                                    .object(objectName)
                                    .build());
            FileDto fileDto = new FileDto();
            fileDto.setObjectName(objectName);
            fileDto.setContent(stream.readAllBytes());
            String[] slit = objectName.split("\\.");
            log.info(Arrays.toString(slit));
            fileDto.setContentType(MimeTypes.lookupMimeType(slit[1]));
            stream.close();
            return fileDto;
        } catch (MinioException e) {
            log.error("Error occurred: " + e);
            log.error("HTTP trace: " + e.httpTrace());
            throw new BusinessException(ResponseCode.FILE_MINIO_ERROR);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResponseCode.FILE_GET_ERROR);
        }
    }

}
