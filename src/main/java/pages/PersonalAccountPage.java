package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PersonalAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By nameField = By.xpath("//input[@name='Name']");
    private By emailField = By.xpath("//input[@name='name' and @type='text']");
    private By passwordField = By.xpath("//input[@type='password']");
    private By logoutButton = By.xpath("//button[text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Клик на кнопку 'Выход'")
    public LoginPage clickLogoutButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

        return new LoginPage(driver);
    }

    @Step("Проверить загрузку личного кабинета")
    public PersonalAccountPage checkPersonalAccountLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        return this;
    }

    @Step("Проверить успешный выход")
    public LoginPage checkLogoutSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.checkLoginPageLoaded();
    }
}