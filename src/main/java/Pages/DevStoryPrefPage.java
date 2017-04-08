package Pages;

import Utilities.CustomWait;
import Utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DevStoryPrefPage {
    public WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how= How.ID, using="slug-text") private WebElement customNameField;
    @FindBy(how=How.CSS, using="a[class*='btn button save js-save']") private WebElement makePublicButton;
    @FindBy(how=How.CSS, using="a[class*='btn button _outline delete js-delete']") private WebElement deleteButton;
    @FindBy(how=How.CSS, using="div[class*='error error-text dno'][style*='display: block;']") private WebElement linkInvalidText;

    public DevStoryPrefPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    /*
    * Fills-in the customName field with invalid characters,
    * and clicks the "Make Public" button
    *
    * If the customName field is already filled-in,
    * the customNameDelete() method is first called, in order
    * to have this field cleared
    */
    public void customNameInvalid() {
        wait.until(CustomWait.visibilityOfElement(customNameField));
        if(Utilities.isAttributePresent(customNameField, "value")) {
            customNameDelete();
        }
        customNameField.sendKeys("*&^$%*Invalid Name*#$#$");
        makePublicButton.click();
        wait.until(CustomWait.visibilityOfElement(linkInvalidText));
    }

    /*
    * Asserts that the text "This link is invalid" is displayed
    * if an invalid name is entered in
    */
    public void assertCustomNameInvalid() {
        Assert.assertTrue(linkInvalidText.getText().equals("This link is invalid"));
    }

    /*
    * Fills-in the customName field with valid characters,
    * and clicks the "Make Public" button
    *
    * If the customName field is already filled-in,
    * the customNameDelete() method is first called, in order
    * to have this field cleared
    */
    public void customNameValid() {
        wait.until(CustomWait.visibilityOfElement(customNameField));
        if(Utilities.isAttributePresent(customNameField, "value")) {
            customNameDelete();
        }
        customNameField.sendKeys("JaredAutoDemo");
        makePublicButton.click();
        wait.until(CustomWait.visibilityOfElement(customNameField));
    }

    /*
    * Asserts that the text "This link is invalid" is not displayed
    * if a valid name is entered in
    */
    public void assertCustomNameValid() {
        Assert.assertFalse(Utilities.isElementPresent(driver, By.cssSelector("a[class*='error error-text dno']")));
    }

    /*
    * Clicks the Delete button, which deletes the
    * text in the customName field
    *
    * If no text is present, the customNameValid() method is
    * called, in order to fill-in the customName field
    */
    public void customNameDelete() {
        wait.until(CustomWait.visibilityOfElement(customNameField));
        if(!Utilities.isAttributePresent(customNameField, "value")) {
            customNameValid();
            Utilities.sleep(2000);
        }
        deleteButton.click();
        Utilities.sleep(2000);
        wait.until(CustomWait.visibilityOfElement(customNameField));
    }

    /*
    * Asserts that the name is deleted, by checking if the
    * "value" attribute of the customNameField is empty
    */
    public void assertCustomNameDelete() {
        Assert.assertTrue(customNameField.getAttribute("value").isEmpty());
    }
}

