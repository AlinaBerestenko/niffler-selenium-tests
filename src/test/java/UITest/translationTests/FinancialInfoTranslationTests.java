package UITest.translationTests;

import Data.UserData;
import Pages.SignInPage;
import Pages.WalletPage;
import Utils.TestDataProviders;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class FinancialInfoTranslationTests extends TranslationTest {

    private WalletPage page = PageFactory.initElements(getWebDriver(), WalletPage.class);


    String locale;
    Map<String, String> translation = null;

    public FinancialInfoTranslationTests() throws IOException {
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
        open(locale, "financial-details");
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


//FINANCIAL INFO
    @Test
    public void financialDetailsCheckDataSuccess() {
        wait.until(ExpectedConditions.visibilityOf(page.passwordField));
        assertEquals(page.title.getText(), translation.get("FINANCIAL_DETAILS_H1"));
    }

    @Test
    public void financialDetailsOpenWithoutPasswordFailure() {
        wait.until(ExpectedConditions.visibilityOf(page.button));
        page.button.click();
        assertEquals(page.passwordError.getText(), translation.get("REQUIRED_PASSWORD"));
    }

    //check all error messages for wrong password
    @Test(dataProvider = "passwordFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranslationLOGINPasswordField(String password, String translationKey){
        wait.until(ExpectedConditions.visibilityOf(page.passwordField));
        page.type(page.passwordField, password);
        page.passwordField.sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(page.error));
        assertEquals(page.error.getText(), translation.get(translationKey));
    }

    @Test
    public void financialDetailsOpenWithIncorrectPasswordFailure() throws InterruptedException {
        String attempts = "2 " + translation.get("ATTEMPTS_WITH_COMMA");
        wait.until(ExpectedConditions.visibilityOf(page.passwordField));
        page.type(page.passwordField, "123456Qw");
        page.button.click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(page.passwordAttempts));
        assertEquals(page.passwordAttempts.getText(), attempts);
        page.passwordField.click();
        page.type(page.passwordField, "123456Qwe");
        page.button.click();
    }

//EDIT BTC ADDRESS
    @Test
    public void EditBtcAddressWithoutAnyField() {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.editBtcAddressButton));
        page.editBtcAddressButton.click();
        page.editButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.error));
        assertEquals(page.changeBtcTrxPassError.getText(), translation.get("REQUIRED_TRX_PASS"));
        assertEquals(page.editBtcAddressError.getText(), translation.get("INVALID_BTC_ADDRESS"));
    }

    //check all error messages for wrong password
    @Test(dataProvider = "BTCaddressFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranslationBTCAddressField(String address, String translationKey) {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.editBtcAddressButton));
        page.editBtcAddressButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.editBtcAddressField));
        page.type(page.editBtcAddressField, address);
        page.editBtcAddressField.sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(page.error));
        assertEquals(page.error.getText(), translation.get(translationKey));
    }

    //check all error messages for wrong password
    @Test(dataProvider = "passwordFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranslationTRXPasswordForEditBTCAddressField(String password, String translationKey) {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.editBtcAddressButton));
        page.editBtcAddressButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.changeBtcTrxPassField));
        page.type(page.editBtcAddressField, "1ER8FbcDFHQZbDZ3v9iAsafNSHSjtNLejk");
        page.type(page.changeBtcTrxPassField, password);
        page.changeBtcTrxPassField.sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(page.error));
        assertEquals(page.error.getText(), translation.get(translationKey));
    }

    @Test
    public void editBtcAddressSuccess() {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.editBtcAddressButton));
        page.editBtcAddressButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.changeBtcTrxPassField));
        page.type(page.changeBtcTrxPassField, "123456Qwe");
        page.type(page.editBtcAddressField, "1Kw2Yh8GJZbMiHbHoLYgzSw1d36WCB3rgu");
        page.editButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.successEditButton));
        assertEquals(page.successEditButton.getText(), translation.get("SUCCESS_BUTTON"));
    }

    @Test
    public void editBtcAddressWithIncorrectPassFailure() throws InterruptedException {
        String attempts = "2 " + translation.get("ATTEMPTS_WITH_DOT");
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.editBtcAddressButton));
        page.editBtcAddressButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.changeBtcTrxPassField));
        page.type(page.changeBtcTrxPassField, "123456Qw");
        page.type(page.editBtcAddressField, "1Kw2Yh8GJZbMiHbHoLYgzSw1d36WCB3rgu");
        page.editButton.click();
        Thread.sleep(5000);
        assertEquals(page.changeBtcTrxPassAttempts.getText(), attempts);
        page.type(page.editBtcAddressField, " 1Kw2Yh8GJZbMiHbHoLYgzSw1d36WCB3rgu");
        page.type(page.changeBtcTrxPassField, "123456Qwe");
    }


