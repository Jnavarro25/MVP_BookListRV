package com.example.practicalibrosreciclerview.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String category;
    private String urlImage;
    private String createdOn;
    private String pagesNumber;
    private String isbn;
    private String description;

    public Book(
            String title,
            String author,
            String category,
            String urlImage,
            String createdOn,
            String pagesNumber,
            String isbn,
            String description) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.urlImage = urlImage;
        this.createdOn = createdOn;
        this.pagesNumber = pagesNumber;
        this.isbn = isbn;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public String getCategory() {
        return category;
    }


    public String getUrlImage() {
        return urlImage;
    }


    public String getCreatedOn() {
        return createdOn;
    }


    public String getPagesNumber() {
        return pagesNumber;
    }


    public String getIsbn() {
        return isbn;
    }


    public String getDescription() {
        return description;
    }

}
