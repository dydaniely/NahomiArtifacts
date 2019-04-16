package com.project.demo.controller.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author isddyt
 * 4/16/2019
 */
@Component
public class BookUploadServiceImplementation implements BookUploadService {

    private String awsS3bookBucket;
    private AmazonS3 amazonS3;
    static final Logger logger = LoggerFactory.getLogger(BookUploadServiceImplementation.class);

    @Autowired
    public BookUploadServiceImplementation(Region awsRegion, AWSCredentialsProvider awsCredentialProvider, String awsS3bookBucket) {
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(awsCredentialProvider)
                .withRegion(awsRegion.getName()).build();
        this.awsS3bookBucket = awsS3bookBucket;
    }

   @Async
    public void uploadToBooksBucket(MultipartFile multipartFile, boolean enablePublicReadAccess) {
        String filename = multipartFile.getOriginalFilename();
        File file = new File(filename);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(multipartFile.getBytes());
            outputStream.close();
            PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3bookBucket, filename, file);
            if (enablePublicReadAccess) {
                putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);

            }
            this.amazonS3.putObject(putObjectRequest);
            file.delete();
        } catch (IOException | AmazonServiceException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBooksfromBucket(String fileName) {

    }
}