//SHOW PASSPHRASE
    //check all error messages for wrong password
    @Test(dataProvider = "passwordFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranslationForShowPassphraseTRXPassField(String password, String translationKey) {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.button));
        page.button.click();
        wait.until(ExpectedConditions.visibilityOf(page.showPassphraseTrxPassField));
        page.type(page.showPassphraseTrxPassField, password);
        page.showPassphraseTrxPassField.sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(page.error));
        assertEquals(page.error.getText(), translation.get(translationKey));
    }

    @Test
    public void showPassphraseWithIncorrectPasswordFailure() throws InterruptedException {
        String attempts = "2 " + translation.get("ATTEMPTS_WITH_DOT");
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.passphraseField));
        page.button.click();
        wait.until(ExpectedConditions.visibilityOf(page.showPassphraseTrxPassField));
        page.type(page.showPassphraseTrxPassField, "123456Qw");
        page.button.click();
        Thread.sleep(5000);
        assertEquals(page.attempts.getText(), attempts);
        page.type(page.showPassphraseTrxPassField, "123456Qwe");
        page.button.click();
        wait.until(ExpectedConditions.visibilityOf(page.successButton));
        assertEquals(page.successButton.getText(), translation.get("PROCCED"));
    }

    @Test
    public void showPassphraseWithoutTrxFailure() {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.passphraseField));
        page.button.click();
        page.showPassphraseTrxPassField.click();
        page.button.click();
        assertEquals(page.showPassphraseTrxPassError.getText(), translation.get("REQUIRED_TRX_PASS"));
   }

    @Test
    public void showPassphraseWithCorrectTrxSuccess() {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.passphraseField));
        page.button.click();
        wait.until(ExpectedConditions.visibilityOf(page.showPassphraseTrxPassField));
        page.type(page.showPassphraseTrxPassField, "123456Qwe");
        page.button.click();
        wait.until(ExpectedConditions.visibilityOf(page.successButton));
        assertEquals(page.successButton.getText(), translation.get("PROCCED"));
        assertNotEquals(page.passphraseField.getText(), "");
    }

//RESET TRX PASSWORD
    @Test
    public void resetTrxPassWithoutAnyFieldsFailure() {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.resetTRXButton));
        page.resetTRXButton.click();
        page.button.click();
        assertEquals(page.resetTrxPassphraseError.getText(), translation.get("REQUIRED_PASSPHRASE"));
    }

    //check all error messages for wrong password
    @Test(dataProvider = "passwordFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranslationForResetTRXPassField(String password, String translationKey) {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.resetTRXButton));
        page.resetTRXButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.resetTrxPassphraseField));
        page.type(page.resetTrxPassphraseField, "1111");
        page.button.click();
        wait.until(ExpectedConditions.visibilityOf(page.newTRXPasswordField));
        page.type(page.newTRXPasswordField, password);
        page.newTRXPasswordField.sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(page.newTRXPasswordError));
        assertEquals(page.newTRXPasswordError.getText(), translation.get(translationKey));
    }

    @Test
    public void resetTrxPassWithDifferentPasswordsFailure() {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.resetTRXButton));
        page.resetTRXButton.click();
        wait.until(ExpectedConditions.visibilityOf(page.resetTrxPassphraseField));
        page.type(page.resetTrxPassphraseField, "1111");
        page.button.click();
        wait.until(ExpectedConditions.visibilityOf(page.newTRXPasswordField));
        page.resetTrxPassword("123456Qwe", "123456Asd");
        page.button.click();
        assertEquals(page.error.getText(), translation.get("TRANSACTION_PASSWORDS_DON’T_MATCH"));
    }

    //WAIT FOR FIXING NIF-1576
