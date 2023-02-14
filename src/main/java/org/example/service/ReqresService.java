package org.example.service;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.dto.RegisterAndLoginDTO;
import org.example.dto.UserDTO;

import static io.restassured.RestAssured.*;
import static org.apache.http.HttpStatus.*;

public class ReqresService {

    private final RequestSpecification requestSpecification;
    public ReqresService(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response listUsers() {
        return
                given()
                        .spec(requestSpecification)
                        .when()
                        .get("users?page=2")
                        .then()
                        .statusCode(SC_OK)
                        .extract()
                        .response();
    }

    public Response singleUser() {
        return
                given()
                    .spec(requestSpecification)
                .when()
                    .get("users/2")
                .then()
                    .statusCode(SC_OK)
                    .extract()
                    .response();
    }

    public void singleUserNotFound() {
        given()
            .spec(requestSpecification)
        .when()
            .get("users/23")
        .then()
            .statusCode(SC_NOT_FOUND)
            .extract()
            .response();
    }

    public Response listResource() {
        return
                given()
                        .spec(requestSpecification)
                        .when()
                        .get("unknown")
                        .then()
                        .statusCode(SC_OK)
                        .extract()
                        .response();
    }

    public Response singleResource() {
        return
                given()
                        .spec(requestSpecification)
                        .when()
                        .get("unknown/2")
                        .then()
                        .statusCode(SC_OK)
                        .extract()
                        .response();
    }

    public void singleResourceNotFound() {
        given()
                .spec(requestSpecification)
                .when()
                .get("unknown/23")
                .then()
                .statusCode(SC_NOT_FOUND)
                .extract()
                .response();
    }

    public Response createUser(UserDTO userDTO) {
        return
                given()
                        .spec(requestSpecification)
                        .when()
                        .body(userDTO)
                        .post("users")
                        .then()
                        .statusCode(SC_CREATED)
                        .extract()
                        .response();
    }

    public Response updateUserPut(UserDTO userDTO) {
        return
                given()
                        .spec(requestSpecification)
                        .when()
                        .body(userDTO)
                        .put("users/2")
                        .then()
                        .statusCode(SC_OK)
                        .extract()
                        .response();
    }

    public Response updateUserPatch(UserDTO userDTO) {
        return
                given()
                        .spec(requestSpecification)
                        .when()
                        .body(userDTO)
                        .patch("users/2")
                        .then()
                        .statusCode(SC_OK)
                        .extract()
                        .response();
    }

    public void deleteUser() {
        given()
                .spec(requestSpecification)
                .when()
                .delete("users/2")
                .then()
                .statusCode(SC_NO_CONTENT)
                .extract()
                .response();
    }

    public Response register(RegisterAndLoginDTO registerDTO, Integer statusCode) {
        return
                given()
                        .spec(requestSpecification)
                        .when()
                        .body(registerDTO)
                        .post("register")
                        .then()
                        .statusCode(statusCode)
                        .extract()
                        .response();
    }

    public Response login(RegisterAndLoginDTO loginDTO, Integer statusCode) {
        return
                given()
                        .spec(requestSpecification)
                        .when()
                        .body(loginDTO)
                        .post("login")
                        .then()
                        .statusCode(statusCode)
                        .extract()
                        .response();
    }

    public Response delayedResponse(RegisterAndLoginDTO loginDTO, Integer statusCode) {
        return
                given()
                        .spec(requestSpecification)
                        .when()
                        .body(loginDTO)
                        .get("users?delay=3")
                        .then()
                        .statusCode(SC_OK)
                        .extract()
                        .response();
    }


}
