package com.project.micro.controller.service.S3Operation;

import com.project.micro.model.Document;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author isddyt
 * 4/16/2019
 */
public interface DocumentUploadService {
    void uploadToBooksBucket(MultipartFile multipartFile,boolean enablePublicReadAccess);
    void deleteBooksfromBucket(String fileName);
    void save(Document document);
    void scan(MultipartFile file) throws Exception;


}
