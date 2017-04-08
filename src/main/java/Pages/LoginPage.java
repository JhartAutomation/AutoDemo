package Pages;

import Utilities.CustomWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    public WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how=How.ID, using="email") private WebElement email;
    @FindBy(how=How.ID, using="password") private WebElement password;
    @FindBy(how=How.ID, using="submit-button") private WebElement loginSubmitButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    /*
    * Fills-in the email and password fields on the Login page,
    * then clicks the Submit button
    *
    * Parameters | email: User email
    * Parameters | password: User password
    */
    public void login(String email, String password) {
        wait.until(CustomWait.visibilityOfElement(this.email));
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        loginSubmitButton.click();
    }
}
