package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @Before
    @Step("Запуск браузера")
    public void setUp() {

        String browser = System.getProperty("browser", "chrome");

        if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
            driver = new FirefoxDriver(options);
        } else {

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
    }

    @After
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected String getUniqueEmail() {
        return "testuser_" + System.currentTimeMillis() + "@example.com";
    }
}