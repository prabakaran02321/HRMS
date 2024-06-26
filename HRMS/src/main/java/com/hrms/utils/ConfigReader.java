package com.hrms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final String FILE_NAME = "app.properties";
    private static Properties properties;

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (input == null) {
                throw new IOException("Unable to find " + FILE_NAME);
            }
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
