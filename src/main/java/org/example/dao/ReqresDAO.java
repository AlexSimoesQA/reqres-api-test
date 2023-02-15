package org.example.dao;

import io.restassured.response.Response;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ReqresDAO {

    public static void validateListUsers(Response response) {
        String page = response.body().jsonPath().getString("page");
        String perPage = response.body().jsonPath().getString("per_page");
        String total = response.body().jsonPath().getString("total");
        String totalPages = response.body().jsonPath().getString("total_pages");

        String firstName0 = response.body().jsonPath().getString("data[0].first_name");
        List<Object> lastName = response.body().jsonPath().getList("data.last_name");
        List<Object> email = response.body().jsonPath().getList("data.email");

        assertThat(page, is("2"));
        assertThat(perPage, is("6"));
        assertThat(total, is("12"));
        assertThat(totalPages, is("2"));

        assertThat(firstName0, is("Michael"));
        assertThat(lastName, contains("Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell"));
        assertThat(email, hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "george.edwards@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "rachel.howell@reqres.in"));
    }
    public static void validateSingleUser(Response response) {
        String id = response.body().jsonPath().getString("data.id");
        String email = response.body().jsonPath().getString("data.email");
        String firstName = response.body().jsonPath().getString("data.first_name");
        String lastName = response.body().jsonPath().getString("data.last_name");
        String avatar = response.body().jsonPath().getString("data.avatar");
        String url = response.body().jsonPath().getString("support.url");
        String text = response.body().jsonPath().getString("support.text");

        assertThat(id, is("2"));
        assertThat(email, is("janet.weaver@reqres.in"));
        assertThat(firstName, is("Janet"));
        assertThat(lastName, is("Weaver"));
        assertThat(avatar, is("https://reqres.in/img/faces/2-image.jpg"));
        assertThat(url, is("https://reqres.in/#support-heading"));
        assertThat(text, is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    public static void validateListResource(Response response) {
        List<Object> data = response.body().jsonPath().getList("data");
        String page = response.body().jsonPath().getString("page");
        String perPage = response.body().jsonPath().getString("per_page");
        String total = response.body().jsonPath().getString("total");
        String totalPages = response.body().jsonPath().getString("total_pages");

        List<Object> id = response.body().jsonPath().getList("data.id");
        List<Object> name = response.body().jsonPath().getList("data.name");
        List<Object> year = response.body().jsonPath().getList("data.year");
        String color = response.body().jsonPath().getString("data[3].color");
        List<Object> pantoneValue = response.body().jsonPath().getList("data.pantone_value");

        assertThat(data, is(not(empty())));
        assertThat(page, is("1"));
        assertThat(perPage, is("6"));
        assertThat(total, is("12"));
        assertThat(totalPages, is("2"));

        assertThat(id, contains(1, 2, 3, 4, 5, 6));
        assertThat(name, hasItems("cerulean", "true red", "fuchsia rose", "aqua sky", "blue turquoise", "tigerlily"));
        assertThat(year, contains(2000, 2001, 2002, 2003, 2004, 2005));
        assertThat(color, is("#7BC4C4"));
        assertThat(pantoneValue, contains("15-4020", "17-2031", "19-1664", "14-4811", "17-1456", "15-5217"));

    }

    //ToDo: realizar o restante das validações
    public static void validateCreateUser(Response response) {
    }

    public static void validateUpdatePut(Response response) {
    }

    public static void validateRegister(Response response) {
    }

    public static void validateMessageError(Response response) {
    }

    public static void validateLogin(Response response) {
    }

    public static void validateDelayedResponse(Response response) {
    }

}
