package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPageBeforeLogin {
    public WebDriver driver;

    @FindBy(how=How.CSS, using="a[class*='login-link'][rel='nofollow']") private WebElement loginButton;

    public MainPageBeforeLogin(WebDriver driver) {
        this.driver = driver;
    }

    /*
    * Clicks the Login button on the page that is first
    * displayed when the URL is pulled-up
    */
    public void clickLoginPage() {
        loginButton.click();
    }
}
