package UITest.functionalityTests;

import Data.UserData;
import Pages.ProfilePage;
import Pages.SignInPage;
import UITest.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static Utils.Constans.*;
import static org.testng.Assert.assertEquals;

public class SignInTest extends BaseTest {
    private SignInPage signin = PageFactory.initElements(getWebDriver(), SignInPage.class);


    @BeforeMethod
    public void openingBeforeTests() {
        signin.open();
    }
    @BeforeMethod(alwaysRun = true)
    public void logBeforeMethod(Method m, Object[] p){
        Reporter.log("test start " + m.getName(),true);
    }


    @AfterMethod(alwaysRun = true)
    public void logAfteMethod(Method m, Object[] p){
        Reporter.log("test finished",true) ;
    }

    @Test
    public void signInSuccess() {
        UserData user = new UserData("alina.berestenko@sandbx.co", "123456Qwe");
        signin.loginAs(user);
        signin.isElementPresent(5, signin.logOutButton);
        signin.logOutButton.click();
    }

    @Test
    public void assertEnteringPassword() throws InterruptedException {
        UserData user = new UserData("", "123456Qwe");
        String password = user.getPassword();
        signin.type(signin.passwordField, password);
        signin.showPasswordButton.click();
        signin.isElementPresent(5, signin.showenPasswordField);
        assertEquals(signin.showenPasswordField.getAttribute("value"), password);
    }
}
