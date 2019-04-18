package com.project.micro.controller.service;

import com.project.micro.controller.dao.DocumentDao;
import com.project.micro.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author isddyt
 * 4/18/2019
 */
@Service
public class DocumentService {

    private DocumentDao documentDao;

    @Autowired
    public DocumentService(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }


    public List<Document> getDocument(String key){
        return documentDao.findAllByKeys(key);
    }

    public void placeDocument(Document document1) {
        documentDao.save(document1);
    }
}
