package com.demoqa.api.bookStore;

import lombok.Data;

import java.util.List;

@Data
public class AddBookResponseModel {
    private List<Book> books;

    @Data
    public static class Book {
        private String isbn;
    }
}

