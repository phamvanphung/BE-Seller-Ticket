package com.example.ticketsystem.file.controller;


import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.file.dto.common.FileDto;
import com.example.ticketsystem.file.dto.response.FileResponse;
import com.example.ticketsystem.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController implements IFileController {


    @Autowired
    private FileService fileService;


    @Override
    public ResponseEntity<ApiResponse<FileResponse>> upload(MultipartFile file) {
        FileResponse responseFile  = fileService.upload(file);
        ApiResponse<FileResponse> response = new ApiResponse<FileResponse>().ok(responseFile);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> downloadFile(String nameObject) {
        FileDto dto = fileService.getFile(nameObject);
        return ResponseEntity.ok()
                .contentLength(dto.getContent().length)
                .header(HttpHeaders.CONTENT_TYPE, dto.getContentType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + dto.getObjectName()).body(dto.getContent());
    }
}
