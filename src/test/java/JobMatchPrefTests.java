
import Pages.*;
import Utilities.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JobMatchPrefTests {
    private WebDriver driver;
    private String userEmail;
    private String userPassword;
    private MainPageBeforeLogin mainPageBeforeLogin;
    private LoginPage loginPage;
    private MainPageAfterLogin mainPageAfterLogin;
    private HomeProfilePage homeProfilePage;
    private EditProfilePage editProfilePage;
    private JobMatchPreferencePage jobMatchPreferencePage;

    @BeforeMethod
    public void getDriverInitializeObjects() {
        driver = BrowserFactory.startBrowser("chrome", "http://stackoverflow.com/");
        userEmail = "autodemojhart@gmail.com";
        userPassword = "123auto123";
        mainPageBeforeLogin = PageFactory.initElements(driver, MainPageBeforeLogin.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPageAfterLogin = PageFactory.initElements(driver, MainPageAfterLogin.class);
        homeProfilePage = PageFactory.initElements(driver, HomeProfilePage.class);
        editProfilePage = PageFactory.initElements(driver, EditProfilePage.class);
        jobMatchPreferencePage = PageFactory.initElements(driver, JobMatchPreferencePage.class);
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

	/*
     * Note: In the below tests, instead of placing the methods used
     * to navigate the website in the "@BeforeMethod" method, I placed
     * each of these methods inside each "@Test" method, in order
     * to make the tests more readable
     */

    @Test
    public void updateJobMatchPrefCancel() {
        mainPageBeforeLogin.clickLoginPage();
        loginPage.login(userEmail,userPassword);
        mainPageAfterLogin.clickHomeProfilePage();
        homeProfilePage.clickEditProfilePage();
        editProfilePage.clickJobMatchPreferencePage();
        jobMatchPreferencePage.updateJobMatchPreferencesCancel();
        jobMatchPreferencePage.assertUpdateJobMatchPreferencesCancel();
    }

    @Test
    public void updateJobMatchPrefSave() {
        mainPageBeforeLogin.clickLoginPage();
        loginPage.login(userEmail,userPassword);
        mainPageAfterLogin.clickHomeProfilePage();
        homeProfilePage.clickEditProfilePage();
        editProfilePage.clickJobMatchPreferencePage();
        jobMatchPreferencePage.updateJobMatchPreferencesSave();
        jobMatchPreferencePage.assertUpdateJobMatchPreferencesSave();
    }

    @Test
    public void clearJobMatchPreferences() {
        mainPageBeforeLogin.clickLoginPage();
        loginPage.login(userEmail,userPassword);
        mainPageAfterLogin.clickHomeProfilePage();
        homeProfilePage.clickEditProfilePage();
        editProfilePage.clickJobMatchPreferencePage();
        jobMatchPreferencePage.clearJobMatchPreferences();
        jobMatchPreferencePage.assertClearJobMatchPreferences();
    }
}