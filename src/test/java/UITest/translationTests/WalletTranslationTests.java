package UITest.translationTests;

import Data.UserData;
import Pages.ProfilePage;
import Pages.SignInPage;
import Pages.WalletPage;
import Utils.TestDataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;

import static Utils.Constans.*;
import static Utils.Constans.REQUIRED_TRX_PASS_ZH;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class WalletTranslationTests extends TranslationTest {

    private WalletPage page = PageFactory.initElements(getWebDriver(), WalletPage.class);


    String locale;
    Map<String, String> translation = null;

    public WalletTranslationTests() throws IOException {
    }

    @Parameters({"locale"})
    @BeforeClass
    public String getLocale(String localeFromParameters) {
        this.locale = localeFromParameters;
        return locale;
    }

    @BeforeClass
    public Map<String, String> getTranslationMap() {
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
        open(locale, "wealth");
    }

    @BeforeClass
    public void signInBeforeTests() {
        open(locale, "sign-in");
        SignInPage signin = PageFactory.initElements(getWebDriver(), SignInPage.class);
        UserData user = new UserData("alina.berestenko@sandbx.co", "123456Qwe");
        signin.loginAs(user);
    }

    @AfterClass
    public static void logOutAfterTests() {
        SignInPage signin = PageFactory.initElements(getWebDriver(), SignInPage.class);
        signin.logOutButton.click();
    }

    @Test
    public void checkDataInWalletSuccess() {
        wait.until(ExpectedConditions.visibilityOf(page.title));
        assertEquals(page.title.getText(), translation.get("WEALTH_H1"));
    }

//SELL GED
    @Test
    public void sellGedCheckDataSuccess() {
        wait.until(ExpectedConditions.visibilityOf(page.sellGedButton));
        page.sellGedButton.click();
        assertEquals(page.title.getText(), translation.get("GED_CASH_OUT_H1"));
    }

    @Test
    public void sellGedWithoutAnyFieldsFailure() {
        wait.until(ExpectedConditions.visibilityOf(page.sellGedButton));
        page.sellGedButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.amountField));
        page.button.click();
        assertEquals(page.amountError.getText(), translation.get("REQUIRED_GED_AMOUNT"));
        assertEquals(page.trxPassError.getText(), translation.get("REQUIRED_TRX_PASS"));
    }


    //check all error messages for wrong password
    @Test(dataProvider = "passwordFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranslationTRXPasswordField(String password, String translationKey){
        wait.until(ExpectedConditions.visibilityOf(page.sellGedButton));
        page.sellGedButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.amountField));
        page.type(page.amountField, "100");
        page.type(page.trxPassField, password);
        page.trxPassField.sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(page.error));
        assertEquals(page.error.getText(), translation.get(translationKey));
    }

    //check all error messages for wrong amount
    @Test(dataProvider = "GEDamountFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranslationGEDAmountField(String amount, String translationKey){
        wait.until(ExpectedConditions.visibilityOf(page.sellGedButton));
        page.sellGedButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.amountField));
        page.type(page.trxPassField, "123456Qwe");
        page.type(page.amountField, amount);
        page.button.click();
        wait.until(ExpectedConditions.visibilityOf(page.error));
        assertEquals(page.error.getText(), translation.get(translationKey));
    }

    @Test
    public void checkErrorWithoutGedInWallet() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(page.sellGedButton));
        page.sellGedButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.amountField));
        page.sellGed("100", "123456Qwe");
        page.button.click();
        Thread.sleep(2000);
        assertEquals(page.insufficientGedError.getText(), translation.get("INSUFFICIENT_AMOUNT_OF_GED"));
    }

//BUY GEC
    @Test
    public void buyGecCheckTitleSuccess() {
        wait.until(ExpectedConditions.visibilityOf(page.buyGecButton));
        page.buyGecButton.click();
        assertEquals(page.title.getText(), translation.get("BUY_GEC_H1"));
    }

    @Test
    public void buyGecWithoutAnyFieldsFailure() {
        wait.until(ExpectedConditions.visibilityOf(page.buyGecButton));
        page.buyGecButton.click();
        assertEquals(page.title.getText(), translation.get("BUY_GEC_H1"));
        page.gecAmountField.click();
        page.readyToPayButton.click();
        assertEquals(page.gecAmountError.getText(), translation.get("REQUIRED_GEC_AMOUNT"));
    }

    //check all error messages for wrong amount
    @Test(dataProvider = "GECamountFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranslationGECAmountField(String amount, String translationKey){
        wait.until(ExpectedConditions.visibilityOf(page.buyGecButton));
        page.buyGecButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.gecAmountField));
        page.type(page.gecAmountField, amount);
        page.gecAmountField.sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(page.error));
        assertEquals(page.error.getText(), translation.get(translationKey));
    }

    @Test
    public void buyGecCheckCopyButtonSuccess() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(page.buyGecButton));
        page.buyGecButton.click();
        Thread.sleep(1000);
        page.type(page.gecAmountField, "1000");
        page.readyToPayButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.copyLink));
        page.copyLink.click();
        assertEquals(page.copiedLink.getText(), translation.get("COPIED"));
    }

    @Test
    public void buyGecSuccess() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(page.buyGecButton));
        page.buyGecButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.gecAmountField));
        Thread.sleep(2000);
        page.gecAmountField.click();
        page.type(page.gecAmountField, "1000");
        page.readyToPayButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.button));
        assertEquals(page.button.getText(), translation.get("I_HAVE_PAID"));
        page.button.click();
        assertEquals(page.successPaidButton.getText(), translation.get("SUCCESS_BUY_GEC"));
    }
}
