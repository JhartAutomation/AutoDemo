package Pages;

import Utilities.CustomWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeProfilePage {
    public WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how=How.CSS, using="a[href*='/users/edit/'][data-shortcut='E']") private WebElement editProfileSettingsButton;

    public HomeProfilePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    /*
    * Clicks the "Edit Profile & Settings" button on
    * the ActivityProfile page
    */
    public void clickEditProfilePage() {
        wait.until(CustomWait.visibilityOfElement(editProfileSettingsButton));
        editProfileSettingsButton.click();
    }
}

