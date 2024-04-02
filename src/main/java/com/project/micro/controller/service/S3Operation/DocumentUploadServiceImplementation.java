package com.project.micro.controller.service.S3Operation;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.project.micro.model.Document;
import fi.solita.clamav.ClamAVClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author isddyt
 * 4/16/2019
 */
@Service
public class DocumentUploadServiceImplementation implements DocumentUploadService {

    private String awsS3bookBucket;
    private AmazonS3 amazonS3;

    static final Logger logger = LoggerFactory.getLogger(DocumentUploadServiceImplementation.class);

    @Autowired
    public DocumentUploadServiceImplementation(Region awsRegion, AWSCredentialsProvider awsCredentialProvider, String awsS3bookBucket) {
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(awsCredentialProvider)
                .withRegion(awsRegion.getName()).build();
        this.awsS3bookBucket = awsS3bookBucket;
    }

    @Async
    public void uploadToBooksBucket(MultipartFile multipartFile, boolean enablePublicReadAccess) {
        logger.debug("Update started here ...aa");
        String filename = multipartFile.getOriginalFilename();
        assert filename != null;
        File file = new File(filename);
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(multipartFile.getBytes());
            outputStream.close();
             PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3bookBucket, filename, file);
            if (enablePublicReadAccess) {
                putObjectRequest.withCannedAcl(CannedAccessControlList.PublicReadWrite);
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

    @Override
    public void save(Document document) {
        logger.info("BookSaved");
    }

    @Override
    public void scan(MultipartFile file) throws Exception {
        ClamAVClient cl = new ClamAVClient("10.100.1.111", 3310);
        byte[] reply;
        try {
            boolean value = cl.ping();
            reply = cl.scan(file.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Could not scan the input", e.getCause());
        }
        if (!ClamAVClient.isCleanReply(reply)) {
            throw new Exception("Something was found");
        }

    }
}
