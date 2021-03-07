import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class GoogleStartPage {
    private WebDriver driver;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchTxtBox;
    @FindBy(xpath = "//input[@name='btnK']")
    WebElement searchButton;

    public GoogleStartPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void searchInGoogle(String searchWords) throws InterruptedException {
        searchTxtBox.sendKeys(searchWords);
        Thread.sleep(4000); //explicit SLEEP here - we should not click on search button too fast - otherwise, Google will show 'I am not a robot' captcha
        searchButton.click();
    }

    public String getSearchTextBoxValue(){
        String output = searchTxtBox.getAttribute("value");
        return output;
    }


}
