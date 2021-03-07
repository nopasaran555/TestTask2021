import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleResultsPage {
    private WebDriver driver;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchTxtBox;
    @FindBy(xpath = "//img[@alt='Google']")
    private WebElement logoIcon;

    public GoogleResultsPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean isSearchResultContains(String searchWords){
        boolean isContains = false;

        WebElement resultsArea = driver.findElement(By.xpath("//div[@id='res']//div[@id='search']"));

        List<WebElement> allResults = resultsArea.findElements(By.xpath("//a//span[contains(text(), '" + searchWords + "')]"));

        if(allResults.size() > 0){
            isContains = true;
        }

        return isContains;
    }

    public String getSearchTextBoxTitle(){
        Actions action = new Actions(driver);
        action.moveToElement(searchTxtBox).perform();
        String output = searchTxtBox.getAttribute("title");
        return output;
    }

    public void clickOnLogo() {
        logoIcon.click();
    }

}
