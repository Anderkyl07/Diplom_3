package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    private String testEmail;

    @Before
    public void setUp() {
        super.setUp();
        testEmail = getUniqueEmail();
    }

    @Test
    public void testSuccessfulRegistration() {
        pages.MainPage mainPage = new pages.MainPage(driver);
        mainPage.open()
                .clickLoginAccountButton();

        pages.LoginPage loginPage = new pages.LoginPage(driver);
        loginPage.clickRegisterLink();

        pages.RegistrationPage registrationPage = new pages.RegistrationPage(driver);
        registrationPage.registerUser("Test User", testEmail, data.TestData.VALID_PASSWORD);

        // После регистрации должны быть перенаправлены на логин - проверяем это
        loginPage.checkLoginPageLoaded();
    }

    @Test
    public void testRegistrationWithShortPasswordShowsError() {
        pages.MainPage mainPage = new pages.MainPage(driver);
        mainPage.open()
                .clickLoginAccountButton();

        pages.LoginPage loginPage = new pages.LoginPage(driver);
        loginPage.clickRegisterLink();

        pages.RegistrationPage registrationPage = new pages.RegistrationPage(driver);
        registrationPage.registerUser("Test User", testEmail, data.TestData.SHORT_PASSWORD);

        // Проверяем что ошибка отображается (не перенаправляет на другую страну)
        registrationPage.checkPasswordErrorDisplayed();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }
}