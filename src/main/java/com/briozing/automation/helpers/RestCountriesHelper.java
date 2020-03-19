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

    public Response getCountryByAlphacode(String alphacode,int status){
        final Response response = given(requestSpecification)
                .pathParam("alphacode-name", alphacode)
                .get("/alpha/{alphacode-name}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response getCountryByCapital(String capital, int status) {
        final Response response = given(requestSpecification)
                .pathParam("capital-name", capital)
                .get("/capital/{capital-name}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response getCountryByCurrencyCodes(String currencycode, int status) {
        final Response response = given(requestSpecification)
                .pathParam("currency-code-name", currencycode)
                .get("/currency/{currency-code-name}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response getCountryByLanguageCodes(String languagecode, int status){
        final Response response = given(requestSpecification)
                .pathParam("language-code-name", languagecode)
                .get("/lang/{language-code-name}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response getCountryByCallingCodes(String callingcode, int status) {
        final Response response = given(requestSpecification)
                .pathParam("calling-code", callingcode)
                .get("/callingcode/{calling-code}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response getCountryByRegion(String region, int status) {
        final Response response = given(requestSpecification)
                .pathParam("region-name", region)
                .get("/region/{region-name}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response getCountryByRegionalBloc(String regionalbloc, int status) {
        final Response response = given(requestSpecification)
                .pathParam("regional-bloc", regionalbloc)
                .get("/regionalbloc/{regional-bloc}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }
}
