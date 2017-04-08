package Utilities;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomWait {
    /*
    * Custom ExpectedCondition, which will cause the driver to wait until the specified element is visible
    * Parameters | element: specified element that the driver will wait until it's visible
    */
    public static ExpectedCondition<Boolean> visibilityOfElement(final WebElement element) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver input) {
                try {
                    return element.isDisplayed();
                }catch(NoSuchElementException e) {
                    return false;
                }catch(StaleElementReferenceException e1) {
                    return false;
                }
            }
        };
    }

}
