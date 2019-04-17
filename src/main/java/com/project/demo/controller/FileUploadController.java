package com.project.demo.controller;


import com.project.demo.controller.service.BookUploadServiceImplementation;
import com.project.demo.controller.service.ClaimService;
import com.project.demo.controller.service.MetaDataServiceImpl;
import com.project.demo.model.MetaData;
import fi.solita.clamav.ClamAVClient;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author isddyt
 * 4/12/2019
 */

@RestController
@RequestMapping("/")
public class FileUploadController {
    static Logger log = LogManager.getLogger(FileUploadController.class);
    private ClaimService claimService;
    private BookUploadServiceImplementation bookUploadServiceImplementation;
    private MetaDataServiceImpl metaDataService;

    @Autowired
    public FileUploadController(ClaimService claimService, BookUploadServiceImplementation bookUploadServiceImplementation, MetaDataServiceImpl metaDataService) {
        this.claimService = claimService;
        this.bookUploadServiceImplementation = bookUploadServiceImplementation;
        this.metaDataService = metaDataService;
    }

    @PostMapping("/uploadFiles")
    public HttpStatus submitClaim(@RequestPart(value = "files", required = false) MultipartFile files) throws Exception {
        ClamAVClient cl = new ClamAVClient("10.100.1.111", 3310);
        byte[] reply;
        try {
            boolean value = cl.ping();
            reply = cl.scan(files.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Could not scan the input", e.getCause());
        }
        if (!ClamAVClient.isCleanReply(reply)) {
            throw new Exception("aaargh. Something was found");
        }
        return HttpStatus.OK;
    }

    @GetMapping("/scanBackLogs")


    @PostMapping("/uploadBooks")
    public ResponseEntity<Map<String, String>> uploadBooks(@RequestPart(value = "file") MultipartFile file) {
        //store Image
        this.bookUploadServiceImplementation.uploadToBooksBucket(file, true);
        MetaData metaData = new MetaData(RandomStringUtils.randomAlphanumeric(10).toUpperCase(), LocalDate.now(), LocalDate.now().plusDays(3), true);
        //Save Book properties
        metaDataService.placeMetaData(metaData);
        Map<String, String> value = new HashMap<>();
        value.put(metaData.getReferenceKey(), "Data Saved ");
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}

