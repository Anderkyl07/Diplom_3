package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private By emailField = By.xpath("//input[@type='text']");
    private By passwordField = By.xpath("//input[@type='password']");
    private By loginButton = By.xpath("//button[text()='Войти']");
    private By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }

    @Step("Ввод email: {email}")
    public LoginPage enterEmail(String email) {
        WebElement field = wait.until(ExpectedConditions.presenceOfElementLocated(emailField));
        field.clear();
        field.sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage enterPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
        field.clear();
        field.sendKeys(password);
        return this;
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
        js.executeScript("arguments[0].click();", button);
    }

    @Step("Авторизация пользователя")
    public MainPage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
        return new MainPage(driver);
    }

    @Step("Клик на ссылку 'Зарегистрироваться'")
    public RegistrationPage clickRegisterLink() {
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(registerLink));
        js.executeScript("arguments[0].click();", link);
        return new RegistrationPage(driver);
    }

    @Step("Клик на ссылку 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(forgotPasswordLink));
        js.executeScript("arguments[0].click();", link);
    }

    @Step("Проверить, что страница логина загружена")
    public LoginPage checkLoginPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return this;
    }
}