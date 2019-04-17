package com.project.demo.controller.service;

import com.project.demo.model.Book;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author isddyt
 * 4/16/2019
 */
public interface BookUploadService {
    void
    uploadToBooksBucket(MultipartFile multipartFile,boolean enablePublicReadAccess);
    void deleteBooksfromBucket(String fileName);
    void save(Book book);

}
