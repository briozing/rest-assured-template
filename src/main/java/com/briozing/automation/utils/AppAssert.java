package com.briozing.automation.utils;

import com.briozing.automation.factory.Log4JFactory;
import org.apache.log4j.Logger;
import org.testng.Assert;

/**
 * @author KohitijDas
 */
public class AppAssert {

    private static Logger logger = Log4JFactory.getLogger("AssertLogger");

    static {
        logger = Log4JFactory.getLogger("AssertLogger");
    }

    public static void assertEqual(String actVal, String exVal, String message) {
        if (message != null) {
            logger.info(message + " Actual :[" + actVal + "], expected :[" + exVal + "]");
            Assert.assertEquals(actVal, exVal, message);
        } else {
            logger.info("Actual :[" + actVal + "], expected :[" + exVal + "]");
            Assert.assertEquals(actVal, exVal);
        }
    }

    public static void assertEqual(String actVal, String exVal) {
        assertEqual(actVal, exVal, null);
    }

    public static void assertTrue(Boolean condition, String message) {
        if (message != null) {
            logger.info(message + " Condition :[" + condition + "]");
            Assert.assertTrue(condition, message);
        } else {
            logger.info("Condition" + condition + "]");
            Assert.assertTrue(condition);
        }
    }

    public static void assertTrue(Boolean condition) {
        assertTrue(condition, null);
    }


    public static void assertEqual(int actVal, int exVal, String message) {
        if (message != null) {
            logger.info(message + " Actual :[" + actVal + "], expected :[" + exVal + "]");
            Assert.assertEquals(actVal, exVal, message);
        } else {
            logger.info("Actual :[" + actVal + "], expected :[" + exVal + "]");
            Assert.assertEquals(actVal, exVal);
        }
    }

    public static void assertEqual(int actVal, int exVal) {
        assertEqual(actVal, exVal, null);
    }

    public static void assertEqual(Boolean actVal, Boolean exVal, String message) {
        if (message != null) {
            logger.info(message + " Actual :[" + actVal + "], expected :[" + exVal + "]");
            Assert.assertEquals(actVal, exVal, message);
        } else {
            logger.info("Actual :[" + actVal + "], expected :[" + exVal + "]");
            Assert.assertEquals(actVal, exVal);
        }
    }

    public static void assertEqual(Boolean actVal, Boolean exVal) {
        assertEqual(actVal, exVal, null);
    }
}
