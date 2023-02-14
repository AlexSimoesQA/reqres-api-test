package org.example.service;

import org.example.requestSpecification.RequestSpecificationBuild;

public class ReqresServiceFactory {

    public static ReqresService buildService() {
        return new ReqresService(RequestSpecificationBuild.getRequestSpec());
    }
}
