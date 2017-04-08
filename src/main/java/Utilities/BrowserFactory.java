package Utilities;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {
    private static WebDriver driver;

    /*
    * Initializes the WebDriver object
    * Parameters | browser: Web browser to be utilized
    * Parameters | url: Specified URL that the driver will navigate to
    */
    public static WebDriver startBrowser(String browser, String url) {
        if(browser.equalsIgnoreCase("chrome")) {
            if(SystemUtils.IS_OS_MAC_OSX) {
                System.setProperty("webdriver.chrome.driver", "src/chromedriver");
            }
            else if(SystemUtils.IS_OS_WINDOWS) {
                System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
            }
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.get(url);

        return driver;
    }
}
