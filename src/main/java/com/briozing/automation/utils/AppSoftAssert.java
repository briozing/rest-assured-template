package com.briozing.automation.utils;

import com.briozing.automation.factory.Log4JFactory;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;

/**
 * @author KohitijDas
 */
@Getter
public class AppSoftAssert {

    SoftAssert softAssert = null;

    private Logger logger = Log4JFactory.getLogger("Soft Assert LOGGER");

    @SuppressWarnings("unused")
    private AppSoftAssert() {
    }

    public AppSoftAssert(SoftAssert softAssert) {
        this.softAssert = softAssert;
    }

    public void assertEqual(String actVal, String exVal, String message) {
        if (message != null) {
            logger.info(message + " Actual :[" + actVal + "], expected :[" + exVal + "]");
            softAssert.assertEquals(actVal, exVal, message);
        } else {
            logger.info("Actual :[" + actVal + "], expected :[" + exVal + "]");
            softAssert.assertEquals(actVal, exVal);
        }
    }

    public void assertEqual(String actVal, String exVal) {
        assertEqual(actVal, exVal, null);
    }

    public void assertTrue(Boolean condition, String message) {
        if (message != null) {
            logger.info(message + " Condition :[" + condition + "]");
            softAssert.assertTrue(condition, message);
        } else {
            logger.info("Condition :[" + condition + "]");
            softAssert.assertTrue(condition);
        }
    }

    public void assertNull(Boolean condition) {
        assertEqual(condition, null);
    }

    public void assertTrue(Boolean condition) {
        assertTrue(condition, null);
    }

    public void assertEqual(int actVal, int exVal, String message) {
        if (message != null) {
            logger.info(message + " Actual :[" + actVal + "], expected :[" + exVal + "]");
            softAssert.assertEquals(actVal, exVal, message);
        } else {
            logger.info("Actual :[" + actVal + "], expected :[" + exVal + "]");
            softAssert.assertEquals(actVal, exVal);
        }
    }

    public void assertEqual(int actVal, int exVal) {
        assertEqual(actVal, exVal, null);
    }

    public void assertEqual(Boolean actVal, Boolean exVal, String message) {
        if (message != null) {
            logger.info(message + " Actual :[" + actVal + "], expected :[" + exVal + "]");
            softAssert.assertEquals(actVal, exVal, message);
        } else {
            logger.info("Actual :[" + actVal + "], expected :[" + exVal + "]");
            softAssert.assertEquals(actVal, exVal);
        }
    }

    public void assertEqual(Boolean actVal, Boolean exVal) {
        assertEqual(actVal, exVal, null);
    }

    public void assertEqual(int actVal, String exVal) {
        assertEqual(String.valueOf(actVal), exVal, null);
    }

    public void assertEqual(String actVal, int exVal) {
        assertEqual(actVal, String.valueOf(exVal), null);
    }

    public void assertEqual(long actVal, long exVal, String message) {
        if (message != null) {
            logger.info(message + " Actual :[" + actVal + "], expected :[" + exVal + "]");
            softAssert.assertEquals(actVal, exVal, message);
        } else {
            logger.info("Actual :[" + actVal + "], expected :[" + exVal + "]");
            softAssert.assertEquals(actVal, exVal);
        }
    }

    public void assertEqual(long actVal, long exVal) {
        assertEqual(actVal, exVal, null);
    }

    public void assertContains(String actVal, String exVal, String message) {
        if (message != null) {
            logger.info(message + " Actual :[" + actVal + "], expected :[" + exVal + "]");
            softAssert.assertTrue(actVal.contains(exVal), message);
        } else {
            logger.info("Actual :[" + actVal + "], expected :[" + exVal + "]");
            softAssert.assertTrue(actVal.contains(exVal));
        }
    }

    public void assertContains(String actVal, String exVal) {
        assertContains(actVal, exVal, null);
    }

    public void assertEqual(Double actVal, Double exVal, String message) {
        if (message != null) {
            logger.info(message + " Actual :[" + actVal + "], expected :[" + exVal + "]");
            softAssert.assertEquals(actVal, exVal, message);
        } else {
            logger.info("Actual :[" + actVal + "], expected :[" + exVal + "]");
            softAssert.assertEquals(actVal, exVal);
        }
    }

    public void assertEqual(Double actVal, Double exVal) {
        assertEqual(actVal, exVal, null);
    }

}