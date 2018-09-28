package UITest.functionalityTests;

import Data.UserData;
import Pages.ProfilePage;
import Pages.SignInPage;
import Pages.WalletPage;
import UITest.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static Utils.Constans.*;
import static org.testng.Assert.assertEquals;

public class WalletTest extends BaseTest{
    private WalletPage wallet = PageFactory.initElements(getWebDriver(), WalletPage.class);


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
        wallet.open();
    }

    @BeforeMethod
    public static void signInBeforeTests() {
        SignInPage signin = PageFactory.initElements(getWebDriver(), SignInPage.class);
        signin.open();
        UserData user = new UserData("alina.berestenko@sandbx.co", "123456Qwe");
        signin.loginAs(user);
    }

    @AfterClass
    public void logOutAfterTests() throws InterruptedException {
        ProfilePage profile = PageFactory.initElements(getWebDriver(), ProfilePage.class);
        profile.logOutButton.click();
    }



//SELL GED
    @Test
    public void sellGedCheckBtcWallet() {
        String btcWallet = wallet.btcWallet.getText();
        wallet.sellGedButton.click();
        assertEquals(btcWallet, wallet.btcWalletAddressField.getAttribute("placeholder"));
    }


//EDIT BTC ADDRESS
    @Test
    public void editBtcAddressSuccess() {
        String newAddress = "1Kw2Yh8GJZbMiHbHoLYgzSw1d36WCB3rgu";
        wallet.isElementPresent(5, wallet.financialDetailsButton);
        wallet.openFinancialDetails();
        wallet.isElementPresent(5, wallet.editBtcAddressButton);
        wallet.editBtcAddressButton.click();
        wallet.isElementPresent(5, wallet.changeBtcTrxPassField);
        wallet.type(wallet.editBtcAddressField, newAddress);
        wallet.type(wallet.changeBtcTrxPassField, "123456Qwe");
        wallet.editButton.click();
        wallet.isElementPresent(5, wallet.successEditButton);
        assertEquals(wallet.successEditButton.getText(), "SUCCESS");
        assertEquals(wallet.btcAddress.getText(), newAddress);
        wallet.open();
        wallet.isElementPresent(5, wallet.btcWallet);
        assertEquals(wallet.btcWallet.getText(), newAddress);
    }

//SHOW PASSPHRASE


//RESET TRX PASSWORD
    //TO DO
