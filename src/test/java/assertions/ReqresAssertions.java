package assertions;

import io.restassured.response.Response;
import org.example.dto.UserDTO;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.*;
import static org.example.utils.StaticValue.*;
import static org.testng.Assert.*;

public class ReqresAssertions {

    public static void assertListUsers(Response response) {
        String page = response.body().jsonPath().getString("page");
        String perPage = response.body().jsonPath().getString("per_page");
        String total = response.body().jsonPath().getString("total");
        String totalPages = response.body().jsonPath().getString("total_pages");

        String firstName0 = response.body().jsonPath().getString("data[0].first_name");
        List<Object> lastName = response.body().jsonPath().getList("data.last_name");
        List<Object> listOfLastName = List.of("Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell");
        List<Object> expectedEmails = List.of("michael.lawson@reqres.in",
                "lindsay.ferguson@reqres.in",
                "tobias.funke@reqres.in",
                "byron.fields@reqres.in",
                "george.edwards@reqres.in",
                "rachel.howell@reqres.in"
        );
        List<Object> email = response.body().jsonPath().getList("data.email");

        assertEquals(page, "2");
        assertEquals(perPage, "6");
        assertEquals(total, "12");
        assertEquals(totalPages, "2");

        assertEquals(firstName0, "Michael");
        assertEquals(lastName, listOfLastName);
        assertEquals(email, expectedEmails);
    }

    public static void assertSingleUser(Response response) {
        String id = response.body().jsonPath().getString("data.id");
        String email = response.body().jsonPath().getString("data.email");
        String firstName = response.body().jsonPath().getString("data.first_name");
        String lastName = response.body().jsonPath().getString("data.last_name");
        String avatar = response.body().jsonPath().getString("data.avatar");
        String url = response.body().jsonPath().getString("support.url");
        String text = response.body().jsonPath().getString("support.text");

        assertEquals(id, "2");
        assertEquals(email, "janet.weaver@reqres.in");
        assertEquals(firstName, "Janet");
        assertEquals(lastName, "Weaver");
        assertEquals(avatar, "https://reqres.in/img/faces/2-image.jpg");
        assertEquals(url, "https://reqres.in/#support-heading");
        assertEquals(text, "To keep ReqRes free, contributions towards server costs are appreciated!");
    }

    public static void assertListResource(Response response) {
        List<Object> data = response.body().jsonPath().getList("data");
        String page = response.body().jsonPath().getString("page");
        String perPage = response.body().jsonPath().getString("per_page");
        String total = response.body().jsonPath().getString("total");
        String totalPages = response.body().jsonPath().getString("total_pages");

        List<Object> id = response.body().jsonPath().getList("data.id");
        List<Object> expectedIds = List.of(1, 2, 3, 4, 5, 6);
        List<Object> name = response.body().jsonPath().getList("data.name");
        List<String> expectedNames = List.of("cerulean", "fuchsia rose", "true red", "aqua sky", "tigerlily", "blue turquoise");
        List<Object> year = response.body().jsonPath().getList("data.year");
        List<Object> expectedYears = List.of(2000, 2001, 2002, 2003, 2004, 2005);
        String color = response.body().jsonPath().getString("data[3].color");
        List<Object> pantoneValue = response.body().jsonPath().getList("data.pantone_value");
        List<Object> expectedPantoneValue = List.of("15-4020", "17-2031", "19-1664", "14-4811", "17-1456", "15-5217");

        assertNotNull(data);
        assertEquals(page, "1");
        assertEquals(perPage, "6");
        assertEquals(total, "12");
        assertEquals(totalPages, "2");

        assertEquals(id, expectedIds);
        assertEquals(name, expectedNames);
        assertEquals(year, expectedYears);
        assertEquals(color, "#7BC4C4");
        assertEquals(pantoneValue, expectedPantoneValue);
    }

    public static void assertSingleResource(Response response) {
        Integer id = response.body().jsonPath().get("data.id");
        String name = response.body().jsonPath().getString("data.name");
        Integer year = response.body().jsonPath().get("data.year");
        String color = response.body().jsonPath().getString("data.color");
        String pantoneValue = response.body().jsonPath().getString("data.pantone_value");
        String url = response.body().jsonPath().getString("support.url");
        String text = response.body().jsonPath().getString("support.text");

        assertEquals(id, 2);
        assertEquals(name, "fuchsia rose");
        assertEquals(year, 2001);
        assertEquals(color, "#C74375");
        assertEquals(pantoneValue, "17-2031");
        assertEquals(url, "https://reqres.in/#support-heading");
        assertEquals(text, "To keep ReqRes free, contributions towards server costs are appreciated!");
    }

