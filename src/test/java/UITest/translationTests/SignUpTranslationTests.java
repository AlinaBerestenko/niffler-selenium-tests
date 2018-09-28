package UITest.translationTests;

import Data.UserData;
import Pages.SignUpPage;
import Utils.TestDataProviders;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class SignUpTranslationTests extends TranslationTest {

    private SignUpPage signup = PageFactory.initElements(getWebDriver(), SignUpPage.class);

    String locale;
    Map<String, String> translation = null;


    /*
    before class should get locale from testNG parameters in xml
    and should get get map from translation file
     */


    @Parameters({"locale"})
    @BeforeClass(groups = "test")
    public String getLocale(@Optional("en") String localeFromParameters) {
       this.locale = localeFromParameters;
       return locale;
    }

    @BeforeClass(groups = "test")
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



    @BeforeMethod(groups = "test")
    public void openingBeforeTests(Method m, Object[] p) {
        open(locale, "sign-up");

    }

    @BeforeMethod(alwaysRun = true)
    public void logBeforeMethod(Method m, Object[] p){
            Reporter.log("test start " + m.getName(),true);
        }


    @AfterMethod(alwaysRun = true)
    public void logAfteMethod(Method m, Object[] p){
            Reporter.log("test finished",true) ;
    }


    //WITHOUT INTRO
    @Test
    public void checkErrorMessageRequiredDateOfBirthCorrectnes(){
        signup.usernameField.clear();
        signup.usernameField.sendKeys(Keys.ENTER);
        assertEquals(signup.dateRequiredField.getText(), (translation.get("REQUIRED") + " " + translation.get("DATE_OF_BIRTH")));
    }

    @Test
    public void checkErrorMessagesRequiredUsernameCorrectnes(){
        signup.usernameField.click();
        signup.usernameField.sendKeys(Keys.TAB);
        assertEquals(signup.error.getText(), translation.get("REQUIRED") +" " + translation.get("USERNAME"));
    }


    @Test
    public void checkErrorMessageLongUsernameCorrectnes() throws InterruptedException, IOException {
        UserData user = new UserData("qweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrererqweqwewrrerwerwerwerwerewrerer", "alina.berestenko@sandbx.co", "", "");
        signup.registerWithSubmit(user);
        assertEquals(signup.usernameError.getText(), translation.get("LONG_USERNAME"));
    }

    @Test
    public void checkErorMessageEmailRequiredCorrectnes(){
        signup.emailField.click();
        signup.emailField.sendKeys(Keys.TAB);
        assertEquals(signup.error.getText(), (translation.get("REQUIRED")+" "+translation.get("EMAIL")));

    }

    //check all error messages for wrong password
    @Test(dataProvider = "passwordFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranlationPasswordField(String password, String translationKey){
        signup.type(signup.passwordField, password );
        signup.passwordConfirmField.sendKeys(Keys.TAB);
        assertEquals(signup.error.getText(), translation.get(translationKey));

    }

    @Test
    public void checkErroMessageRequiredConfimPasswordCorrectnes(){
        signup.passwordConfirmField.click();
        signup.passwordConfirmField.sendKeys(Keys.TAB);
        assertEquals(signup.error.getText(),(translation.get("REQUIRED") + " "+ translation.get("CONFIRM_PASSWORD")));
    }


    @Test
    public void checkErroMessagePasswordMismatchCorrectness(){
        signup.type(signup.passwordField, "123Qwert");
        signup.type(signup.passwordConfirmField,"321");
        signup.passwordConfirmField.sendKeys(Keys.TAB);
        assertEquals(signup.error.getText(),translation.get("PASSWORDS_DON’T_MATCH"));

    }


    //check is the text present on image frame, and its correctness
    @Test
    public void checkImageTextCorrectness(){
        assertEquals(signup.imageText.getText(), translation.get("IMAGE_TEXT"));

    }

    @Test
    public void checkEmailExistErrorTextcorrectness() throws InterruptedException {
        UserData user = new UserData("a","alina.berestenko","", "");
        signup.registerWithSubmit(user);
        assertEquals(signup.error.getText(), translation.get("INVALID_EMAIL"));
    }

    @Test(groups = "test")
    public void checkMessageExistsEmailTextCorrectness() throws InterruptedException, IOException {
        UserData user = new UserData("a","alina.berestenko@sandbx.co","1234Qwer", "1234Qwer");
        signup.registerWithSubmit(user);
        wait.until(ExpectedConditions.visibilityOf(signup.error));
        assertEquals(signup.error.getText(), translation.get("EXISTS_EMAIL"));
    }

    @Test
    public void checkPasswordHintTextCorrectness(){
        assertEquals(signup.hintText.getAttribute("innerText"), translation.get("HINT_TEXT_PASSWORD"));
    }

    @Test
    public void checkCheckboxAgreeTextCorrectness(){
        assertEquals(signup.checkboxAgreeText.getAttribute("innerText"), translation.get("CHEKBOX_AGREE_TEXT"));
    }


    @Test
    public  void checkHaveAnAccountTextCorrectness(){
        assertEquals(signup.haveAnAccount.getAttribute("textContent"), translation.get("HAVE_AN_ACCOUNT"));
    }

    @Test(groups = "test")
    public void signUpWithoutAnyQuestionsFailure() {
        Actions actions = new Actions(driver);
        Random rand = new Random();
        int  n = rand.nextInt(100) + 1;
        UserData user = new UserData("test","test"+n+"@yopmail.com","123456Qwe", "123456Qwe");
        signup.registerWithSubmit(user);
        try{
        wait.until(ExpectedConditions.visibilityOf(signup.question1));}
        catch (Exception e){
            System.out.println("TimeoutException" + e);

        }
        signup.confirmButton.click();
        assertEquals(signup.answer1RequiredError.getText(),(translation.get("REQUIRED") + " "+ translation.get("ANSWER")));
        assertEquals(signup.answer2RequiredError.getText(),(translation.get("REQUIRED") + " "+ translation.get("ANSWER")));
        assertEquals(signup.answer3RequiredError.getText(),(translation.get("REQUIRED") + " "+ translation.get("ANSWER")));
        assertEquals(signup.question1RequiredError.getText(),(translation.get("REQUIRED_SEQURITY_QUESTION")));
        assertEquals(signup.question2RequiredError.getText(),(translation.get("REQUIRED_SEQURITY_QUESTION")));
        assertEquals(signup.question3RequiredError.getText(),(translation.get("REQUIRED_SEQURITY_QUESTION")));
    }

    @Test
    public void checkSignUpWithIntroRequiredEmailErrorMessageCorrectness() throws InterruptedException {
        signup.withIntroTab.click();
        signup.type(signup.introEmailField, Keys.TAB);
        assertEquals(signup.introEmailRequiredError.getText(),(translation.get("REQUIRED") + " "+ translation.get("INTRODUCER'S_EMAIL")));
    }

    //Referral link tab
    @Test
    public void checkReferralLinkRequiredErrorMessageCorrectness() throws InterruptedException {
        signup.referralTab.click();
        signup.referralNextButton.click();
        assertEquals(signup.referralRequiredError.getText(),(translation.get("REQUIRED") + " "+ translation.get("REFERRAL_LINK")));
    }

    @Test
    public void checkReferralLinkInvalidErrorMessageCorrectness() throws InterruptedException {
        signup.referralTab.click();
        signup.type(signup.referralField, "bla-bla");
        signup.referralNextButton.click();
        wait.until(ExpectedConditions.visibilityOf(signup.error));
        assertEquals(signup.error.getText(),translation.get("INVALID_REFERRAL_LINK"));
    }















}
