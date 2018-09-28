package UITest.translationTests;

import Data.UserData;
import Pages.SignInPage;
import Utils.TestDataProviders;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import static Utils.Constans.*;
import static org.testng.Assert.assertEquals;

public class SignInTranslationTests extends TranslationTest {

    private SignInPage page = PageFactory.initElements(getWebDriver(), SignInPage.class);

    String locale;
    Map<String, String> translation = null;

    public SignInTranslationTests() throws IOException {
    }

    @BeforeMethod(alwaysRun = true)
    public void logBeforeMethod(Method m, Object[] p){
        Reporter.log("test start " + m.getName(),true);
    }


    @AfterMethod(alwaysRun = true)
    public void logAfteMethod(Method m, Object[] p){
        Reporter.log("test finished",true) ;
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

    @BeforeMethod
    public void open() {
        open(locale, "sign-in");
    }

    @Test
    public void signInWithoutPasswordAndEmailFailure() {
        page.type(page.emailField, "");
        page.type(page.passwordField, "");
        page.signInButton.click();
        assertEquals(page.emailError.getText(), translation.get("REQUIRED_EMAIL"));
        assertEquals(page.passwordError.getText(), translation.get("REQUIRED_PASSWORD"));
    }


    //check all error messages for wrong password
    @Test(dataProvider = "passwordFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranslationPasswordField(String password, String translationKey){
        page.type(page.emailField, "test@gmail.com");
        page.type(page.passwordField, password);
        page.passwordField.sendKeys(Keys.TAB);
        assertEquals(page.error.getText(), translation.get(translationKey));
    }

    //check all error messages for wrong email
    @Test(dataProvider = "emailFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranslationEmailField(String email, String translationKey){
        page.type(page.passwordField, "123456Qwe");
        page.type(page.emailField, email);
        page.emailField.sendKeys(Keys.TAB);
        assertEquals(page.error.getText(), translation.get(translationKey));
    }

    @Test
    public void signInWithBadCredentialsFailure() {
        UserData user = new UserData("alina.berestenko@sandbx.co", "654321Qwe");
        page.type(page.emailField, "alina.berestenko@sandbx.co");
        page.type(page.passwordField, "654321Qwe");
        page.signInButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.error));
        assertEquals(page.error.getText(), translation.get("BAD_CREDENTIALS"));
    }
}
