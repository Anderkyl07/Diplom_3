package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By makeBurgerHeader = By.xpath("//h1[text()='Соберите бургер']");
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]");
    private final By placeOrderButton = By.xpath("//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25)); // Увеличили до 25 секунд
    }

    public MainPage open() {
        driver.get("https://stellarburgers.education-services.ru/");

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(makeBurgerHeader),
                ExpectedConditions.visibilityOfElementLocated(placeOrderButton)
        ));
        return this;
    }

    public LoginPage clickLoginAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginAccountButton)).click();
        return new LoginPage(driver);
    }

    public MainPage checkConstructorIsLoaded() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(makeBurgerHeader),
                ExpectedConditions.visibilityOfElementLocated(placeOrderButton)
        ));
        return this;
    }

    public MainPage checkUserIsLoggedIn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        return this;
    }


    public MainPage clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
        return this;
    }

    public MainPage clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
        return this;
    }

    public MainPage clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
        return this;
    }

    public boolean isBunsTabActive() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab))
                .getText().contains("Булки");
    }

    public boolean isSaucesTabActive() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab))
                .getText().contains("Соусы");
    }

    public boolean isFillingsTabActive() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab))
                .getText().contains("Начинки");
    }
}