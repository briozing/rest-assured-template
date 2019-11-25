package com.briozing.automation.utils;

import com.briozing.automation.factory.Log4JFactory;
import org.apache.log4j.Logger;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MainUtils {

    public static <T> T loadJson(String serviceName, String JsonFileName, Class<T> type) {
        return ObjectMapperUtils
                .readValue("src/test/resources/configurations/" + serviceName + "/request/" + JsonFileName, type);
    }

    /**
     * @param key
     * @return
     */
    public static String dbReadableFormat(String key) {
        return ("'" + key + "'");
    }

    /**
     * @param logger
     * @param testName
     */
    public static void testNameLog(Logger logger, String testName) {
        logger.info("==========================================================================================");
        logger.info("::::::: TEST NAME : " + testName + " :::::::::");
        logger.info("==========================================================================================");
        Reporter.log("TEST NAME : " + testName);
    }

    public static void stepLog(Logger logger, String stepName) {
        logger.info("--------------------------------------------------------------------------------------------");
        logger.info("::::::: Step : " + stepName + " :::::::::");
        logger.info("--------------------------------------------------------------------------------------------");
        Reporter.log("Test Step Name : " + stepName);
    }

    public static void ValidationLog(Logger logger, String message) {
        logger.info("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        logger.info(">>>>>>>>>> VALIDATION : " + message + "<<<<<<<<<<<<<");
        logger.info("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        Reporter.log("Validation : " + message);
    }

    public static void dBStepsLog(String stepMessage) {

        Log4JFactory.getCommonLogger().info("DB LOG ==========> :: " + stepMessage);
        Reporter.log("DB Log : " + stepMessage);

    }

    /**
     * will fetch system properties
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        String value = null;
        try {
            value = System.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            Log4JFactory.getCommonLogger().info("System.fetchProperty : Error while getting key value, KEY: " + key);
        }
        AppAssert.assertTrue(value != null, "Verify value of key " + key + "should not null");
        return value;
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

    public static Map<String, String> getDateFieldForUnixTimeStamp(String col, String value) {
        String colValue = "";
        String colName = "";

        if (value == null || value.isEmpty() || value.toUpperCase().contains("NA")
                || value.toUpperCase().contains("NULL") || value.trim().length() == 0) {
            colValue = "";
            colName = "";
        } else if (value != null && value.toUpperCase().contains("NOW")) {
            colValue = "UNIX_TIMESTAMP(" + value + ")*1000,";
            colName = "`" + col + "`,";
        } else {
            colValue = value + ",";
            colName = "`" + col + "`,";
        }

        Map<String, String> data = new HashMap<>();
        data.put("col", colName);
        data.put("value", colValue);
        return data;

    }

    public static Map<String, String> getSQLDate(String col, String value) {
        String colValue = "";
        String colName = "";

        if (value == null || value.isEmpty() || value.toUpperCase().contains("NA")
                || value.toUpperCase().contains("NULL") || value.trim().length() == 0) {
            colValue = "";
            colName = "";
        } else if (value != null && value.toUpperCase().contains("NOW")) {
            colValue = value + ",";
            colName = "`" + col + "`,";
        } else {
            colValue = "'" + value + "',";
            colName = "`" + col + "`,";
        }

        Map<String, String> data = new HashMap<>();
        data.put("col", colName);
        data.put("value", colValue);
        return data;

    }

    public static boolean isNeedToRun(Map<String, Boolean> testSteps, String step) {
        if (testSteps.get(step)) {
            stepLog(Log4JFactory.getCommonLogger(), step);
            return true;
        }
        return false;
    }

    /**
     * @param msg
     * @param log
     */
    public static void reportingLog(String msg, Logger log) {
        Reporter.log(msg);
        log.info(msg);
    }
}
