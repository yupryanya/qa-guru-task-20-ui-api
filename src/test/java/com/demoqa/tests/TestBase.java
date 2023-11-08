package com.demoqa.tests;

import com.demoqa.api.BookStoreApi;
import com.demoqa.config.App;
import com.demoqa.helpers.WebDriverProvider;
import com.demoqa.helpers.Attach;
import com.demoqa.pages.ProfilePage;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    final ProfilePage profilePage = new ProfilePage();
    final BookStoreApi bookStoreApi = new BookStoreApi();

    @BeforeAll
    static void init() {
        WebDriverProvider.webDriverConfigInit();
        RestAssured.baseURI = App.appConfig.apiUrl();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot" );
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (App.appConfig.isRemote()) {
            Attach.addVideo();
        }
        closeWebDriver();
    }
}
