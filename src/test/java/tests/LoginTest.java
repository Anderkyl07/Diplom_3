package tests;

import io.restassured.response.Response;
import org.junit.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginViaMainPageButton() {
        String testEmail = getUniqueEmail();
        Response response = api.UserAPI.createUser(testEmail, data.TestData.VALID_PASSWORD, data.TestData.VALID_NAME);
        String accessToken = api.UserAPI.getAccessToken(response);

        pages.MainPage mainPage = new pages.MainPage(driver);
        mainPage.open()
                .clickLoginAccountButton();

        pages.LoginPage loginPage = new pages.LoginPage(driver);
        loginPage.login(testEmail, data.TestData.VALID_PASSWORD);

        mainPage.checkUserIsLoggedIn();

        api.UserAPI.deleteUser(accessToken);
    }

    @Test
    public void testLoginViaPersonalAccountButton() {
        String testEmail = getUniqueEmail();
        Response response = api.UserAPI.createUser(testEmail, data.TestData.VALID_PASSWORD, data.TestData.VALID_NAME);
        String accessToken = api.UserAPI.getAccessToken(response);

        pages.MainPage mainPage = new pages.MainPage(driver);
        mainPage.open();

        pages.Header header = new pages.Header(driver);
        header.clickPersonalAccount();

        pages.LoginPage loginPage = new pages.LoginPage(driver);
        loginPage.login(testEmail, data.TestData.VALID_PASSWORD);

        mainPage.checkUserIsLoggedIn();

        api.UserAPI.deleteUser(accessToken);
    }

    @Test
    public void testLoginViaRegistrationForm() {
        String testEmail = getUniqueEmail();
        Response response = api.UserAPI.createUser(testEmail, data.TestData.VALID_PASSWORD, data.TestData.VALID_NAME);
        String accessToken = api.UserAPI.getAccessToken(response);

        pages.MainPage mainPage = new pages.MainPage(driver);
        mainPage.open()
                .clickLoginAccountButton();

        pages.LoginPage loginPage = new pages.LoginPage(driver);
        loginPage.clickRegisterLink();

        pages.RegistrationPage registrationPage = new pages.RegistrationPage(driver);
        registrationPage.clickLoginLink();

        loginPage.login(testEmail, data.TestData.VALID_PASSWORD);

        mainPage.checkUserIsLoggedIn();

        api.UserAPI.deleteUser(accessToken);
    }

    @Test
    public void testLoginViaPasswordRecoveryForm() {
        String testEmail = getUniqueEmail();
        Response response = api.UserAPI.createUser(testEmail, data.TestData.VALID_PASSWORD, data.TestData.VALID_NAME);
        String accessToken = api.UserAPI.getAccessToken(response);

        pages.MainPage mainPage = new pages.MainPage(driver);
        mainPage.open()
                .clickLoginAccountButton();

        pages.LoginPage loginPage = new pages.LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        pages.RegistrationPage recoveryPage = new pages.RegistrationPage(driver);
        recoveryPage.clickLoginLink();

        pages.LoginPage newLoginPage = new pages.LoginPage(driver);
        newLoginPage.login(testEmail, data.TestData.VALID_PASSWORD);

        mainPage.checkUserIsLoggedIn();

        api.UserAPI.deleteUser(accessToken);
    }
}