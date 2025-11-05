package tests;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NavigationTest extends BaseTest {
    private String testEmail;
    private String accessToken;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        super.setUp(); // вызываем родительский setUp
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Создаем тестового пользователя
        testEmail = getUniqueEmail();
        Response createResponse = api.UserAPI.createUser(testEmail, data.TestData.VALID_PASSWORD, data.TestData.VALID_NAME);
        accessToken = api.UserAPI.getAccessToken(createResponse);
    }

    @After
    public void tearDown() {
        // Удаляем тестового пользователя
        if (accessToken != null) {
            api.UserAPI.deleteUser(accessToken);
        }
        super.tearDown(); // вызываем родительский tearDown
    }

    @Test
    public void testNavigateFromAccountToConstructorViaConstructorLink() {
        // Логин через UI
        pages.MainPage mainPage = new pages.MainPage(driver);
        mainPage.open()
                .clickLoginAccountButton();

        pages.LoginPage loginPage = new pages.LoginPage(driver);
        loginPage.login(testEmail, data.TestData.VALID_PASSWORD);

        // Переход в ЛК
        pages.Header header = new pages.Header(driver);
        header.clickPersonalAccount();

        // Проверяем что ЛК загрузился
        pages.PersonalAccountPage personalAccountPage = new pages.PersonalAccountPage(driver);
        personalAccountPage.checkPersonalAccountLoaded();

        // Переход обратно в конструктор через ссылку "Конструктор"
        header.clickConstructorLink();

        // Проверяем что конструктор загружен
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[text()='Соберите бургер']")
        ));
    }

    @Test
    public void testNavigateFromAccountToConstructorViaLogo() {
        // Логин через UI
        pages.MainPage mainPage = new pages.MainPage(driver);
        mainPage.open()
                .clickLoginAccountButton();

        pages.LoginPage loginPage = new pages.LoginPage(driver);
        loginPage.login(testEmail, data.TestData.VALID_PASSWORD);

        // Переход в ЛК
        pages.Header header = new pages.Header(driver);
        header.clickPersonalAccount();

        // Проверяем что ЛК загрузился
        pages.PersonalAccountPage personalAccountPage = new pages.PersonalAccountPage(driver);
        personalAccountPage.checkPersonalAccountLoaded();

        // Переход обратно в конструктор через логотип
        header.clickLogo();

        // Проверяем что конструктор загружен
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[text()='Соберите бургер']")
        ));
    }
}