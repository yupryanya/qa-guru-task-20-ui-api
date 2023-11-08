package com.demoqa.helpers;

import com.demoqa.api.AccountApi;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", AccountApi.getAuthorizationResponse().getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", AccountApi.getAuthorizationResponse().getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", AccountApi.getAuthorizationResponse().getToken()));
    }
}