//    @Test
//    public void resetTrxPassWithInvalidPassphraseFailure() {
//        page.openFinancialDetails();
//        wait.until(ExpectedConditions.visibilityOf(page.resetTRXButton));
//        page.resetTRXButton.click();
//        wait.until(ExpectedConditions.visibilityOf(page.resetTrxPassphraseField));
//        page.type(page.resetTrxPassphraseField, "1111");
//        page.button.click();
//        assertEquals(page.attempts.getText(), translation.get("PASSPHRASE_2_ATTEMPTS"));
//        page.type(page.resetTrxPassphraseField, "beautiful rape button clear spin peel rock might shiny wise near discover melt ancient");
//        page.attempts.click();
//    }

//CHANGE TRX PASSWORD
    @Test
    public void changeTrxPasswordWithoutAnyFieldsFailure() {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.trxPassTab));
        page.trxPassTab.click();
        wait.until(ExpectedConditions.visibilityOf(page.button));
        page.button.click();
        wait.until(ExpectedConditions.visibilityOf(page.changeTRXCurrentPassError));
        assertEquals(page.changeTRXCurrentPassError.getText(), translation.get("REQUIRED_OLD_PASSWORD"));
        assertEquals(page.changeTRXNewPassError.getText(), translation.get("REQUIRED_NEW"));
        assertEquals(page.changeTRXConfirmPassError.getText(), translation.get("REQUIRED_CONFIRM"));
    }

    //check all error messages for wrong password
    @Test(dataProvider = "passwordFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranslationForChangeOldTRXPassField(String password, String translationKey) {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.trxPassTab));
        page.trxPassTab.click();
        wait.until(ExpectedConditions.visibilityOf(page.changeTRXCurrentPassField));
        page.type(page.changeTRXCurrentPassField, password);
        page.changeTRXCurrentPassField.sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(page.error));
        assertEquals(page.error.getText(), translation.get(translationKey));
    }

    //check all error messages for wrong password
    @Test(dataProvider = "passwordFieldCheckedData", dataProviderClass = TestDataProviders.class)
    public void checkErrorTranslationForChangeNewTRXPassField(String password, String translationKey) {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.trxPassTab));
        page.trxPassTab.click();
        wait.until(ExpectedConditions.visibilityOf(page.changeTRXNewPassField));
        page.type(page.changeTRXNewPassField, password);
        page.changeTRXNewPassField.sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(page.error));
        assertEquals(page.error.getText(), translation.get(translationKey));
    }

    @Test
    public void changeTrxPasswordWithDifferentPasswordsFailure() {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.trxPassTab));
        page.trxPassTab.click();
        wait.until(ExpectedConditions.visibilityOf(page.changeTRXNewPassField));
        page.type(page.changeTRXNewPassField, "123456Qwe");
        page.type(page.changeTRXConfirmPassField, "123456Qw");
        page.changeTRXConfirmPassField.sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(page.error));
        assertEquals(page.error.getText(), translation.get("PASSWORDS_DON’T_MATCH"));
    }

    @Test
    public void changeTrxPasswordWithIncorrectOldPasswordsFailure() throws InterruptedException {
        String attempts = "2 " + translation.get("ATTEMPTS_WITH_DOT");
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.trxPassTab));
        page.trxPassTab.click();
        wait.until(ExpectedConditions.visibilityOf(page.changeTRXCurrentPassField));
        page.type(page.changeTRXCurrentPassField, "123456Qw");
        page.type(page.changeTRXNewPassField, "123456Qw");
        page.type(page.changeTRXConfirmPassField, "123456Qw");
        page.button.click();
        Thread.sleep(2000);
        assertEquals(page.attempts.getText(), attempts);
        page.type(page.changeTRXCurrentPassField, "123456Qwe");
        page.type(page.changeTRXNewPassField, "123456Qwe");
        page.type(page.changeTRXConfirmPassField, "123456Qwe");
        page.button.click();
    }

    @Test
    public void changeTrxPasswordSuccess() {
        page.openFinancialDetails();
        wait.until(ExpectedConditions.visibilityOf(page.trxPassTab));
        page.trxPassTab.click();
        wait.until(ExpectedConditions.visibilityOf(page.changeTRXCurrentPassField));
        page.type(page.changeTRXCurrentPassField, "123456Qwe");
        page.type(page.changeTRXNewPassField, "123456Qwe");
        page.type(page.changeTRXConfirmPassField, "123456Qwe");
        page.button.click();
        wait.until(ExpectedConditions.visibilityOf(page.successButton));
        assertEquals(page.successButton.getText(), translation.get("SUCCESS_BUTTON"));
    }
}
