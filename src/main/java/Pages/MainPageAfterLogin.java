package Pages;

import Utilities.CustomWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageAfterLogin {
    public WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how=How.CSS, using="a[href*='/users/'][class*='my-profile']") private WebElement profileHomePageButton;

    public MainPageAfterLogin(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    /*
    * Clicks the Profile button / icon on the
    * page that is displayed after the user first
    * logs in
    */
    public void clickHomeProfilePage() {
        wait.until(CustomWait.visibilityOfElement(profileHomePageButton));
        profileHomePageButton.click();
    }
}
