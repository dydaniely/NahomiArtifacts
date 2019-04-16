package com.project.demo.controller;

import com.project.demo.controller.service.BookUploadServiceImplementation;
import com.project.demo.controller.service.ClaimService;
import com.project.demo.model.Claim;
import com.project.demo.model.ClaimsDocument;
import fi.solita.clamav.ClamAVClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author isddyt
 * 4/12/2019
 */

@RestController
@RequestMapping("/")
public class FileUploadController {
    private Logger log = LogManager.getLogger(FileUploadController.class);
    private ClaimService claimService;
    private BookUploadServiceImplementation bookUploadServiceImplementation;

    @Autowired
    public FileUploadController(ClaimService claimService,BookUploadServiceImplementation bookUploadServiceImplementation) {
        this.claimService = claimService;
        this.bookUploadServiceImplementation=bookUploadServiceImplementation;
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
    public HttpStatus scanFiles() {
        List<Claim> claims = claimService.getAllClaims();
        ClamAVClient cl = new ClamAVClient("10.100.1.111", 3310);
        List<String> errorFiles = new ArrayList<>();
        for (Claim claim : claims) {
            for (ClaimsDocument claimsDocument : claim.getClaimsDocumentList()) {
                log.info("scanning file :" + claimsDocument.getFileName());
                try {
                    cl.scan(claimsDocument.getDocument());
                } catch (IOException e) {
                    log.info("could not scan :" + claimsDocument.getFileName());
                    errorFiles.add(claimsDocument.getFileName());
                }
            }
        }
        if (errorFiles.isEmpty()) {
            return HttpStatus.OK;
        } else {
            System.out.println("Errors");
            errorFiles.forEach(System.out::println);
            return HttpStatus.CONFLICT;
        }
    }


    @PostMapping("/uploadBooks")
    public HttpStatus uploadBooks(@RequestPart (value = "file") MultipartFile file){
        this.bookUploadServiceImplementation.uploadToBooksBucket(file,true);
        return HttpStatus.OK;
    }

}
