package UITest.functionalityTests;

import Data.PasswordData;
import Data.UserData;
import Pages.ProfilePage;
import Pages.SignInPage;
import UITest.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static Utils.Constans.*;
import static org.testng.Assert.assertEquals;

public class ProfileTest extends BaseTest {
    private ProfilePage profile = PageFactory.initElements(getWebDriver(), ProfilePage.class);

    @BeforeMethod(alwaysRun = true)
    public void logBeforeMethod(Method m, Object[] p){
        Reporter.log("test start " + m.getName(),true);
    }


    @AfterMethod(alwaysRun = true)
    public void logAfteMethod(Method m, Object[] p){
        Reporter.log("test finished",true) ;
    }


    @BeforeMethod
    public void openingBeforeTests() {
        profile.open();
        profile.isElementPresent(5, profile.usernameText);
    }

    @BeforeClass
    public void signInBeforeTests() {
        SignInPage signin = PageFactory.initElements(getWebDriver(), SignInPage.class);
        signin.open();
        UserData user = new UserData("alina.berestenko@sandbx.co", "123456Qwe");
        signin.loginAs(user);
    }

    @AfterClass
    public void logOutAfterTests() {
        ProfilePage profile = PageFactory.initElements(getWebDriver(), ProfilePage.class);
        profile.logOutButton.click();
    }

    @Test
    public void checkDataInProfileSuccess() {
        profile.isElementPresent(5, profile.usernameText);
        assertEquals("alina.berestenko", profile.usernameText.getText());
        assertEquals("alina.berestenko@sandbx.co", profile.emailText.getText());
    }

    @Test
    public void editPersonalInfoSuccess() {
        profile.editProfileButton.click();
        UserData user = new UserData("alina.berestenko1","","", "");
        profile.editPersonalInfo(user);
        profile.isElementPresent(5, profile.usernameText);
        assertEquals("alina.berestenko1", profile.usernameText.getText());

        profile.editProfileButton.click();
        UserData userReturned = new UserData("alina.berestenko","","", "");
        profile.editPersonalInfo(userReturned);
        profile.isElementPresent(5, profile.usernameText);
        assertEquals("alina.berestenko", profile.usernameText.getText());
    }

    @Test
    public void changedPasswordWithAllFieldsSuccess() throws InterruptedException {
        PasswordData passwordData = new PasswordData("123456Qwe", "123456Qwe", "123456Qwe");
        profile.isElementPresent(5, profile.currentPasswordField);
        profile.changePassword(passwordData);
        profile.confirmChangingPasswordButton.click();
        profile.isElementPresent(5, profile.successButton);
        assertEquals(profile.successButton.getText(), "SUCCESS");
    }
}
