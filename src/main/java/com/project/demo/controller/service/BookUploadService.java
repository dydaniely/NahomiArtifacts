package com.project.demo.controller.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author isddyt
 * 4/16/2019
 */
public interface BookUploadService {
    void uploadToBooksBucket(MultipartFile multipartFile,boolean enablePublicReadAccess);
    void deleteBooksfromBucket(String fileName);
}
