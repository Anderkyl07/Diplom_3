package tests;

import org.junit.Test;

public class ConstructorTest extends BaseTest {

    @Test
    public void testNavigateToBunsSection() {
        pages.MainPage mainPage = new pages.MainPage(driver);
        mainPage.open();

        pages.ConstructorPage constructorPage = new pages.ConstructorPage(driver);
        constructorPage.clickBunsTab()
                .checkBunsSectionActive();
    }

    @Test
    public void testNavigateToSaucesSection() {
        pages.MainPage mainPage = new pages.MainPage(driver);
        mainPage.open();

        pages.ConstructorPage constructorPage = new pages.ConstructorPage(driver);
        constructorPage.clickSaucesTab()
                .checkSaucesSectionActive();
    }

    @Test
    public void testNavigateToFillingsSection() {
        pages.MainPage mainPage = new pages.MainPage(driver);
        mainPage.open();

        pages.ConstructorPage constructorPage = new pages.ConstructorPage(driver);
        constructorPage.clickFillingsTab()
                .checkFillingsSectionActive();
    }
}