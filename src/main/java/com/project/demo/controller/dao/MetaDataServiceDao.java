package com.project.demo.controller.dao;

import com.project.demo.model.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.ReportAsSingleViolation;

/**
 * @author isddyt
 * 4/17/2019
 */
@Repository
public interface MetaDataServiceDao extends JpaRepository<MetaData,Long> {


}
