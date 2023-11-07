package com.demoqa.api.bookStore;

import com.demoqa.api.account.AccountApi;

import java.util.List;

import static com.demoqa.specs.BookStoreSpec.*;
import static io.restassured.RestAssured.given;

public class BookStoreApi {
    public void clearBookCollection() {
        given(requestWithAuthSpec)
                .queryParams("UserId", AccountApi.getAuthorizationResponse().getUserId())
                .when()
                .delete("/Books")
                .then()
                .spec(responseWithStatusCode204Spec);
    }

    public AddBookResponseModel addBookToCollection(String bookIsbn) {
        AddBookRequestModel bookData = new AddBookRequestModel();

        bookData.setUserId(AccountApi.getAuthorizationResponse().getUserId());

        AddBookRequestModel.Isbn isbn = new AddBookRequestModel.Isbn();
        isbn.setIsbn(bookIsbn);
        bookData.setCollectionOfIsbns(List.of(isbn));

        return given(requestWithAuthSpec)
                .body(bookData)
                .when()
                .post("/Books")
                .then()
                .spec(responseWithStatusCode201Spec)
                .extract().as(AddBookResponseModel.class);
    }

    public BookDataResponseModel GetBookDataByIsbn(String isbn) {
        return given(requestWithAuthSpec)
                .queryParams("ISBN", isbn)
                .when()
                .get("/Book")
                .then()
                .spec(responseWithStatusCode200Spec)
                .extract().as(BookDataResponseModel.class);
    }
}