package com.demoqa.config;

import org.aeonbits.owner.ConfigFactory;

public class App {
    public static AppConfig appConfig = ConfigFactory.create(AppConfig.class, System.getProperties());
}