package Pages;

import Utilities.CustomWait;
import Utilities.Utilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EditProfilePage {
    public WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @FindBy(how=How.CSS, using="input[name='DisplayName'][type='text']") private WebElement displayNameField;
    @FindBy(how=How.CSS, using="input[name='Location'][type='text']") private WebElement locationField;
    @FindBy(how=How.ID, using="Title") private WebElement titleField;
    @FindBy(how=How.ID, using="wmd-input") private WebElement aboutField;
    @FindBy(how=How.CSS, using="p") private WebElement aboutFieldText;
    @FindBy(how=How.CSS, using="input[value*='Save Profile']") private WebElement saveButton;
    @FindBy(how=How.ID, using="cancel") private WebElement cancelButton;
    @FindBy(how=How.CLASS_NAME, using="val-textemphasis") private WebElement saveMessage;
    @FindBy(how=How.XPATH, using="//*[@id='side-menu']/ul/li[1]/ul/li[3]/a") private WebElement jobMatchPreferencePage;
    @FindBy(how=How.XPATH, using="//*[@id='side-menu']/ul/li[1]/ul/li[2]/a") private WebElement devStoryPrefPage;
    @FindBy(how=How.XPATH, using="//*[@id='tabs']/a[4]") private WebElement editProfileSettingsButton;

    public EditProfilePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        js = (JavascriptExecutor)driver;
    }

    /*
    * Clicks the "Developer Story Preferences" button on the left
    * tool bar of the "Edit Profile & Settings" tab
    *
    * Navigates to the "Developer Story Preferences" page
    */
    public void clickDevStoryPrefPage() {
        wait.until(CustomWait.visibilityOfElement(devStoryPrefPage));
        devStoryPrefPage.click();
    }

    /*
    * Clicks the "Job Match Preferences" button on the left
    * tool bar of the "Edit Profile & Settings" tab
    *
    * Navigates to the "Job Match Preferences" page
    */
    public void clickJobMatchPreferencePage() {
        wait.until(CustomWait.visibilityOfElement(jobMatchPreferencePage));
        jobMatchPreferencePage.click();
    }

    /*
    * Fills-in all of the fields on the EditProfile page,
    * then clicks the Cancel button
    *
    * If the locationField's value attribute is not empty, that means
    * that the fields on the form are already filled-in, thus the
    * clearProfileSettings() method is called to first clear the fields
    */
    public void updateProfileSettingsCancel() {
        wait.until(CustomWait.visibilityOfElement(displayNameField));

        if(!locationField.getAttribute("value").isEmpty()) {
            clearProfileSettings();
        }

        displayNameField.clear();
        displayNameField.sendKeys("123JhartTest456");
        locationField.sendKeys("Orlando, FL, United States");
        titleField.sendKeys("Jared's Automation Demo");
        aboutField.sendKeys("This is an automation Demo by Jared Hart!");
        cancelButton.click();

        wait.until(CustomWait.visibilityOfElement(editProfileSettingsButton));
        js.executeScript("arguments[0].click();", editProfileSettingsButton);
        wait.until(CustomWait.visibilityOfElement(displayNameField));
    }

    /*
    * Asserts that the fields are not saved with the
    * filled-in values, after the Cancel button is clicked
    */
    public void assertUpdateProfileSettingsCancel() {
        Assert.assertFalse(displayNameField.getAttribute("value").equals("123JhartTest456"));
        Assert.assertTrue(locationField.getAttribute("value").isEmpty());
        Assert.assertTrue(titleField.getAttribute("value").isEmpty());
        Assert.assertFalse(Utilities.isElementPresent(aboutFieldText));
    }

    /*
    * Fills-in all of the fields on the EditProfile page,
    * then clicks the Save button
    *
    * If the locationField's value attribute is not empty, that means
    * that the fields on the form are already filled-in, thus the
    * clearProfileSettings() method is called to first clear the fields
    */
    public void updateProfileSettingsSave() {
        wait.until(CustomWait.visibilityOfElement(displayNameField));

        if(!locationField.getAttribute("value").isEmpty()) {
            clearProfileSettings();
        }

        displayNameField.clear();
        displayNameField.sendKeys("123JhartTest456");
        locationField.sendKeys("Orlando, FL, United States");
        titleField.sendKeys("Jared's Automation Demo");
        aboutField.sendKeys("This is an automation Demo by Jared Hart!");
        Utilities.sleep(2000); //Sleep needed here because the automation is too fast, and throws an error
        js.executeScript("arguments[0].click();", saveButton);
        wait.until(CustomWait.visibilityOfElement(saveMessage));
    }

    /*
    * Asserts that the filled-in values are saved
    */
    public void assertUpdateProfileSettingsSave() {
        Assert.assertTrue(saveMessage.getText().equals("Your profile has been saved successfully."));
        Assert.assertTrue(displayNameField.getAttribute("value").equals("123JhartTest456"));
        Assert.assertTrue(locationField.getAttribute("value").equals("Orlando, FL, United States"));
        Assert.assertTrue(titleField.getAttribute("value").equals("Jared's Automation Demo"));
        Assert.assertTrue(aboutFieldText.getText().equals("This is an automation Demo by Jared Hart!"));
    }

    /*
    * Clears all of the fields on the EditProfile page,
    * then clicks the Save button
    */
    public void clearProfileSettings() {
        wait.until(CustomWait.visibilityOfElement(displayNameField));

        if(locationField.getAttribute("value").isEmpty()) {
            updateProfileSettingsSave();
        }

        displayNameField.clear();
        displayNameField.sendKeys("User123AutoDemo123");
        locationField.clear();
        titleField.clear();
        aboutField.clear();
        Utilities.sleep(2000); //Sleep needed here because the automation is too fast, and throws an error
        js.executeScript("arguments[0].click();", saveButton);
        wait.until(CustomWait.visibilityOfElement(saveMessage));
        driver.navigate().refresh();
        wait.until(CustomWait.visibilityOfElement(displayNameField));
    }

    /*
    * Asserts that the fields are cleared
    */
    public void assertClearProfileSettings() {
        Assert.assertTrue(displayNameField.getAttribute("value").equals("User123AutoDemo123"));
        Assert.assertTrue(locationField.getAttribute("value").isEmpty());
        Assert.assertTrue(titleField.getAttribute("value").isEmpty());
        Assert.assertFalse(Utilities.isElementPresent(aboutFieldText));
    }
}

