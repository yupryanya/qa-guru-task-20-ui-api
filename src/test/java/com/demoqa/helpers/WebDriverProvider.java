package com.demoqa.helpers;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.demoqa.config.App.appConfig;

public class WebDriverProvider {
    public static void webDriverConfigInit() {
        Configuration.baseUrl = appConfig.webUrl();
        Configuration.browser = appConfig.browser();
        Configuration.browserVersion = appConfig.browserVersion();
        Configuration.browserSize = appConfig.browserSize();

        if (appConfig.isRemote()) {
            Configuration.remote = appConfig.remoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", Configuration.browser);
            capabilities.setCapability("browserVersion", Configuration.browserVersion);
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }
}