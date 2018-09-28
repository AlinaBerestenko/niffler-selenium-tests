package UITest.translationTests;

import Data.PasswordData;
import Data.UserData;
import Pages.ProfilePage;
import Pages.SignInPage;
import UITest.functionalityTests.ProfileTest;
import Utils.TestDataProviders;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import static Utils.Constans.*;
import static org.testng.Assert.assertEquals;

public class ProfileTranslationTests extends TranslationTest{
    private ProfilePage page = PageFactory.initElements(getWebDriver(), ProfilePage.class);



    String locale;
    Map<String, String> translation = null;

    public ProfileTranslationTests() throws IOException {
    }

    @Parameters({"locale"})
    @BeforeClass
    public String getLocale(@Optional("en") String localeFromParameters) {
        this.locale = localeFromParameters;
        return locale;
    }

    @BeforeClass
    public Map<String,String> getTranslationMap() {
        {
            try {
                translation = readTranslationFile(Locale.valueOf(locale));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return translation;
    }

    @BeforeClass
    public void openingBeforeTests() {
        open(locale, "sign-in");
        SignInPage signin = PageFactory.initElements(getWebDriver(), SignInPage.class);
        UserData user = new UserData("alina.berestenko@sandbx.co", "123456Qwe");
        signin.loginAs(user);
    }

    @AfterClass
    public void logOutAfterTests() {
        ProfilePage profile = PageFactory.initElements(getWebDriver(), ProfilePage.class);
        profile.logOutButton.click();
    }

    @BeforeMethod
    public void openBeforeTest() {
        open(locale, "profile");
        page.isElementPresent(5, page.copyLinkButton);
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
    public void openingProfilePage() {
        page.isElementPresent(5, page.newPasswordField);
        assertEquals(page.title.getText(), translation.get("PROFILE_H1"));
    }

    @Test
    public void checkCopyButtonSuccess() {
        page.isElementPresent(5, page.copyLinkButton);
        assertEquals(page.copyLinkButton.getText(), translation.get("COPY"));
        page.copyLinkButton.click();
        assertEquals(page.copyLinkButton.getText(), translation.get("COPIED").toUpperCase());
    }

    @Test
    public void changedPasswordWithAllFieldsSuccess() {
        assertEquals(page.confirmChangingPasswordButton.getText(), translation.get("CONFIRM_BUTTON"));
        PasswordData passwordData = new PasswordData("123456Qwe", "123456Qwe", "123456Qwe");
        page.changePassword(passwordData);
        page.confirmChangingPasswordButton.click();
        page.isElementPresent(5, page.successButton);
        assertEquals(page.successButton.getText(), translation.get("SUCCESS_BUTTON"));
    }

    @Test
    public void changedPasswordWithoutAnyFieldsFailure() {
        PasswordData passwordData = new PasswordData("", "", "");
        page.changePassword(passwordData);
        page.confirmChangingPasswordButton.click();
        page.isElementPresent(5, page.error);
        assertEquals(page.oldPasswordRequiredError.getText(), translation.get("REQUIRED_OLD_PASSWORD"));
        assertEquals(page.newPasswordRequiredError.getText(), translation.get("REQUIRED_NEW"));
        assertEquals(page.newPasswordConfirmRequiredError.getText(), translation.get("REQUIRED_CONFIRM"));
    }

    @Test
    public void changedPasswordWithIncorrectPasswordFailure() throws InterruptedException {
        String expectedText = "2 " + translation.get("ATTEMPTS_WITH_COMMA");
        PasswordData passwordData = new PasswordData("123456Bla", "123456Qwe", "123456Qwe");
        page.changePassword(passwordData);
        page.confirmChangingPasswordButton.click();
        Thread.sleep(1000);
        assertEquals(page.passwordAttemptsText.getText(), expectedText);

        String newExpectedText = "3 " + translation.get("ATTEMPTS_WITH_COMMA");
        PasswordData correctPasswordData = new PasswordData("123456Qwe", "123456Qwe", "123456Qwe");
        page.changePassword(correctPasswordData);
        page.confirmChangingPasswordButton.click();
        Thread.sleep(1000);
        assertEquals(page.passwordAttemptsText.getText(), newExpectedText);
    }

    //check all error messages for wrong password
    @Test(dataProvider = "passwordFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranlationPasswordField(String password, String translationKey){
        page.type(page.newPasswordField, password);
        page.newPasswordField.sendKeys(Keys.TAB);
        page.isElementPresent(5, page.error);
        assertEquals(page.error.getText(), translation.get(translationKey));
    }

    @Test
    public void checkErrorTranlationMismatchPasswords(){
        PasswordData passwordData = new PasswordData("123456Qwe", "123456Qwe", "123456Qw");
        page.changePassword(passwordData);
        page.confirmChangingPasswordButton.click();
        page.isElementPresent(5, page.error);
        assertEquals(page.error.getText(), translation.get("PASSWORDS_DON’T_MATCH"));
    }

}
