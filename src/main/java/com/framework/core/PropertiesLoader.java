package com.framework.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    Properties props;

    public PropertiesLoader() {
        props = new Properties();
    }

    public void loadAllProperties(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        props.load(fileInputStream);
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public static void main(String[] args) throws IOException {
        PropertiesLoader obj = new PropertiesLoader();
        obj.loadAllProperties(System.getProperty("user.dir") + "//src//main//resources//OR.properties");
        System.out.println(obj.getProperty("LoginPage.tbx_Username"));

    }

}
