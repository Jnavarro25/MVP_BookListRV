package com.example.practicalibrosreciclerview.Model;

import java.io.Serializable;

public class Book implements Serializable
{
    private String title;
    private String author;
    private String category;
    private String urlImage;
    private String createdOn;
    private String pagesNumber;
    private String isbn;
    private String description;


    public Book(){}

    public Book(String title, String author, String category, String urlImage, String createdOn, String pagesNumber, String isbn, String description) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(String pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
