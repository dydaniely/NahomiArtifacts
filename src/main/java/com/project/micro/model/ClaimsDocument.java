package com.project.micro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.ResourceSupport;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class ClaimsDocument extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long docId;

    private String fileType;

    private String fileName;
    @JsonIgnore
    private byte[] document;

    @JsonIgnore
    private byte[] thumbnailDocument;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/New York")
    private Date creationDate;

    private Long fileSize;
    @JsonIgnore
    private String dimensions;
    @JsonIgnore
    private String make;
    @JsonIgnore
    private String model;
    @JsonIgnore
    private Double latitude;
    @JsonIgnore
    private Double longitude;

    @JsonIgnore
    private boolean sentStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "webClaimNo", nullable = false)
    private Claim claim;

    public long getDocId() {
        return docId;
    }

    public void setDocId(long docId) {
        this.docId = docId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public byte[] getThumbnailDocument() {
        return thumbnailDocument;
    }

    public void setThumbnailDocument(byte[] thumbnailDocument) {
        this.thumbnailDocument = thumbnailDocument;
    }

    public boolean isSentStatus() {
        return sentStatus;
    }


    public void setSentStatus(boolean sentStatus) {
        this.sentStatus = sentStatus;
    }
}
