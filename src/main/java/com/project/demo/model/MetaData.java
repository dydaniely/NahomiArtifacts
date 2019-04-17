package com.project.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author isddyt
 * 4/17/2019
 */

@Getter
@Setter
@AllArgsConstructor

@NoArgsConstructor
@Entity
public class MetaData {
    @Id
    @GeneratedValue
    private long id;
    private String referenceKey;
    private LocalDate modOn;
    private LocalDate expireOn;
    private boolean status;

    public MetaData(String referenceKey, LocalDate modOn, LocalDate expireOn, boolean status) {
        this.referenceKey = referenceKey;
        this.modOn = modOn;
        this.expireOn = expireOn;
        this.status = status;
    }
}
