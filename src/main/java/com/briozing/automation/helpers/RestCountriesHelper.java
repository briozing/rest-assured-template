package com.briozing.automation.helpers;

import com.briozing.automation.common.Configuration;
import com.briozing.automation.factory.Log4JFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

/**
 * @author KohitijDas
 */
public class RestCountriesHelper {

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());

    private RequestSpecification requestSpecification;

    public RestCountriesHelper() {
        requestSpecification = with()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(Configuration.apiServer);
    }

    public Response getAllCountries() {
        final Response response = given(requestSpecification)
                .get("/all");
        //logger.info(response.prettyPrint());
        response.prettyPrint();
        return response;
    }

    public Response getCountryByName(String country) {
        final Response response = given(requestSpecification)
                .pathParam("country-name", country)
                .get("/name/{country-name}");
        logger.info(response.print());
        return response;
    }

    public Response getCountryByFullName(String country) {
        final Response response = given(requestSpecification)
                .pathParam("country-name", country)
                .queryParam("fullText", "true")
                .get("/name/{country-name}");
        logger.info(response.print());
        return response;
    }

}
