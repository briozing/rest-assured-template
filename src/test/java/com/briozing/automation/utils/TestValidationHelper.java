package com.briozing.automation.utils;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.models.CountryDetailsDTO;
import com.briozing.automation.models.CurrencyDTO;
import com.briozing.automation.models.LanguageDTO;
import com.briozing.automation.models.RegionalBlocDTO;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

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
        logger.info("CallingCode: " + actualResponse[104].getCallingCodes().get(0).equalsIgnoreCase("91"));
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

    public void verify_get_country_by_capital(CountryDetailsDTO[] actualResponse, Map<String, String> expected) {
        logger.info("Country Name: " + actualResponse[0].getName());
        logger.info("Country capital: " + actualResponse[0].getCapital());
        AppAssert.assertTrue(actualResponse[0].getName() != null, "Country Name is not null");
        AppAssert.assertTrue(actualResponse[0].getCapital() != null, "Country capital is not null");
        AppAssert.assertTrue(actualResponse[0].getCapital().equalsIgnoreCase(expected.get("capitalName")));
    }

    public void verify_get_country_by_alphacode(CountryDetailsDTO actualResponse, Map<String, String> expected) {
        logger.info("Country Name: " + actualResponse.getName());
        logger.info("Alpha2code Name: " + actualResponse.getAlpha2Code());
        logger.info("Alpha3code Name: " + actualResponse.getAlpha3Code());
        AppAssert.assertTrue(actualResponse.getName() != null, "Country Name is not null");
        AppAssert.assertTrue(actualResponse.getAlpha2Code() != null, "Alpha2code Name is not null");
        AppAssert.assertTrue(actualResponse.getAlpha3Code() != null, "Alpha3code Name is not null");
//        String[] alphacodes=expected.get("alphacodes");
//        for(String alphacode: alphacodes){
//            boolean isPresent=false;
//            if(alphacode.equals(actualResponse.getAlpha2Code()))
//                isPresent=true;
//            else if(alphacode.equals(actualResponse.getAlpha3Code()))
//                isPresent=true;
//            AppAssert.assertTrue(isPresent);
//        }

        AppAssert.assertEqual(actualResponse.getAlpha2Code(),expected.get("alphacode2"));
        AppAssert.assertEqual(actualResponse.getAlpha3Code(),expected.get("alphacode3"));
    }

    public void verify_get_country_by_currency(CountryDetailsDTO[] actualResponse, Map<String, String[]> expected) {

        for (CountryDetailsDTO countryDetailsDTO : actualResponse) {
            logger.info("Country Name: " + countryDetailsDTO.getName());
            logger.info("Currency: " + countryDetailsDTO.getCurrencies());
            AppAssert.assertTrue(countryDetailsDTO.getName() != null, "Country Name is not null");
            AppAssert.assertTrue(countryDetailsDTO.getCurrencies() != null, "Currency is not null");
            String[] currencies = expected.get("currency");
            for (String currency : currencies) {
                boolean isPresent = false;
                for (CurrencyDTO currencyDTO : countryDetailsDTO.getCurrencies()) {
                    if (currency.equalsIgnoreCase(currencyDTO.getCode())) {
                        isPresent = true;
                        break;
                    }
                }
                AppAssert.assertTrue(isPresent);
            }
        }

        String[] countries = expected.get("countries");
        for (String country : countries) {
            boolean isPresent = false;
            for (CountryDetailsDTO countryDetailsDTO : actualResponse) {
                if (country.equalsIgnoreCase(countryDetailsDTO.getName())) {
                    isPresent = true;
                    break;
                }
            }
            AppAssert.assertTrue(isPresent);
        }
    }

    public void verify_get_country_by_language(CountryDetailsDTO[] actualResponse, Map<String, String[]> expected) {

        for (CountryDetailsDTO countryDetailsDTO : actualResponse) {
            logger.info("Country Name: " + countryDetailsDTO.getName());
            logger.info("Language: " + countryDetailsDTO.getLanguages());
            AppAssert.assertTrue(countryDetailsDTO.getName() != null, "Country Name is not null");
            AppAssert.assertTrue(countryDetailsDTO.getLanguages() != null, "Language is not null");
            String[] languages = expected.get("language");
            for (String language : languages) {
                boolean isPresent = false;
                for (LanguageDTO languageDTO : countryDetailsDTO.getLanguages()) {
                    if (language.equalsIgnoreCase(languageDTO.getIso639_1())) {
                        isPresent = true;
                        break;
                    }
                }
                AppAssert.assertTrue(isPresent);
            }
        }

        String[] countries = expected.get("countries");
        for (String country : countries) {
            boolean isPresent = false;
            for (CountryDetailsDTO countryDetailsDTO : actualResponse) {
                if (country.equalsIgnoreCase(countryDetailsDTO.getName())) {
                    isPresent = true;
                    break;
                }
            }
            AppAssert.assertTrue(isPresent);
        }
    }

    public void verify_get_country_by_callingcode(CountryDetailsDTO[] actualResponse, Map<String, String> expected) {
        logger.info("Country Name: " + actualResponse[0].getName());
        logger.info("Calling Code: " + actualResponse[0].getCallingCodes());
        AppAssert.assertTrue(actualResponse[0].getName() != null, "Country Name is not null");
        AppAssert.assertTrue(actualResponse[0].getCallingCodes() != null, "Calling code is not null");
        AppAssert.assertTrue(actualResponse[0].getCallingCodes().contains(expected.get("callingcode")));
    }

    public void verify_get_country_by_region(CountryDetailsDTO[] actualResponse) {
        for (CountryDetailsDTO country : actualResponse) {
            logger.info("Country: " + country.getName());
            logger.info("Capital: " + country.getCapital());
            logger.info("Region: " + country.getRegion());
            logger.info("Region: " + country.getSubRegion());
            AppAssert.assertTrue(country.getName() != null, "Country name is not null");
            AppAssert.assertTrue(country.getRegion() != null, "Region name is not null");
            AppAssert.assertTrue(country.getSubRegion() != null, "SubRegion name is not null");
        }
    }

    public void verify_get_country_by_regionalbloc(CountryDetailsDTO[] actualResponse) {
        for (CountryDetailsDTO countryDetailsDTO : actualResponse) {
            for (RegionalBlocDTO regionalBlocDTO : countryDetailsDTO.getRegionalBlocs()) {
                logger.info("Country: " + countryDetailsDTO.getName());
                logger.info("Regional Blocs Acronym: " + regionalBlocDTO.getAcronym());
                logger.info("Regional Blocs name: " + regionalBlocDTO.getName());
                AppAssert.assertTrue(countryDetailsDTO.getName() != null, "Country name is not null");
                AppAssert.assertTrue(regionalBlocDTO.getAcronym() != null, "Regional bloc acronym is not null");
                AppAssert.assertTrue(regionalBlocDTO.getName() != null, "Regional bloc name is not null");
            }
            }

    }

}
