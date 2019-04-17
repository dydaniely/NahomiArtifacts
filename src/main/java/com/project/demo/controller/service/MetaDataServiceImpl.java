package com.project.demo.controller.service;

import com.project.demo.controller.dao.MetaDataServiceDao;
import com.project.demo.model.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author isddyt
 * 4/17/2019
 */
@Service
public class MetaDataServiceImpl {
    private MetaDataServiceDao metaDataServiceDao;

    @Autowired
    public MetaDataServiceImpl(MetaDataServiceDao metaDataServiceDao) {
        this.metaDataServiceDao = metaDataServiceDao;
    }

    public  void placeMetaData(MetaData metadata) {
        metaDataServiceDao.save(metadata);
    }

   public void remove(MetaData metaData) {
        metaDataServiceDao.delete(metaData);
    }
}
