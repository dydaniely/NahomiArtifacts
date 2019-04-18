package com.project.micro.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author isddyt
 * 4/17/2019
 */

@Data
@NoArgsConstructor
@Entity
public class MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String referenceKey;
    private LocalDate modOn;
    private LocalDate expireOn;
    private boolean status;
    private byte[] tempfile;

    public MetaData(String referenceKey, LocalDate modOn, LocalDate expireOn, boolean status,byte[] tempfile) {
        this.referenceKey = referenceKey;
        this.modOn = modOn;
        this.expireOn = expireOn;
        this.status = status;
        this.tempfile=tempfile;
    }
}
