package UITest.functionalityTests;

import Data.UserData;
import Pages.ProfilePage;
import Pages.SignUpPage;
import UITest.BaseTest;
import UITest.translationTests.TranslationTest;
import Utils.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Random;

import static UITest.translationTests.TranslationTest.readTranslationFile;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class SignUpTest extends BaseTest {
    private SignUpPage signup = PageFactory.initElements(getWebDriver(), SignUpPage.class);

    String locale;
    Map<String, String> translation = null;

    @Parameters({"locale"})
    @BeforeClass
    public String getLocale(@Optional("en") String localeFromParameters) {
        this.locale = localeFromParameters;
        return locale;
    }

    @BeforeClass
    public Map<String, String> getTranslationMap() {
        {
            try {
                translation = readTranslationFile(TranslationTest.Locale.valueOf(locale));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return translation;
    }

    @BeforeMethod(alwaysRun = true)
    public void logBeforeMethod(Method m, Object[] p) {
        Reporter.log("test start " + m.getName(), true);
    }


    @AfterMethod(alwaysRun = true)
    public void logAfteMethod(Method m, Object[] p) {
        Reporter.log("test finished", true);
    }

    @BeforeMethod
    public void openingBeforeTests() {
        signup.open();
    }

    //WITHOUT INTRO
    @Test
    public void signUpWithoutAnyFieldsFailure() throws InterruptedException {
        UserData user = new UserData("", "", "", "");
        signup.registerWithoutSubmit(user);
        signup.signUpButton.click();
        assertEquals(signup.usernameRequiredError.getText(), translation.get("REQUIRED") + " " + translation.get("USERNAME"));
        assertEquals(signup.dateRequiredField.getText(), (translation.get("REQUIRED") + " " + translation.get("DATE_OF_BIRTH")));
        assertEquals(signup.emailRequiredError.getText(), (translation.get("REQUIRED") + " " + translation.get("EMAIL")));
        assertEquals(signup.passwordRequiredError.getText(), (translation.get("REQUIRED") + " " + translation.get("PASSWORD")));
        assertEquals(signup.confirmPasswordRequiredError.getText(), (translation.get("REQUIRED") + " " + translation.get("CONFIRM_PASSWORD")));
        assertEquals(signup.agreeCheckboxRequiredError.getText(), translation.get("REQUIRED_AGREE"));
    }


//    @Test
//    public void signUpWithIncorrectImageFailure() throws InterruptedException {
//     signup.uploadImage(INVALID_IMAGE_AVI);
//        UserData user = new UserData("","","", "");
//        signup.registerWithSubmit(user);
//        assertEquals(IMAGE_ERROR_FORMAT_EN, signup.imageError.getText());
//        signup.uploadImage(INVALID_IMAGE_MP3);
//        assertEquals(IMAGE_ERROR_FORMAT_EN, signup.imageError.getText());
//        signup.uploadImage(INVALID_IMAGE_MP4);
//        assertEquals(IMAGE_ERROR_FORMAT_EN, signup.imageError.getText());
//        signup.uploadImage(INVALID_IMAGE_MPG);
//        assertEquals(IMAGE_ERROR_FORMAT_EN, signup.imageError.getText());
//        signup.uploadImage(INVALID_IMAGE_MOV);
//        assertEquals(IMAGE_ERROR_FORMAT_EN, signup.imageError.getText());
//        signup.uploadImage(INVALID_IMAGE_PDF);
//        assertEquals(IMAGE_ERROR_FORMAT_EN, signup.imageError.getText());
//        signup.uploadImage(VALID_BIG_IMAGE_JPG);
//        assertEquals(IMAGE_ERROR_SIZE_EN, signup.imageError.getText());
//
//        signup.headerLangZhButton.click();
//        signup.uploadImage(INVALID_IMAGE_AVI);
//        assertEquals(IMAGE_ERROR_FORMAT_ZH, signup.imageError.getText());
//        signup.uploadImage(INVALID_IMAGE_MP3);
//        assertEquals(IMAGE_ERROR_FORMAT_ZH, signup.imageError.getText());
//        signup.uploadImage(INVALID_IMAGE_MP4);
//        assertEquals(IMAGE_ERROR_FORMAT_ZH, signup.imageError.getText());
//        signup.uploadImage(INVALID_IMAGE_MPG);
//        assertEquals(IMAGE_ERROR_FORMAT_ZH, signup.imageError.getText());
//        signup.uploadImage(INVALID_IMAGE_MOV);
//        assertEquals(IMAGE_ERROR_FORMAT_ZH, signup.imageError.getText());
//        signup.uploadImage(INVALID_IMAGE_PDF);
//        assertEquals(IMAGE_ERROR_FORMAT_ZH, signup.imageError.getText());
//        signup.uploadImage(VALID_BIG_IMAGE_JPG);
//        assertEquals(IMAGE_ERROR_SIZE_ZH, signup.imageError.getText());
//    }


    @Test
    public void redirectToTermsPageSuccess() throws InterruptedException {
        signup.termsLink.click();
        assertEquals(signup.h1.getText(), translation.get("TERMS_H1"));
    }

    @Test
    public void redirectToSignInPageSuccess() {
        signup.signInLink.click();
        assertEquals(driver.findElement(By.className("h1")).getText(), translation.get("SIGNIN_H1"));
    }

    @Test
    public void signUpSuccess() {
        Random rand = new Random();
        int n = rand.nextInt(50) + 1;
        UserData user = new UserData("test", "test" + n + "@yopmail.com", "123456Qwe", "123456Qwe");
        signup.registerWithSubmit(user);
    }


    @Test
    public void redirectToPreviousStepSucess() throws InterruptedException {
        Random rand = new Random();
        int n = rand.nextInt(50) + 1;
        UserData user = new UserData("test", "test" + n + "@yopmail.com", "123456Qwe", "123456Qwe");
        signup.registerWithSubmit(user);
        signup.confirmButton.click();
        signup.previousLink.click();
        assertEquals(signup.h1.getText(), translation.get("SIGNUP_H1"));
        assertEquals(signup.usernameField.getAttribute("value"), user.getUsername());
        assertEquals(signup.emailField.getAttribute("value"), user.getEmail());
    }

    @Test
    public void redirectToSignInPageFromSecondStepSuccess() throws InterruptedException {
        signup.signInLink.click();
        assertEquals(driver.findElement(By.className("h1")).getText(), translation.get("SIGNIN_H1"));
    }

    @Test
    public void signUpAllStepsSuccess() throws InterruptedException {
        Random rand = new Random();
        int n = rand.nextInt(50) + 1;
        UserData user = new UserData("test", "test" + n + "@yopmail.com", "123456Qwe", "123456Qwe");
        signup.registerWithSubmit(user);
        signup.fillingQuestionsAndAnswers();
        signup.confirmButton.click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".markdown")));
        } catch (Exception e) {
            assertFalse("Timeout during wait", true);
        }
        String url = Config.getProperty("url") + "/en/check-your-email";
        assertEquals(driver.getCurrentUrl(), url);
    }


    //WITH INTRO
    @Test
    public void signUpWithIntroSuccess() throws InterruptedException {
        Actions actions = new Actions(driver);
        signup.withIntroTab.click();
        signup.type(signup.introEmailField, "alina.berestenko@sandbx.co");
        signup.type(signup.introEmailField, Keys.TAB);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(signup.introUsernameField, "defaultValue"));
        String introduserUsername = signup.introUsernameField.getAttribute("defaultValue");
        assertEquals(introduserUsername, "alina.berestenko");
    }


    @Test
    public void signUpByReferralSuccess() throws InterruptedException {
        signup.referralTab.click();
        String referalLink = Config.getProperty("referral_link");
        signup.type(signup.referralField, referalLink);
        signup.referralNextButton.click();
        wait.until(ExpectedConditions.visibilityOf(signup.introEmailField));
        assertEquals(signup.introEmailField.getAttribute("value"), "alina.berestenko@sandbx.co");
    }


}
