package tests;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

public class PersonalAccountTest extends BaseTest {
    private String testEmail;
    private String accessToken;

    @Test
    public void testNavigateToPersonalAccount() {
        testEmail = getUniqueEmail();
        Response createResponse = api.UserAPI.createUser(testEmail, data.TestData.VALID_PASSWORD, data.TestData.VALID_NAME);
        accessToken = api.UserAPI.getAccessToken(createResponse);

        pages.MainPage mainPage = new pages.MainPage(driver);
        mainPage.open()
                .clickLoginAccountButton();

        pages.LoginPage loginPage = new pages.LoginPage(driver);
        loginPage.login(testEmail, data.TestData.VALID_PASSWORD);

        pages.Header header = new pages.Header(driver);
        header.clickPersonalAccount();

        pages.PersonalAccountPage personalAccountPage = new pages.PersonalAccountPage(driver);
        personalAccountPage.checkPersonalAccountLoaded();
    }

    @After
    public void deleteTestUser() {
        if (accessToken != null) {
            api.UserAPI.deleteUser(accessToken);
        }
    }
}