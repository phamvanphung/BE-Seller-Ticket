package com.example.ticketsystem.file.controler;


import com.example.ticketsystem.dto.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/v1/upload")
    private ResponseEntity<ApiResponse<Object>> upload(@RequestParam("file")MultipartFile file) {

    }
}
