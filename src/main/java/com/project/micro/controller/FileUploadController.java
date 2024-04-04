package com.project.micro.controller;


import com.project.micro.controller.service.ClaimService;
import com.project.micro.controller.service.DocumentService;
import com.project.micro.controller.service.MetaDataServiceImpl;
import com.project.micro.controller.service.S3Operation.DocumentUploadServiceImplementation;
import com.project.micro.model.Document;
import com.project.micro.model.MetaData;
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
import java.util.List;
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
    private DocumentUploadServiceImplementation documentUploadServiceImplementation;
    private MetaDataServiceImpl metaDataService;
    private final DocumentService documentService;

    @Autowired
    public FileUploadController(ClaimService claimService, DocumentUploadServiceImplementation documentUploadServiceImplementation, MetaDataServiceImpl metaDataService, DocumentService documentService) {
        this.claimService = claimService;
        this.documentUploadServiceImplementation = documentUploadServiceImplementation;
        this.metaDataService = metaDataService;
        this.documentService = documentService;
    }

    @PostMapping("/uploadFiles")
    public HttpStatus submitClaim(@RequestPart(value = "files", required = false) MultipartFile files) throws Exception {
        log.debug("Files uploads start here");
        documentUploadServiceImplementation.scan(files);
        return HttpStatus.OK;
    }


    @PostMapping("/addDocuments")
    public ResponseEntity<Map<String, String>> addDocuments(@RequestPart(value = "file") MultipartFile file) throws Exception {
        //store Image
        documentUploadServiceImplementation.scan(file);
        documentUploadServiceImplementation.uploadToBooksBucket(file, true);
        final String refKey = RandomStringUtils.randomAlphanumeric(5).toUpperCase();
        metaDataService.placeMetaData(new MetaData(refKey, LocalDate.now(), LocalDate.now().plusMonths(3), true, null));
        Map<String, String> value = new HashMap<>();
        value.put("Key", refKey);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @PostMapping("/SubmitDocument")
    public ResponseEntity createDocument(@RequestBody Document document) {
        MetaData metadata = metaDataService.getAllByKey(document.getKeys());
        Document document1 = new Document();
        document1.setAuthor(document.getAuthor());
        document1.setCategory(document.getCategory());
        document1.setFileName(metadata.getTempfile());
        document1.setKeys(document.getKeys());
        documentService.placeDocument(document1);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listDocuments")
    public ResponseEntity<Map<String, List<MetaData>>> getAllDocumentsList() {
        List<MetaData> metaDataList = metaDataService.getAllDocuments();

        Map<String, List<MetaData>> value = new HashMap<>();
        value.put("list", metaDataList);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}

