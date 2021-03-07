import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TaskTwo {

    public ChromeDriver chromeDriver;

    @BeforeTest
    public void beforeTest() {
        String pathToChromeDriver = String.format("%s\\Tools\\chromedriver89.exe", System.getProperty("user.dir") );
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
    }

    @AfterTest
    public void afterTest() {
        chromeDriver.quit();
    }

    @Test(priority = 1, testName = "Searching by person name in Google")
    public void searchByPersonName() throws InterruptedException {
        String searchWords = "Иванов, Иван Иванович";
        chromeDriver.get("https://google.com");
        GoogleStartPage googleStartPage = new GoogleStartPage(chromeDriver);
        googleStartPage.searchInGoogle(searchWords);
        GoogleResultsPage googleResultsPage = new GoogleResultsPage(chromeDriver);
        boolean result = googleResultsPage.isSearchResultContains(searchWords);
        Assert.assertTrue(result);
    }

    @Test(priority = 2, testName = "Searching by company name in Google")
    public void searchByCompanyName() throws InterruptedException {
        String searchWords = "Libertex";
        chromeDriver.get("https://google.com");
        GoogleStartPage googleStartPage = new GoogleStartPage(chromeDriver);
        googleStartPage.searchInGoogle(searchWords);
        GoogleResultsPage googleResultsPage = new GoogleResultsPage(chromeDriver);
        boolean result = googleResultsPage.isSearchResultContains(searchWords);
        Assert.assertTrue(result);
    }

    @Test(priority = 3, testName = "Checking tooltip on search field in Google")
    public void mouseHoverOnSearchTextBox() throws InterruptedException {
        String searchWords = "Libertex";
        String expectedValue = "Поиск";
        chromeDriver.get("https://google.com");
        GoogleStartPage googleStartPage = new GoogleStartPage(chromeDriver);
        googleStartPage.searchInGoogle(searchWords);
        GoogleResultsPage googleResultsPage = new GoogleResultsPage(chromeDriver);
        String result = googleResultsPage.getSearchTextBoxTitle();
        Assert.assertEquals(result, expectedValue);
    }

    @Test(priority = 4, testName = "Clicking on logo in Google")
    public void clickOnLogo() throws InterruptedException {
        String searchWords = "Libertex";
        String expectedValue = "";
        chromeDriver.get("https://google.com");
        GoogleStartPage googleStartPage = new GoogleStartPage(chromeDriver);
        googleStartPage.searchInGoogle(searchWords);
        GoogleResultsPage googleResultsPage = new GoogleResultsPage(chromeDriver);
        googleResultsPage.clickOnLogo();
        String result = googleStartPage.getSearchTextBoxValue();
        Assert.assertEquals(result, expectedValue);
    }


}
