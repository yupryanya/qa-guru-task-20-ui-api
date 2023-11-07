package com.demoqa.helpers;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.demoqa.config.App.appConfig;
import static com.codeborne.selenide.Configuration.*;

public class WebDriverProvider {
    public static void webDriverConfigInit() {
        pageLoadStrategy = "eager";
        baseUrl = appConfig.webUrl();
        browser = appConfig.browser();
        browserVersion = appConfig.browserVersion();
        browserSize = appConfig.browserSize();

        if (appConfig.isRemote()) {
            remote = "https://user1:1234@" + appConfig.remoteUrl() + "/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", browser);
            capabilities.setCapability("browserVersion", browserVersion);
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            browserCapabilities = capabilities;
        }
    }
}