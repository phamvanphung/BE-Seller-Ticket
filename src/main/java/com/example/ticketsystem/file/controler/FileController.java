package com.example.ticketsystem.file.controler;


import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.enums.ResponseCode;
import com.example.ticketsystem.file.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private MinioService fileService;

    @PostMapping(value = "/v1/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<ApiResponse<Object>> upload(@RequestParam("file")MultipartFile file) {
        String response = fileService.upload(file);
        return new ResponseEntity<>(new ApiResponse<>(ResponseCode.SUCCESS, new String(response)), HttpStatus.OK);
    }
}
