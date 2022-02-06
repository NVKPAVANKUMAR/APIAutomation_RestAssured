package com.spotify.oauth2.util;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/main/resources/config.properties");
    }

    //Implementation of Singleton Pattern
    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("base_url");
        if (prop != null) return prop;
        else throw new RuntimeException("Property client_id is not specified in the config.properties file");
    }

}
