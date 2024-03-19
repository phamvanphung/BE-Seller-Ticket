package com.example.ticketsystem.file.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto {
    private byte[] content;
    private String objectName;
    private String contentType;
}
