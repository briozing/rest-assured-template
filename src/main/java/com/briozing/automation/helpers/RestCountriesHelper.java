package com.briozing.automation.helpers;

import com.briozing.automation.common.Configuration;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

/**
 * @author KohitijDas
 */
public class RestCountriesHelper {

    private RequestSpecification requestSpecification;

    public RestCountriesHelper() {
        requestSpecification = with()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(Configuration.apiServer);
    }

    public Response getAllCountries(int status) {
        final Response response = given(requestSpecification)
                .get("/all");
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response getCountryByName(String country, int status) {
        final Response response = given(requestSpecification)
                .pathParam("country-name", country)
                .get("/name/{country-name}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response getCountryByFullName(String country, int status) {
        final Response response = given(requestSpecification)
                .pathParam("country-name", country)
                .queryParam("fullText", "true")
                .get("/name/{country-name}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

}
