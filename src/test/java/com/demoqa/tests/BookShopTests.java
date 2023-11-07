package com.demoqa.tests;

import com.demoqa.helpers.WithLogin;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.qameta.allure.Allure.step;

public class BookShopTests extends TestBase {
    @ParameterizedTest(name = "Delete one book from user collection")
    @Tag("regression")
    @WithLogin
    @ValueSource(strings = {
            "9781491904244"
    })
    void deleteBookFromCollectionTest(String bookIsbn) {
        step("Clear book collection", () ->
                bookStoreApi.clearBookCollection()
        );
        step("Add book to collection by ISBN", () ->
                bookStoreApi.addBookToCollection(bookIsbn)
        );
        String bookName = step("Get book name by its ISBN", () ->
                bookStoreApi.GetBookDataByIsbn(bookIsbn).getTitle()
        );
        step("Open profile page", () ->
                profilePage.openPage()
        );
        step("Check the profile page contains added book", () ->
                profilePage.checkTheBookIsDisplayedInProfile(bookName)
        );
        step("Delete book from collection", () ->
                profilePage.removeFirstBookFromProfile()
        );
        step("Check the profile page does not contain deleted book", () ->
                profilePage.checkTheBookIsNotDisplayedInProfile(bookName)
        );
    }
}
