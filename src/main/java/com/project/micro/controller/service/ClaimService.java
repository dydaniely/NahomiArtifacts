package com.project.micro.controller.service;

import com.project.micro.controller.dao.ClaimDao;
import com.project.micro.model.Claim;
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
