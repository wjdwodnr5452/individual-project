package com.individual.individual_project.comm.file;

import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.web.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 파일업로드 모듈화
 */
@Slf4j
@Component
public class FileUploadService {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName){
        return fileDir + fileName;
    }


    public List<UploadFileDto> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFileDto> storeFileResult = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public UploadFileDto storeFile(MultipartFile multipartFile)  {

        try {
            if(multipartFile.isEmpty()){
                return null;
            }
            String originalFilename = multipartFile.getOriginalFilename();
            String storeFileName = createStoreFileName(originalFilename);
            multipartFile.transferTo(new File(getFullPath(storeFileName)));
            return new UploadFileDto(originalFilename, storeFileName);

        } catch (IOException e) {
            log.info("파일업로드 실패 : {}", e.getMessage());
            throw new BaseException(ResponseCode.BAD_REQUEST);
        }

    }

    private String createStoreFileName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFilename);
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename){
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);

    }



}
