package com.briozing.automation.utils;

import com.briozing.automation.factory.Log4JFactory;
import org.apache.log4j.Logger;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class MainUtils {

    public static void stepLog(Logger logger, String stepName) {
        logger.info("--------------------------------------------------------------------------------------------");
        logger.info("::::::: Step : " + stepName + " :::::::::");
        logger.info("--------------------------------------------------------------------------------------------");
        Reporter.log("Test Step Name : " + stepName);
    }

    /**
     * will fetch from passed {@link Properties} Object
     *
     * @param properties
     * @param key
     * @return
     */
    public static String fetchProperty(Properties properties, String key) {
        String value = null;
        try {
            value = properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            Log4JFactory.getCommonLogger().info("Properties.fetchProperty : Key not found in property object " + key);
        }
        AppAssert.assertTrue(value != null, " Verify value of key " + key + " should not null");
        return value;
    }

    public static FileInputStream loadProperties(String propertiesFile) {
        FileInputStream inputStream = null;
        String filePath = System.getProperty("user.dir") + "/" + (propertiesFile);
        File configFile = new File(filePath);
        AppAssert.assertTrue(configFile != null, "Verify not null");
        try {
            inputStream = new FileInputStream(configFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log4JFactory.getCommonLogger().info("Error while getting file object" + e);
        }
        AppAssert.assertTrue(inputStream != null, "Verify not null");
        return inputStream;

    }

}
