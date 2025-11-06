package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Header {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private By constructorLink = By.xpath("//a[.//p[text()='Конструктор']]");
    private By logo = By.xpath("//a[@href='/']");
    private By personalAccountLink = By.xpath("//a[@href='/account']");

    public Header(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }

    @Step("Клик на 'Конструктор'")
    public MainPage clickConstructorLink() {
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(constructorLink));
        js.executeScript("arguments[0].click();", link);
        return new MainPage(driver);
    }

    @Step("Клик на логотип")
    public MainPage clickLogo() {
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(logo));
        js.executeScript("arguments[0].click();", link);
        return new MainPage(driver);
    }

    @Step("Клик на 'Личный кабинет'")
    public LoginPage clickPersonalAccount() {
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(personalAccountLink));
        js.executeScript("arguments[0].click();", link);
        return new LoginPage(driver);
    }

    @Step("Проверить, что главная страница загружена")
    public Header checkMainPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(constructorLink));
        return this;
    }
}