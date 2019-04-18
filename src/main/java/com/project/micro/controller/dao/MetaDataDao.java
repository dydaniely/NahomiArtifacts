package com.project.micro.controller.dao;

import com.project.micro.model.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author isddyt
 * 4/17/2019
 */
@Repository
public interface MetaDataDao extends JpaRepository<MetaData, Long> {

    @Query("select  a from Document a where a.keys=:keys")
    MetaData findAllByKeys(String keys);
}
