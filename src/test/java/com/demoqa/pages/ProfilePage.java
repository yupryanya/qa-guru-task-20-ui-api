package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {
    SelenideElement removeBookButton = $("#delete-record-undefined" ),
            confirmDeletionModalButton = $("#closeSmallModal-ok" ),
            bookTable = $(".ReactTable" );

    public ProfilePage openPage() {
        open("/profile" );
        return this;
    }

    public ProfilePage removeFirstBookFromProfile() {
        removeBookButton.click();
        confirmDeletionModalButton.click();
        return this;
    }

    public ProfilePage checkTheBookIsNotDisplayedInProfile(String bookName) {
        bookTable.shouldNotHave(text(bookName));
        return this;
    }

    public ProfilePage checkTheBookIsDisplayedInProfile(String bookName) {
        bookTable.shouldHave(text(bookName));
        return this;
    }
}