    public static void assertCreateUser(Response response, UserDTO userDTO) {
        String name = response.body().jsonPath().getString("name");
        String job = response.body().jsonPath().getString("job");
        String id = response.body().jsonPath().getString("id");
        String createdAt = response.body().jsonPath().getString("createdAt");

        assertEquals(name, userDTO.name());
        assertEquals(job, userDTO.job());
        assertNotNull(id);
        assertEquals(createdAt.substring(0, 10), DATE_NOW);
    }

    public static void assertUpdate(Response response, UserDTO userDTO) {
        String name = response.body().jsonPath().getString("name");
        String job = response.body().jsonPath().getString("job");
        String updatedAt = response.body().jsonPath().getString("updatedAt");

        assertEquals(name, userDTO.name());
        assertEquals(job, userDTO.job());
        assertEquals(updatedAt.substring(0, 10), DATE_NOW);
    }

    public static void assertRegister(Response response) {
        Integer id = response.body().jsonPath().get("id");
        String token = response.body().jsonPath().getString("token");

        assertEquals(id, 4);
        assertEquals(token, "QpwL5tke4Pnpja7X4");
    }

    public static void assertMessageError(Response response) {
        String error = response.body().jsonPath().getString("error");

        assertEquals(error, "Missing password");
    }

    public static void assertLogin(Response response) {
        String token = response.body().jsonPath().getString("token");

        assertEquals(token, "QpwL5tke4Pnpja7X4");
    }

    public static void assertDelayedResponse(Response response) {
        await().atMost(5, TimeUnit.SECONDS).until(() -> !response.body().jsonPath().getList("data").isEmpty());

        Integer page = response.body().jsonPath().get("page");
        Integer perPage = response.body().jsonPath().get("per_page");
        Integer total = response.body().jsonPath().get("total");
        Integer totalPages = response.body().jsonPath().get("total_pages");

        List<Object> id = response.body().jsonPath().getList("data.id");
        List<Object> expectedIds = List.of(1, 2, 3, 4, 5, 6);
        List<Object> email = response.body().jsonPath().getList("data.email");
        List<String> expectedEmails = List.of("george.bluth@reqres.in",
                "janet.weaver@reqres.in",
                "emma.wong@reqres.in",
                "eve.holt@reqres.in",
                "charles.morris@reqres.in",
                "tracey.ramos@reqres.in"
        );
        List<Object> firstName = response.body().jsonPath().getList("data.first_name");
        List<Object> expectedFirstName = List.of("George", "Janet", "Emma", "Eve", "Charles", "Tracey");
        List<Object> lastName = response.body().jsonPath().getList("data.last_name");
        List<Object> expectedLastName = List.of("Bluth", "Weaver", "Wong", "Holt", "Morris", "Ramos");
        List<String> avatar = response.body().jsonPath().getList("data.avatar");
        List<String> expectedAvatar = List.of("https://reqres.in/img/faces/1-image.jpg",
                "https://reqres.in/img/faces/2-image.jpg",
                "https://reqres.in/img/faces/3-image.jpg",
                "https://reqres.in/img/faces/4-image.jpg",
                "https://reqres.in/img/faces/5-image.jpg",
                "https://reqres.in/img/faces/6-image.jpg"
        );
        String url = response.body().jsonPath().getString("support.url");
        String text = response.body().jsonPath().getString("support.text");

        assertEquals(page, 1);
        assertEquals(perPage, 6);
        assertEquals(total, 12);
        assertEquals(totalPages, 2);

        assertEquals(id, expectedIds);
        assertEquals(email, expectedEmails);
        assertEquals(firstName, expectedFirstName);
        assertEquals(lastName, expectedLastName);
        assertEquals(avatar, expectedAvatar);

        assertEquals(url, "https://reqres.in/#support-heading");
        assertEquals(text, "To keep ReqRes free, contributions towards server costs are appreciated!");
    }

}
