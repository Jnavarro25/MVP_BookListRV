package com.example.practicalibrosreciclerview.model;

import java.io.Serializable;

public class Book implements Serializable {

    private String id;
    private String isbn;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String published;
    private String publisher;
    private String pagesNumber;
    private String description;
    private String imageUrl;
    private String category;

    public Book (){}

    public Book(
            String id,
            String isbn,
            String title,
            String authorFirstName,
            String authorLastName,
            String published,
            String publisher,
            String pagesNumber,
            String description,
            String imageUrl,
            String category) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.published = published;
        this.publisher = publisher;
        this.pagesNumber = pagesNumber;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getPublished() {
        return published;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPagesNumber() {
        return pagesNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategory() {
        return category;
    }
}
