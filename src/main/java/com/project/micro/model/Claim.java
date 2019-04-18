package com.project.micro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Claim extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "claimGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "webClaimNo",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "claimGen")
    private long webClaimNo;

    private String claimNo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/New York")
    private Date lossDate;
    private String incidentDesc;
    private String damageDesc;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "America/New York")
    private Date reportedDate;
    @JsonIgnore
    private String claimssvcrqmsgs;/*Claim service requests to next workflow */
    @JsonIgnore
    private String claimssvcrsmsgs;/*Claim service response from work flow */
    private String status;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private Date modOn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private ClaimAddress whereOccurred;
    @JsonIgnore
    @OneToMany(mappedBy = "claim", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ClaimsDocument> claimsDocumentList;


    public Claim() {
    }

    public Claim(long webClaimNo, String claimNo, Date lossDate, String incidentDesc, String damageDesc, Date reportedDate) {
        this.webClaimNo = webClaimNo;
        this.claimNo = claimNo;
        this.lossDate = lossDate;
        this.incidentDesc = incidentDesc;
        this.damageDesc = damageDesc;
        this.reportedDate = reportedDate;

    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getClaimNo() {
        return claimNo;
    }

    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }

    public long getWebClaimNo() {
        return webClaimNo;
    }

    public void setWebClaimNo(long webClaimNo) {
        this.webClaimNo = webClaimNo;
    }

    public Date getLossDate() {
        return lossDate;
    }

    public void setLossDate(Date lossDate) {
        this.lossDate = lossDate;
    }

    public String getIncidentDesc() {
        return incidentDesc;
    }

    public void setIncidentDesc(String incidentDesc) {
        this.incidentDesc = incidentDesc;
    }

    public String getDamageDesc() {
        return damageDesc;
    }

    public void setDamageDesc(String damageDesc) {
        this.damageDesc = damageDesc;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getClaimssvcrqmsgs() {
        return claimssvcrqmsgs;
    }

    public void setClaimssvcrqmsgs(String claimssvcrqmsgs) {
        this.claimssvcrqmsgs = claimssvcrqmsgs;
    }

    public String getClaimssvcrsmsgs() {
        return claimssvcrsmsgs;
    }

    public void setClaimssvcrsmsgs(String claimssvcrsmsgs) {
        this.claimssvcrsmsgs = claimssvcrsmsgs;
    }

    public ClaimAddress getWhereOccurred() {
        return whereOccurred;
    }

    public void setWhereOccurred(ClaimAddress whereOccurred) {
        this.whereOccurred = whereOccurred;
    }

    public List<ClaimsDocument> getClaimsDocumentList() {
        return claimsDocumentList;
    }

    public void setClaimsDocumentList(List<ClaimsDocument> claimsDocumentList) {
        this.claimsDocumentList = claimsDocumentList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getModOn() {
        return modOn;
    }

    public void setModOn(Date modOn) {
        this.modOn = modOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Claim claim = (Claim) o;
        return getWebClaimNo() == claim.getWebClaimNo() &&
                Objects.equals(getClaimNo(), claim.getClaimNo()) &&
                Objects.equals(getLossDate(), claim.getLossDate()) &&
                Objects.equals(getIncidentDesc(), claim.getIncidentDesc()) &&
                Objects.equals(getDamageDesc(), claim.getDamageDesc()) &&
                Objects.equals(getReportedDate(), claim.getReportedDate()) &&
                Objects.equals(getClaimssvcrqmsgs(), claim.getClaimssvcrqmsgs()) &&
                Objects.equals(getClaimssvcrsmsgs(), claim.getClaimssvcrsmsgs()) &&
                Objects.equals(getStatus(), claim.getStatus()) &&
                Objects.equals(getContactName(), claim.getContactName()) &&
                Objects.equals(getContactEmail(), claim.getContactEmail()) &&
                Objects.equals(getContactPhone(), claim.getContactPhone()) &&
                Objects.equals(getModOn(), claim.getModOn()) &&
                Objects.equals(getWhereOccurred(), claim.getWhereOccurred()) &&
                Objects.equals(getClaimsDocumentList(), claim.getClaimsDocumentList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWebClaimNo(), getClaimNo(), getLossDate(), getIncidentDesc(), getDamageDesc(), getReportedDate(), getClaimssvcrqmsgs(), getClaimssvcrsmsgs(), getStatus(), getContactName(), getContactEmail(), getContactPhone(), getModOn(), getWhereOccurred(), getClaimsDocumentList());
    }
}

