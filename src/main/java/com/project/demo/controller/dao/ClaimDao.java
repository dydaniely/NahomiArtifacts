package com.project.demo.controller.dao;

import com.project.demo.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author isddyt
 * 4/15/2019
 */
@Repository
@Transactional
public interface ClaimDao extends JpaRepository<Claim, Long> {

    @Override
    List<Claim> findAll();

    @Query("select a from Claim  a where a.webClaimNo=:webClaimNo")
    Claim findAllByWebClaimNo(long webClaimNo);
}
