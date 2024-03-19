package com.example.ticketsystem.file.controller;


import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.file.dto.response.FileResponse;
import jakarta.mail.internet.ContentType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/file")
public interface IFileController {

    //Todo: upload file

    @PostMapping("/v1/upload")
    ResponseEntity<ApiResponse<FileResponse>> upload(@RequestParam(name = "file")MultipartFile file);
    //Todo: Download file

    @GetMapping(value = "/v1/download", produces = MediaType.IMAGE_PNG_VALUE)
    ResponseEntity<byte[]> downloadFile(@RequestParam(name = "name") String nameObject);

}
