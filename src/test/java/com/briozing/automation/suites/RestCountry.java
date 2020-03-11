package com.briozing.automation.suites;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.helpers.RestCountriesHelper;
import com.briozing.automation.utils.AppAssert;
import com.briozing.automation.utils.MainUtils;
import com.briozing.automation.utils.TestSteps;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RestCountry {

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());

    private RestCountriesHelper restCountriesHelper;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        restCountriesHelper = new RestCountriesHelper();
    }

    @Test(groups = {"smoke"})
    public void verify_get_all_countries() throws Exception {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_ALL_COUNTRIES.name(), true);
            validateTest(testSteps);
        } catch (Exception ex) {
            AppAssert.assertTrue(false, "Failure getting all countries");
            ex.printStackTrace();
            logger.info(ex);
        }
    }

    @Test(groups = {"smoke"})
    public void verify_get_country_by_name() throws Exception {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_COUNTRY_BY_NAME.name(), true);
            validateTest(testSteps);
        } catch (Exception ex) {
            AppAssert.assertTrue(false, "Failure getting country by name");
            ex.printStackTrace();
            logger.info(ex);
        }
    }

    @Test(groups = {"smoke"})
    public void verify_get_country_by_fullname() throws Exception {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_COUNTRY_BY_FULL_NAME.name(), true);
            validateTest(testSteps);
        } catch (Exception ex) {
            AppAssert.assertTrue(false, "Failure getting country by fullName");
            ex.printStackTrace();
            logger.info(ex);
        }
    }

    private void validateTest(Map<String, Boolean> testSteps) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_GET_ALL_COUNTRIES.name()) && testSteps.get(TestSteps.STEP_GET_ALL_COUNTRIES.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_ALL_COUNTRIES.name());
            restCountriesHelper.getAllCountries();
        }
        if (null != testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_NAME.name()) && testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_NAME.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_COUNTRY_BY_NAME.name());
            restCountriesHelper.getCountryByName("Australia");
        }
        if (null != testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_FULL_NAME.name()) && testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_FULL_NAME.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_COUNTRY_BY_FULL_NAME.name());
            restCountriesHelper.getCountryByFullName("India");
        }
    }
}
