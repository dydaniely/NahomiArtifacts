package com.project.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;
/**
 * @author isddyt
 * 4/17/2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    long id;
    String isbn;
    String title;
    String author;
    String category;
    LocalDate yearPublished;
    LocalDate modOn;
    double price;
    String fileName;
}
