package com.project.demo.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

/**
 * @author isddyt
 * 4/17/2019
 */
@Data
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