//    @Test
//    public void resetTrxPassWithoutAnyFieldsFailure() throws InterruptedException {
//        wallet.openFinancialDetails();
//        Thread.sleep(1000);
//        wallet.resetTRXButton.click();
//        assertEquals(RESET_TRX_PASSWORD_EN, wallet.h1.getText());
//        wallet.resetTrxButton.click();
//        assertEquals(REQUIRED_PASSPHRASE_EN, wallet.resetTrxPassphraseRequiredError.getText());
//        wallet.resetTrxPassphraseField.click();
//        wallet.type(wallet.resetTrxPassphraseField, "111");
//        wallet.resetTrxButton.click();
//        wallet.resetTrxButton.click();
//        assertEquals(REQUIRED_RESET_TRX_PASS_EN, wallet.newTRXPasswordError.getText());
//        assertEquals(REQUIRED_CONFIRM_TRX_PASS_EN, wallet.checkTRXPasswordError.getText());
//
//        wallet.footerLangZhButton.click();
//        wallet.passwordField.click();
//        wallet.type(wallet.passwordField, "123456Qwe");
//        wallet.confirmOpenDetailButtonButton.click();
//        Thread.sleep(1000);
//        wallet.resetTRXButton.click();
//        assertEquals(RESET_TRX_PASSWORD_ZH, wallet.h1.getText());
//        wallet.resetTrxButton.click();
//        assertEquals(REQUIRED_PASSPHRASE_ZH, wallet.resetTrxPassphraseRequiredError.getText());
//        wallet.resetTrxPassphraseField.click();
//        wallet.type(wallet.resetTrxPassphraseField, "111");
//        wallet.resetTrxButton.click();
//        wallet.resetTrxButton.click();
//        assertEquals(REQUIRED_RESET_TRX_PASS_ZH, wallet.newTRXPasswordError.getText());
//        assertEquals(REQUIRED_CONFIRM_TRX_PASS_ZH, wallet.checkTRXPasswordError.getText());
//    }
//
//    @Test
//    public void resetTrxPassWithInvalidPasswordFieldsFailure() throws InterruptedException {
//        wallet.openFinancialDetails();
//        Thread.sleep(1000);
//        wallet.resetTRXButton.click();
//        wallet.resetTrxPassphraseField.click();
//        wallet.type(wallet.resetTrxPassphraseField, "111");
//        wallet.resetTrxButton.click();
//        wallet.resetTrxPassword("1", "1");
//        assertEquals(INVALID_WITHOUT_UPPERCASE_PASSWORD_EN, wallet.newTRXPasswordError.getText());
//        wallet.resetTrxPassword("1Q", "1");
//        assertEquals(INVALID_WITHOUT_LOWERCASE_PASSWORD_EN, wallet.newTRXPasswordError.getText());
//        wallet.resetTrxPassword("1Qw", "1");
//        assertEquals(INVALID_SHORT_PASSWORD_EN, wallet.newTRXPasswordError.getText());
//        assertEquals(TRX_PASS_DO_NOT_MATCH_EN, wallet.checkTRXPasswordError.getText());
//
//        wallet.footerLangZhButton.click();
//        wallet.passwordField.click();
//        wallet.type(wallet.passwordField, "123456Qwe");
//        wallet.confirmOpenDetailButtonButton.click();
//        Thread.sleep(1000);
//        wallet.resetTRXButton.click();
//        wallet.resetTrxPassphraseField.click();
//        wallet.type(wallet.resetTrxPassphraseField, "111");
//        wallet.resetTrxButton.click();
//        wallet.resetTrxPassword("1", "1");
//        assertEquals(INVALID_WITHOUT_UPPERCASE_PASSWORD_ZH, wallet.newTRXPasswordError.getText());
//        wallet.resetTrxPassword("1Q", "1");
//        assertEquals(INVALID_WITHOUT_LOWERCASE_PASSWORD_ZH, wallet.newTRXPasswordError.getText());
//        wallet.resetTrxPassword("1Qw", "1");
//        assertEquals(INVALID_SHORT_PASSWORD_ZH, wallet.newTRXPasswordError.getText());
//        assertEquals(TRX_PASS_DO_NOT_MATCH_ZH, wallet.checkTRXPasswordError.getText());
//    }
//
//    @Test
//    public void resetTrxPassWithIncorrectPassphraseFailure() throws InterruptedException {
//        wallet.openFinancialDetails();
//        Thread.sleep(1000);
//        wallet.showPassphraseButton.click();
//        wallet.showPassphraseTrxPassField.click();
//        wallet.type(wallet.showPassphraseTrxPassField, "123456Qwe");
//        wallet.resetTrxButton.click();
//        Thread.sleep(1000);
//        String passphrase = wallet.passphraseField.getText();
//
//        wallet.resetTRXButton.click();
//        wallet.resetTrxPassphraseField.click();
//        wallet.type(wallet.resetTrxPassphraseField, "1111");
//        wallet.resetTrxButton.click();
//        wallet.resetTrxPassword("123456Qwe", "123456Qwe");
//        Thread.sleep(1000);
//        assertEquals(PASSPHRASE_2_ATTEMPTS_EN, wallet.resetTrxAttempts.getText());
//
//        wallet.footerLangZhButton.click();
//        wallet.passwordField.click();
//        wallet.type(wallet.passwordField, "123456Qwe");
//        wallet.confirmOpenDetailButtonButton.click();
//        Thread.sleep(1000);
//        wallet.resetTRXButton.click();
//        wallet.resetTrxPassphraseField.click();
//        wallet.type(wallet.resetTrxPassphraseField, "1111");
//        wallet.resetTrxButton.click();
//        wallet.resetTrxPassword("123456Qwe", "123456Qwe");
//        Thread.sleep(1000);
//        assertEquals(PASSPHRASE_1_ATTEMPTS_ZH, wallet.resetTrxAttempts.getText());
//
//        wallet.backButton.click();
//        wallet.type(wallet.resetTrxPassphraseField, passphrase);
//        wallet.resetTrxButton.click();
//        wallet.resetTrxPassword("123456Qwe", "123456Qwe");
//        assertEquals(PROCCED_ZH, wallet.resetTrxButton.getText());
//    }


//CHANGE TRX PASSWORD
//    @Test
//    public void changeTrxPasswordWithoutAnyFieldsFailure() throws InterruptedException {
//        wallet.openFinancialDetails();
//        Thread.sleep(1000);
//        wallet.trxPassTab.click();
//
//    }





}