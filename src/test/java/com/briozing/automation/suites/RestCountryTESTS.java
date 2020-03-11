package com.briozing.automation.suites;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.helpers.RestCountriesHelper;
import com.briozing.automation.models.CountryDetailsDTO;
import com.briozing.automation.utils.AppAssert;
import com.briozing.automation.utils.MainUtils;
import com.briozing.automation.utils.TestSteps;
import com.briozing.automation.utils.TestValidationHelper;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RestCountryTESTS {

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());

    private RestCountriesHelper restCountriesHelper;
    private TestValidationHelper validationHelper;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        restCountriesHelper = new RestCountriesHelper();
        validationHelper = new TestValidationHelper();
    }

    @Test(groups = {"smoke"})
    public void verify_get_all_countries() {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_ALL_COUNTRIES.name(), true);
            validateTest(testSteps);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting all countries");
        }
    }

    @Test(groups = {"smoke"})
    public void verify_get_country_by_name() {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_COUNTRY_BY_NAME.name(), true);
            validateTest(testSteps);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting country by name");
        }
    }

    @Test(groups = {"smoke"})
    public void verify_get_country_by_fullname() {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_COUNTRY_BY_FULL_NAME.name(), true);
            validateTest(testSteps);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting country by fullName");
        }
    }

    private void validateTest(Map<String, Boolean> testSteps) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_GET_ALL_COUNTRIES.name()) && testSteps.get(TestSteps.STEP_GET_ALL_COUNTRIES.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_ALL_COUNTRIES.name());
            final CountryDetailsDTO[] response = restCountriesHelper.getAllCountries(200)
                    .getBody().as(CountryDetailsDTO[].class);
            validationHelper.verify_get_all_countries(response);
        }
        if (null != testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_NAME.name()) && testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_NAME.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_COUNTRY_BY_NAME.name());
            final CountryDetailsDTO[] response = restCountriesHelper.getCountryByName("Australia", 200)
                    .getBody().as(CountryDetailsDTO[].class);
            validationHelper.verify_get_country_by_name(response);
        }
        if (null != testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_FULL_NAME.name()) && testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_FULL_NAME.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_COUNTRY_BY_FULL_NAME.name());
            final CountryDetailsDTO[] response = restCountriesHelper.getCountryByFullName("India", 200)
                    .getBody().as(CountryDetailsDTO[].class);
            validationHelper.verify_get_country_by_name(response);
        }
    }
}
