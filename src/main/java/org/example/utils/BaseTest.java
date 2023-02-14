package org.example.utils;

import io.restassured.RestAssured;
import org.example.requestSpecification.RequestSpecificationBuild;
import org.example.service.ReqresService;
import org.example.service.ReqresServiceFactory;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    private static ReqresService reqresService;

    @BeforeSuite
    public static void setUp() {
        reqresService = ReqresServiceFactory.buildService();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
