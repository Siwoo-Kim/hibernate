package com.learning.jpa.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import java.util.List;


@Getter @Setter @ToString(callSuper = true)
@NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("B")
public class Book extends Item {


    @Builder
    public Book(Long id, String name, int price, int stockQuantity, List<Category> categories, String author, String isbn) {
        super(id, name, price, stockQuantity, categories);
        this.author = author;
        this.isbn = isbn;
    }



    private String author;

    private String isbn;

}
