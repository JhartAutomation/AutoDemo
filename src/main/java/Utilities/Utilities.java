package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utilities {
    /*
    * Verifies that a WebElement is not displayed
    * Parameters | driver: WebDriver where the element is located
    * Parameters | locator: By object that is verified if present
    */
    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        }catch(NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        }catch(NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementPresent(WebElement referenceElement, By locator) {
        try {
            referenceElement.findElement(locator);
            return true;
        }catch(NoSuchElementException e) {
            return false;
        }
    }

    /*
    * Verifies that a WebElement's attribute is present / not null
    * Parameters | element: Associated WebElement of the attribute
    * Parameters | attribute: Attribute that you want to verify is present / not null
    */
    public static boolean isAttributePresent(WebElement element, String attribute) {
        String value = element.getAttribute(attribute);
        if(value.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
