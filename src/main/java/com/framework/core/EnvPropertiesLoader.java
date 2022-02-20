package com.framework.core;

import java.io.IOException;

public class EnvPropertiesLoader {

    public static String browser;
    public static String chromeBinaryPath;
    public static String firefoxBinaryPath;
    public static String edgeBinaryPath;
    public static String ieBinaryPath;
    public static long standardWait;
    public static long longWait;
    public static long shortWait;
    public static String applicationUrl;
    public static boolean takescreenshot;
    public static String environment;

    public static void loadEnvProperties() throws IOException {
        PropertiesLoader propertiesLoaderObj = new PropertiesLoader();
        propertiesLoaderObj.loadAllProperties(System.getProperty("user.dir") + "//src//main//resources//config.properties");
        if (System.getProperty("browser") == null || System.getProperty("browser").isEmpty()) {
            browser = propertiesLoaderObj.getProperty("browserName");
        } else {
            browser = System.getProperty("browser");
        }
        chromeBinaryPath = propertiesLoaderObj.getProperty("chromeBinaryPath");
        firefoxBinaryPath = propertiesLoaderObj.getProperty("firefoxBinaryPath");
        edgeBinaryPath = propertiesLoaderObj.getProperty("edgeBinaryPath");
        ieBinaryPath = propertiesLoaderObj.getProperty("ieBinaryPath");
        standardWait = Long.valueOf(propertiesLoaderObj.getProperty("standardWait"));
        longWait = Long.valueOf(propertiesLoaderObj.getProperty("longWait"));
        shortWait = Long.valueOf(propertiesLoaderObj.getProperty("shortWait"));
        applicationUrl = propertiesLoaderObj.getProperty("applicationUrl");
        takescreenshot = Boolean.valueOf(propertiesLoaderObj.getProperty("takescreenshot"));
        if (System.getProperty("environment") == null || System.getProperty("environment").isEmpty())
            environment = propertiesLoaderObj.getProperty("environment");
        else
            environment = System.getProperty("environment");

    }

}
