package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ConstructorPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private By bunsTab = By.xpath("//span[text()='Булки']");
    private By saucesTab = By.xpath("//span[text()='Соусы']");
    private By fillingsTab = By.xpath("//span[text()='Начинки']");
    private By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }

    @Step("Клик на таб 'Булки'")
    public ConstructorPage clickBunsTab() {
        WebElement tab = wait.until(ExpectedConditions.presenceOfElementLocated(bunsTab));
        js.executeScript("arguments[0].click();", tab);
        return this;
    }

    @Step("Клик на таб 'Соусы'")
    public ConstructorPage clickSaucesTab() {
        WebElement tab = wait.until(ExpectedConditions.presenceOfElementLocated(saucesTab));
        js.executeScript("arguments[0].click();", tab);
        return this;
    }

    @Step("Клик на таб 'Начинки'")
    public ConstructorPage clickFillingsTab() {
        WebElement tab = wait.until(ExpectedConditions.presenceOfElementLocated(fillingsTab));
        js.executeScript("arguments[0].click();", tab);
        return this;
    }

    @Step("Проверить, что раздел 'Булки' активен")
    public ConstructorPage checkBunsSectionActive() {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(activeTab, "Булки"));
        return this;
    }

    @Step("Проверить, что раздел 'Соусы' активен")
    public ConstructorPage checkSaucesSectionActive() {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(activeTab, "Соусы"));
        return this;
    }

    @Step("Проверить, что раздел 'Начинки' активен")
    public ConstructorPage checkFillingsSectionActive() {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(activeTab, "Начинки"));
        return this;
    }

    @Step("Проверить загрузку конструктора")
    public ConstructorPage checkConstructorPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
        return this;
    }
}