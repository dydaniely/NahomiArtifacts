package com.project.micro.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

/**
 * @author isddyt
 * 4/17/2019
 */
@Data
@Entity
public class Document {
    @Id
    @GeneratedValue
    long id;
    String isbn;
    String title;
    String author;
    String category;
    LocalDate yearPublished;
    LocalDate modOn;
    double price;
    byte[] fileName;
    String keys;

}
