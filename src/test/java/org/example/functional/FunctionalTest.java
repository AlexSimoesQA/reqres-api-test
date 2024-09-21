package org.example.functional;

import io.restassured.response.Response;
import org.example.dataProvider.ReqresDataProvider;
import org.example.dto.RegisterAndLoginDTO;
import org.example.dto.UserDTO;
import org.example.base.BaseTest;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.*;
import static org.example.assertions.ReqresAssertions.*;

public class FunctionalTest extends BaseTest {

    @Test
    public void listUsersTest() {
        Response response = reqresService.listUsers();
        assertListUsers(response);
    }
    @Test
    public void singleUserTest() {
        Response response = reqresService.singleUser("2", SC_OK);
        assertSingleUser(response);
    }

    @Test
    public void singleUserNotFoundTest() {
        reqresService.singleUser("23", SC_NOT_FOUND);
    }

    @Test
    public void listResourceTest() {
        Response response = reqresService.listResource();
        assertListResource(response);
    }

    @Test
    public void singleResourceTest() {
        Response response = reqresService.singleResource("2", SC_OK);
        assertSingleResource(response);
    }

    @Test
    public void singleResourceNotFoundTest() {
        reqresService.singleResource("23", SC_NOT_FOUND);
    }

    @Test(dataProvider = "creatingUser", dataProviderClass = ReqresDataProvider.class)
    public void createUserTest(UserDTO userDTO) {
        Response response = reqresService.createUser(userDTO);
        assertCreateUser(response, userDTO);
    }

    @Test(dataProvider = "updateUser", dataProviderClass = ReqresDataProvider.class)
    public void updatePutTest(UserDTO userDTO) {
        Response response = reqresService.updateUserPut(userDTO);
        assertUpdate(response, userDTO);
    }

    @Test(dataProvider = "updateUser", dataProviderClass = ReqresDataProvider.class)
    public void updatePatchTest(UserDTO userDTO) {
        Response response = reqresService.updateUserPatch(userDTO);
        assertUpdate(response, userDTO);
    }

    @Test
    public void deleteTest() {
        reqresService.deleteUser();
    }

    @Test(dataProvider = "registerSuccessful", dataProviderClass = ReqresDataProvider.class)
    public void registerSuccessfulTest(RegisterAndLoginDTO registerDTO) {
        Response response = reqresService.register(registerDTO, SC_OK);
        assertRegister(response);
    }

    @Test(dataProvider = "registerUnsuccessful", dataProviderClass = ReqresDataProvider.class)
    public void registerUnsuccessfulTest(RegisterAndLoginDTO registerDTO) {
        Response response = reqresService.register(registerDTO, SC_BAD_REQUEST);
        assertMessageError(response);
    }

    @Test(dataProvider = "loginSuccessful", dataProviderClass = ReqresDataProvider.class)
    public void loginSuccessfulTest(RegisterAndLoginDTO loginDTO) {
        Response response = reqresService.login(loginDTO, SC_OK);
        assertLogin(response);
    }

    @Test(dataProvider = "loginUnsuccessful", dataProviderClass = ReqresDataProvider.class)
    public void loginUnsuccessfulTest(RegisterAndLoginDTO loginDTO) {
        Response response = reqresService.login(loginDTO, SC_BAD_REQUEST);
        assertMessageError(response);
    }

    @Test
    public void delayedResponseTest() {
        Response response = reqresService.delayedResponse();
        assertDelayedResponse(response);
    }

}
