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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public void verify_get_all_countries(String input) {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_ALL_COUNTRIES.name(), true);
            validateTest(testSteps, input);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting all countries");
        }
    }

    @DataProvider(name = "country-name-dp")
    public Object[][] countryNameDP() {

        return new Object[][]{
                {"Australia"}, {"India"}
        };
    }

    @DataProvider(name = "country-code-dp")
    public Object[][] countryCodeDP() {

        return new Object[][]{
                {"ala"}, {"ax"}
        };
    }

    @DataProvider(name = "capital-name-dp")
    public Object[][] capitalNameDP() {

        return new Object[][]{
                {"new delhi"}, {"kabul"}, {"Mariehamn"}, {"Tirana"}
        };
    }

    @DataProvider(name = "currency-code-dp")
    public Object[][] currencyCodeNameDP() {

        return new Object[][]{
                {"cop", new String[]{"Colombia"}}, {"eur",new String[]{"Lithuania"}}, {"inr",new String[]{"India","Bhutan"}}
        };
    }

    @Test(groups = {"smoke"}, dataProvider = "country-name-dp")
    public void verify_get_country_by_name(String input) {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_COUNTRY_BY_NAME.name(), true);
            validateTest(testSteps, input);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting country by name");
        }
    }

    @Test(groups = {"smoke"}, dataProvider = "country-name-dp")
    public void verify_get_country_by_fullname(String input) {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_COUNTRY_BY_FULL_NAME.name(), true);
            validateTest(testSteps, input);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting country by fullName");
        }
    }

    @Test(groups = {"smoke", "alpha"}, dataProvider = "country-code-dp")
    public void verify_get_country_by_alphacode(String alphacode) {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_COUNTRY_BY_ALPHACODE.name(), true);
            validateAlphacodeTest(testSteps, alphacode);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting country by ALPHACODE");
        }
    }

    @Test(groups = {"smoke", "capital"}, dataProvider = "capital-name-dp")
    public void verify_get_country_by_capital(String capitalName) {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            Map<String, String> expected = new HashMap<>();

            testSteps.put(TestSteps.STEP_GET_COUNTRY_BY_CAPITAL.name(), true);
            expected.put("capitalName", capitalName);
            validateCapitalTest(testSteps, capitalName, expected);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting country by CAPITAL");
        }
    }

    @Test(groups = {"smoke", "currency"}, dataProvider = "currency-code-dp")
    public void verify_get_country_by_currency(String currency, String[] countries) {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_COUNTRY_BY_CURRENCY.name(), true);

            Map<String, String[]> expected = new HashMap<>();

            expected.put("currency", new String[]{currency});

            expected.put("countries", countries);

            validateCurrencyTest(testSteps, currency, expected);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting country by currency codes");
        }
    }


    private void validateTest(Map<String, Boolean> testSteps, String input) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_GET_ALL_COUNTRIES.name()) && testSteps.get(TestSteps.STEP_GET_ALL_COUNTRIES.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_ALL_COUNTRIES.name());
            final CountryDetailsDTO[] response = restCountriesHelper.getAllCountries(200)
                    .getBody().as(CountryDetailsDTO[].class);
            validationHelper.verify_get_all_countries(response);
        }
        if (null != testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_NAME.name()) && testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_NAME.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_COUNTRY_BY_NAME.name());
            final CountryDetailsDTO[] response = restCountriesHelper.getCountryByName(input, 200)
                    .getBody().as(CountryDetailsDTO[].class);
            validationHelper.verify_get_country_by_name(response);
        }
        if (null != testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_FULL_NAME.name()) && testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_FULL_NAME.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_COUNTRY_BY_FULL_NAME.name());
            final CountryDetailsDTO[] response = restCountriesHelper.getCountryByFullName(input, 200)
                    .getBody().as(CountryDetailsDTO[].class);
            validationHelper.verify_get_country_by_name(response);
        }

    }


    private void validateCurrencyTest(Map<String, Boolean> testSteps, String currency, Map<String, String[]> expected) throws Exception {

        if (null != testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_CURRENCY.name()) && testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_CURRENCY.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_COUNTRY_BY_CURRENCY.name());
            final CountryDetailsDTO[] response = restCountriesHelper.getCountryByCurrencyCodes(currency, 200)
                    .getBody().as(CountryDetailsDTO[].class);
            validationHelper.verify_get_country_by_currency(response, expected);
        }
    }

    private void validateAlphacodeTest(Map<String, Boolean> testSteps, String alphacode) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_ALPHACODE.name()) && testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_ALPHACODE.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_COUNTRY_BY_ALPHACODE.name());
            final CountryDetailsDTO response = restCountriesHelper.getCountryByAlphacode(alphacode, 200)
                    .getBody().as(CountryDetailsDTO.class);
            validationHelper.verify_get_country_by_alphacode(response);
        }
    }

    private void validateCapitalTest(Map<String, Boolean> testSteps, String capitalName, Map<String, String> expected) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_CAPITAL.name()) && testSteps.get(TestSteps.STEP_GET_COUNTRY_BY_CAPITAL.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_COUNTRY_BY_CAPITAL.name());
            final CountryDetailsDTO[] response = restCountriesHelper.getCountryByCapital(capitalName, 200)
                    .getBody().as(CountryDetailsDTO[].class);
            validationHelper.verify_get_country_by_capital(response, expected);
        }
    }
}