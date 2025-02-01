package com.individual.individual_project.comm.file;

import lombok.Data;

@Data
public class UploadFileDto {

    private String uploadFileName;
    private String storeFileName;

    public UploadFileDto(String originalFilename, String storeFileName) {
        this.uploadFileName = originalFilename;
        this.storeFileName = storeFileName;
    }
}
