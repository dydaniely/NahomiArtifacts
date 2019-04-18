package com.project.micro.controller.dao;

import com.project.micro.model.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author isddyt
 * 4/18/2019
 */
@Repository
public interface DocumentDao  extends JpaRepository<Document,Long> {

    @Query("select  a from Document a where a.keys=:keys")
    List<Document> findAllByKeys(String keys);



}
