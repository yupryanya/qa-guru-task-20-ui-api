package com.demoqa.models.bookStore;

import lombok.Data;

@Data
public class BookDataResponseModel {
    private String isbn,
            title,
            subTitle,
            author,
            publish_date,
            description,
            website,
            publisher;
    private int pages;
}