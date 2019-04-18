package com.project.micro.controller.service;

import com.project.micro.controller.dao.ClaimDao;
import com.project.micro.model.Claim;
import org.junit.Assert;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author isddyt
 * 4/16/2019
 */

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ClaimServiceTest {
    private ClaimDao claimMockDao = mock(ClaimDao.class);
    private ClaimService claimService;

    @Before
    public void setUp() throws Exception {
        claimService = new ClaimService(claimMockDao);
    }

//    @Test
    public void getAllClaimsPositive() {
        Claim claim = new Claim(399, "022019002343", new Date(), "Hail", "Water ", new Date());
        Claim claim1 = new Claim(398, "022019002344", new Date(), "Hail", "Water ", new Date());
        List<Claim> claimSet = new ArrayList<>();
        claimSet.add(claim);
        claimSet.add(claim1);
        when(claimMockDao.findAll()).thenReturn((List<Claim>) claimSet); //behaviour of the mock
        List<Claim> result = (List<Claim>) claimService.getAllClaims();//Test
        Assert.assertEquals(result.size(), 2);//Execution

    }

//    @Test
    public void getClaimsByWebIdPositive() {
        long webClaimNo = 399;
        Claim claim = new Claim(399, "022019002343", new Date(), "Hail", "Water ", new Date());
        Claim claim1 = new Claim(398, "022019002344", new Date(), "Hail", "Water ", new Date());
        List<Claim> claimSet = new ArrayList<>();
        claimSet.add(claim);
        claimSet.add(claim1);
        when(claimMockDao.findAllByWebClaimNo(webClaimNo)).thenReturn(claim);
        Claim result = claimService.getClaimsByWebClaimId(webClaimNo);
        Assert.assertTrue(result.equals(claim));
    }

}