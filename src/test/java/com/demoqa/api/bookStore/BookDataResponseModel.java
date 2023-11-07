package com.demoqa.api.bookStore;

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