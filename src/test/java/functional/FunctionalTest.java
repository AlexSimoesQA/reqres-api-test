package functional;

import io.restassured.response.Response;
import org.example.dto.RegisterAndLoginDTO;
import org.example.dto.UserDTO;
import org.example.utils.BaseTest;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.*;
import static org.example.dao.ReqresDAO.*;

public class FunctionalTest extends BaseTest {

    @Test
    public void listUsersTest() {
        Response response = reqresService.listUsers();
        validateListUsers(response);
    }
    @Test
    public void singleUserTest() {
        Response response = reqresService.singleUser("2", SC_OK);
        validateSingleUser(response);
    }

    @Test
    public void singleUserNotFoundTest() {
        reqresService.singleUser("23", SC_NOT_FOUND);
    }

    @Test
    public void listResourceTest() {
        Response response = reqresService.listResource();
        validateListResource(response);
    }

    //ToDo: Realizar o restante das validações
    @Test
    public void singleResourceTest() {
        Response response = reqresService.singleResource("2", SC_OK);
    }

    @Test
    public void singleResourceNotFoundTest() {
        reqresService.singleResource("23", SC_NOT_FOUND);
    }

    @Test
    public void createUserTest(UserDTO userDTO) {
        Response response = reqresService.createUser(userDTO);
    }

    @Test
    public void updatePutTest(UserDTO userDTO) {
        Response response = reqresService.updateUserPut(userDTO);
    }

    @Test
    public void updatePatchTest(UserDTO userDTO) {
        Response response = reqresService.updateUserPatch(userDTO);
    }

    @Test
    public void deleteTest() {
        reqresService.deleteUser();
    }

    @Test
    public void registerTest(RegisterAndLoginDTO registerDTO) {
        Response response = reqresService.register(registerDTO, SC_OK);
    }

    @Test
    public void registerUnsuccessfulTest(RegisterAndLoginDTO registerDTO) {
        Response response = reqresService.register(registerDTO, SC_BAD_REQUEST);
    }

    @Test
    public void loginTest(RegisterAndLoginDTO loginDTO) {
        Response response = reqresService.login(loginDTO, SC_OK);
    }

    @Test
    public void loginUnsuccessfulTest(RegisterAndLoginDTO loginDTO) {
        Response response = reqresService.login(loginDTO, SC_BAD_REQUEST);
    }

    @Test
    public void delayedResponseTest() {
        Response response = reqresService.delayedResponse();
    }

}
