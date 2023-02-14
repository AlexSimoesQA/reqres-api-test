package org.example.requestSpecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationBuild {

    private static final String BASEURI = "https://reqres.in";
    private static final String BASEPATH = "/api/";

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setConfig(
                        new RestAssuredConfig()
                                .sslConfig(
                                        new SSLConfig().relaxedHTTPSValidation()
                                )
                )
                .setContentType(ContentType.JSON)
                .setBaseUri(BASEURI)
                .setBasePath(BASEPATH)
                .log(LogDetail.ALL)
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}
