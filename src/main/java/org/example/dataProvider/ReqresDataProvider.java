package org.example.dataProvider;

import org.example.dataFactory.ReqresDataFactory;
import org.testng.annotations.DataProvider;

public class ReqresDataProvider {

    private final ReqresDataFactory reqresDataFactory = new ReqresDataFactory();

    @DataProvider()
    public Object[][] creatingUser() {
        return new Object[][] {
                {reqresDataFactory.setUser("morpheus", "leader")}
        };
    }

    @DataProvider()
    public Object[][] updateUser() {
        return new Object[][] {
                {reqresDataFactory.setUser("morpheus", "zion resident")}
        };
    }

    @DataProvider()
    public Object[][] registerSuccessful() {
        return new Object[][] {
                {reqresDataFactory.setRegister("eve.holt@reqres.in", "pistol")}
        };
    }

    @DataProvider()
    public Object[][] registerUnsuccessful() {
        return new Object[][] {
                {reqresDataFactory.setRegister("sydney@fife", null)}
        };
    }

    @DataProvider()
    public Object[][] loginSuccessful() {
        return new Object[][] {
                {reqresDataFactory.setRegister("eve.holt@reqres.in", "cityslicka")}
        };
    }

    @DataProvider()
    public Object[][] loginUnsuccessful() {
        return new Object[][] {
                {reqresDataFactory.setRegister("peter@klaven", null)}
        };
    }
}
