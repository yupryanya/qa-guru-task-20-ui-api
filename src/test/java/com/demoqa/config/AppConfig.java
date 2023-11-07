package com.demoqa.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/${env}.properties"
})

public interface AppConfig extends Config {
    @Key("browser" )
    @DefaultValue("chrome" )
    String browser();

    @Key("browserVersion" )
    @DefaultValue("100" )
    String browserVersion();

    @Key("browserSize" )
    @DefaultValue("1920x1080" )
    String browserSize();

    @Key("apiUrl" )
    @DefaultValue("https://demoqa.com" )
    String apiUrl();

    @Key("webUrl" )
    @DefaultValue("https://demoqa.com" )
    String webUrl();

    @Key("isRemote" )
    @DefaultValue("false" )
    boolean isRemote();

    @Key("remoteUrl" )
    String remoteUrl();

    @Key("username" )
    @DefaultValue("IvanIvanov" )
    String username();

    @Key("password" )
    @DefaultValue("IvanIvanov1!" )
    String password();
}