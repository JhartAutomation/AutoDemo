package Pages;

import java.util.List;
import Utilities.CustomWait;
import Utilities.Utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class JobMatchPreferencePage {
    public WebDriver driver;
    private WebDriverWait wait;
    private Select select;
    private JavascriptExecutor js;

    @FindBy(how=How.XPATH, using="//*[@id='developer_types_chosen']/ul/li/input") private WebElement developerTypesField;
    @FindBy(how=How.XPATH, using="//*[@id='developer_types_chosen']/div/ul") private WebElement developerTypesOptions;
    @FindBy(how=How.ID, using="seniority-min") private WebElement minSeniorityDropDown;
    @FindBy(how=How.ID, using="seniority-max") private WebElement maxSeniorityDropDown;
    @FindBy(how=How.XPATH, using="//*[@id='industries_chosen']/ul/li/input") private WebElement industriesField;
    @FindBy(how=How.XPATH, using="//*[@id='industries_chosen']/div/ul") private WebElement industriesOptions;
    @FindBy(how=How.CSS, using="input[name='CompensationMin']") private WebElement compensationField;
    @FindBy(how=How.CSS, using="select[name='CompensationMinUnit']") private WebElement compensationCurrencyDropDown;
    @FindBy(how=How.CSS, using="input[name='LookingForFullTime'][type='checkbox']") private WebElement permanentCheckbox;
    @FindBy(how=How.CSS, using="input[name='LookingForContract'][type='checkbox']") private WebElement contractCheckbox;
    @FindBy(how=How.XPATH, using="//*[@id='mainbar']/form/div[3]/div[2]/div/div[1]/div[1]/input") private WebElement techWorkWithField;
    @FindBy(how=How.XPATH, using="//*[@id='mainbar']/form/div[3]/div[2]/div/div[2]/div/input") private WebElement techNotWorkWithField;
    @FindBy(how=How.CSS, using="input[name='PhoneNumber']") private WebElement phoneField;
    @FindBy(how=How.LINK_TEXT, using="Cancel") private WebElement cancelButton;
    @FindBy(how=How.CSS, using="input[class*='btn float-right'][value='Save'][type='submit']") private WebElement saveButton;
    @FindBy(how=How.XPATH, using="//*[@id='developer_types_chosen']/ul/li[1]/a") private WebElement developerTypesQualityAssurance;
    @FindBy(how=How.XPATH, using="//*[@id='industries_chosen']/ul/li[1]/a") private WebElement industriesSoftwareDevelopment;
    @FindBy(how=How.XPATH, using="//*[@id='mainbar']/form/div[3]/div[2]/div/div[1]/div[1]/span[1]") private WebElement techWorkWithOptionsSelected;
    @FindBy(how=How.XPATH, using="//*[@id='mainbar']/form/div[3]/div[2]/div/div[2]/div/span[1]") private WebElement techNotWorkWithOptionsSelected;

    public JobMatchPreferencePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        js = (JavascriptExecutor)driver;
    }

    /*
    * Fills-in all of the fields on the JobMatchPreferences page,
    * then clicks the Cancel button
    *
    * If the compensationField's "value" attribute is present, then
    * that means that the fields on this form are already filled-in,
    * thus the clearJobMatchPreferences() method is first called,
    * in order to clear the fields
    */
    public void updateJobMatchPreferencesCancel() {
        wait.until(CustomWait.visibilityOfElement(compensationField));

        if(Utilities.isAttributePresent(compensationField, "value")) {
            clearJobMatchPreferences();
        }

		/*
    	* Developer Types drop-down menu does not utilize "Select" element,
    	* thus I first add all of the element options to the developerTypesList list.
    	* Next, I use JavaScript to bring each element into view,
    	* until the element with the associated text "Quality Assurance" is located
    	*/
        developerTypesField.click();
        List<WebElement> developerTypesList = developerTypesOptions.findElements(By.tagName("li"));
        for(WebElement element: developerTypesList) {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            if(element.getText().equals("Quality Assurance")) {
                element.click();
            }
        }

        select = new Select(minSeniorityDropDown);
        select.selectByValue("30");

        select = new Select(maxSeniorityDropDown);
        select.selectByValue("50");

		/*
    	* Industries drop-down menu does not utilize "Select" element,
    	* thus I first add all of the element options to the industriesList list.
    	* Next, I use JavaScript to bring each element into view,
    	* until the element with the associated text "Software Development" is located
    	*/
        industriesField.click();
        List<WebElement> industriesList = industriesOptions.findElements(By.tagName("li"));
        for(WebElement element : industriesList) {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            if(element.getText().equals("Software Development")) {
                element.click();
            }
        }

        compensationField.sendKeys("65000");
        js.executeScript("arguments[0].click();", permanentCheckbox);
        js.executeScript("arguments[0].click();", contractCheckbox);
        techWorkWithField.sendKeys("Java" + Keys.SPACE);
        techWorkWithField.sendKeys("C#" + Keys.SPACE);
        techWorkWithField.sendKeys("Selenium WebDriver" + Keys.SPACE);
        techWorkWithField.sendKeys("Ready! API" + Keys.SPACE);
        techNotWorkWithField.sendKeys("I love all technology!" + Keys.SPACE);
        phoneField.sendKeys("4077603836");
        cancelButton.click();
        wait.until(CustomWait.visibilityOfElement(compensationField));
    }

    /*
    * Asserts that the fields are not filled-in with values (except for
    * the minSeniorityDropDown and maxSeniorityDropDown fields, as
    * this method asserts that these fields reflect the default values)
    */
    public void assertUpdateJobMatchPreferencesCancel() {
        Assert.assertFalse(Utilities.isElementPresent(developerTypesQualityAssurance));

        select = new Select(minSeniorityDropDown);
        Assert.assertFalse(select.getFirstSelectedOption().getText().equals("Mid-Level"));

        select = new Select(maxSeniorityDropDown);
        Assert.assertFalse(select.getFirstSelectedOption().getText().equals("Lead"));

        Assert.assertFalse(Utilities.isElementPresent(industriesSoftwareDevelopment));
        Assert.assertFalse(Utilities.isAttributePresent(compensationField, "value"));
        Assert.assertFalse(permanentCheckbox.isSelected());
        Assert.assertFalse(contractCheckbox.isSelected());
        Assert.assertFalse(Utilities.isElementPresent(techWorkWithField, By.tagName("span")));
        Assert.assertFalse(Utilities.isElementPresent(techNotWorkWithField, By.tagName("span")));
        Assert.assertFalse(Utilities.isAttributePresent(phoneField, "value"));
    }

    /*
    * Fills-in all of the fields on the JobMatchPreferences page,
    * then clicks the Save button
    *
    * If the compensationField's "value" attribute is present, then
    * that means that the fields on this form are already filled-in,
    * thus the clearJobMatchPreferences() method is first called,
    * in order to clear the fields
    */
    public void updateJobMatchPreferencesSave() {
        wait.until(CustomWait.visibilityOfElement(compensationField));

        if(Utilities.isAttributePresent(compensationField, "value")) {
            clearJobMatchPreferences();
        }

		/*
    	* Developer Types drop-down menu does not utilize "Select" element,
    	* thus I first add all of the element options to the developerTypesList list.
    	* Next, I use JavaScript to bring each element into view,
    	* until the element with the associated text "Quality Assurance" is located
    	*/
        developerTypesField.click();
        List<WebElement> developerTypesList = developerTypesOptions.findElements(By.tagName("li"));
        for(WebElement element: developerTypesList) {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            if(element.getText().equals("Quality Assurance")) {
                element.click();
            }
        }

        select = new Select(minSeniorityDropDown);
        select.selectByValue("30");

        select = new Select(maxSeniorityDropDown);
        select.selectByValue("50");

		/*
    	* Industries drop-down menu does not utilize "Select" element,
    	* thus I first add all of the element options to the industriesList list.
    	* Next, I use JavaScript to bring each element into view,
    	* until the element with the associated text "Software Development" is located
    	*/
        industriesField.click();
        List<WebElement> industriesList = industriesOptions.findElements(By.tagName("li"));
        for(WebElement element : industriesList) {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            if(element.getText().equals("Software Development")) {
                element.click();
            }
        }

        compensationField.sendKeys("65000");
        js.executeScript("arguments[0].click();", permanentCheckbox);
        js.executeScript("arguments[0].click();", contractCheckbox);
        techWorkWithField.sendKeys("Java" + Keys.SPACE);
        techWorkWithField.sendKeys("C#" + Keys.SPACE);
        techWorkWithField.sendKeys("Selenium WebDriver" + Keys.SPACE);
        techWorkWithField.sendKeys("Ready! API" + Keys.SPACE);
        techNotWorkWithField.sendKeys("I love all technology!" + Keys.SPACE);
        phoneField.sendKeys("4077603836");
        saveButton.click();
        wait.until(CustomWait.visibilityOfElement(compensationField));
    }

    /*
    * Asserts that the values filled-in the fields are saved
    */
    public void assertUpdateJobMatchPreferencesSave() {
        List<WebElement> list;

        Assert.assertTrue(developerTypesQualityAssurance.isDisplayed());

        select = new Select(minSeniorityDropDown);
        Assert.assertTrue(select.getFirstSelectedOption().getText().equals("Mid-Level"));

        select = new Select(maxSeniorityDropDown);
        Assert.assertTrue(select.getFirstSelectedOption().getText().equals("Lead"));

        list = techWorkWithOptionsSelected.findElements(By.cssSelector("*[class^='post-tag rendered-element']"));
        if(!list.isEmpty()) {
            for(WebElement element : list) {
                Assert.assertTrue(element.findElement(By.tagName("span")).isDisplayed());
            }
        }

        list = techNotWorkWithOptionsSelected.findElements(By.cssSelector("*[class^='post-tag rendered-element']"));
        if(!list.isEmpty()) {
            for(WebElement ele : list) {
                Assert.assertTrue(ele.findElement(By.tagName("span")).isDisplayed());
            }
        }

        Assert.assertTrue(phoneField.getAttribute("value").equals("4077603836"));
    }

    /*
    * Clears all of the fields on the JobMatchPreferences page,
    * then clicks the Cancel button
    *
    * If the compensationField's "value" attribute is not displayed,
    * then that means that the fields on this form are not filled-in,
    * thus the updateJobMatchPreferencesSave() method is first called,
    * in order to fill-in the fields on this form
    */
    public void clearJobMatchPreferences() {
        List<WebElement> list;

        wait.until(CustomWait.visibilityOfElement(compensationField));

        if(!Utilities.isAttributePresent(compensationField, "value")) {
            updateJobMatchPreferencesSave();
        }

        js.executeScript("arguments[0].click();", developerTypesQualityAssurance);

        select = new Select(minSeniorityDropDown);
        select.selectByValue("null");

        select = new Select(maxSeniorityDropDown);
        select.selectByValue("null");

        js.executeScript("arguments[0].click();", industriesSoftwareDevelopment);

        compensationField.clear();

        if(permanentCheckbox.isSelected()) {
            js.executeScript("arguments[0].click();", permanentCheckbox);
        }
        if(contractCheckbox.isSelected()) {
            js.executeScript("arguments[0].click();", contractCheckbox);
        }

		/*
    	* list: Contains all of the element options listed
    	* in the "Tech you want to work with" field
    	*
    	* Goal is to click the "x" element of each option, in order
    	* to delete the option from the "Tech you want to work with" field
    	*/
        list = techWorkWithOptionsSelected.findElements(By.cssSelector("span[class^='post-tag rendered-element']"));
        if(!list.isEmpty()) {
            for(WebElement element : list) {
                element.findElement(By.tagName("span")).click();
            }
        }

		/*
    	* list: Contains all of the element options listed
    	* in the "Tech you prefer not to work with" field
    	*
    	* Goal is to click the "x" element of each option, in order
    	* to delete the option from the "Tech you prefer not to work with" field
    	*/
        list = techNotWorkWithOptionsSelected.findElements(By.cssSelector("span[class^='post-tag rendered-element']"));
        if(!list.isEmpty()) {
            for(WebElement ele : list) {
                ele.findElement(By.tagName("span")).click();
            }
        }

        phoneField.clear();
        saveButton.click();
        wait.until(CustomWait.visibilityOfElement(compensationField));
    }

    /*
    * Asserts that the fields on the form are cleared (except for the minSeniorityDropDown
    * and maxSeniorityDropDown fields, as this method asserts that the default values
    * of these fields are selected)
    */
    public void assertClearJobMatchPreferences() {
        Assert.assertFalse(Utilities.isElementPresent(developerTypesQualityAssurance));

        select = new Select(minSeniorityDropDown);
        Assert.assertTrue(select.getFirstSelectedOption().getText().equals("Min seniority level"));

        select = new Select(maxSeniorityDropDown);
        Assert.assertTrue(select.getFirstSelectedOption().getText().equals("Max seniority level"));

        Assert.assertFalse(Utilities.isElementPresent(industriesSoftwareDevelopment));
        Assert.assertFalse(Utilities.isAttributePresent(compensationField, "value"));
        Assert.assertFalse(permanentCheckbox.isSelected());
        Assert.assertFalse(contractCheckbox.isSelected());
        Assert.assertFalse(Utilities.isElementPresent(techWorkWithField, By.tagName("span")));
        Assert.assertFalse(Utilities.isElementPresent(techNotWorkWithField, By.tagName("span")));
        Assert.assertFalse(Utilities.isAttributePresent(phoneField, "value"));
    }
}
