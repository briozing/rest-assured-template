package com.briozing.automation.utils;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.models.CountryDetailsDTO;
import com.briozing.automation.models.CurrencyDTO;
import org.apache.log4j.Logger;

public class TestValidationHelper {

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());

    public TestValidationHelper() {
    }

    public void verify_get_all_countries(CountryDetailsDTO[] actualResponse) {

        int count = actualResponse.length;
        logger.info("Total Countries: " + count);
        AppAssert.assertTrue(count == 250, "List of countries is equal to 250");
        for (CountryDetailsDTO country : actualResponse) {
            logger.info("Country: " + country.getName());
            logger.info("Capital: " + country.getCapital());
            AppAssert.assertTrue(country.getName() != null, "Country name is not null");
        }
    }

    public void verify_get_country_by_name(CountryDetailsDTO[] actualResponse) {

        for (CountryDetailsDTO country : actualResponse) {
            logger.info("Country Name: " + country.getName());
            AppAssert.assertTrue(country.getName() != null, "Country Name is not null");
            for (CurrencyDTO currency : country.getCurrencies()) {
                logger.info("code: " + currency.getCode());
                AppAssert.assertTrue(currency.getCode() != null, "Currency Code is not null");
                logger.info("name: " + currency.getName());
                AppAssert.assertTrue(currency.getName() != null, "Currency Name is not null");
                logger.info("Symbol: " + currency.getSymbol());
                AppAssert.assertTrue(currency.getSymbol() != null, "Currency Symbol is not null");
            }
        }
    }
}
