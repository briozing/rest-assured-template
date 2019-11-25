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
public class ApiHelper {

    private RequestSpecification requestSpecification;

    public ApiHelper() {
        requestSpecification = with()
                .contentType(ContentType.JSON)
                .log().all()
                .header("API-VERSION", "1.0")
                .baseUri(Configuration.apiServer);
    }

    public Response getWithQueryParam(String params, String token) {
        final Response response = given(requestSpecification)
                .auth().oauth2(token)
                .queryParam("params", params)
                .get("/endpoint");
        response.prettyPrint();
        return response;
    }

    public Response getWithPathParam(String token, String param) {
        final Response response = given(requestSpecification)
                .auth().oauth2(token)
                .pathParam("path-param", param)
                .header("sample-header", "")
                .get("/endpoint/{path-param}/uri");
        response.prettyPrint();
        return response;
    }

}
