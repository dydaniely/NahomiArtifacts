package com.project.demo.controller.service;

import com.project.demo.controller.dao.ClaimDao;
import com.project.demo.model.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author isddyt
 * 4/16/2019
 */
@Service
public class ClaimService {

    private ClaimDao claimDao;

    @Autowired
    public ClaimService(ClaimDao claimDao) {
        this.claimDao = claimDao;
    }

   public List<Claim> getAllClaims() {
        return claimDao.findAll();
    }

     Claim  getClaimsByWebClaimId(long webClaimNo) {
        return claimDao.findAllByWebClaimNo(webClaimNo);
    }

}
