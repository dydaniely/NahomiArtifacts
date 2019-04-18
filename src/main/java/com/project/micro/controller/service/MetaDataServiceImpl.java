package com.project.micro.controller.service;

import com.project.micro.controller.dao.MetaDataDao;
import com.project.micro.model.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author isddyt
 * 4/17/2019
 */
@Service
public class MetaDataServiceImpl {
    private MetaDataDao metaDataDao;

    @Autowired
   public MetaDataServiceImpl(MetaDataDao metaDataDao) {
        this.metaDataDao = metaDataDao;
    }

   public  void placeMetaData(MetaData metadata) {
        metaDataDao.save(metadata);
    }

   public void remove(MetaData metaData) {
        metaDataDao.delete(metaData);
    }

    public List<MetaData> getAllDocuments() {
            return metaDataDao.findAll();
    }

    public MetaData getAllByKey(String key) {
        return metaDataDao.findAllByKeys(key);
    }
}
