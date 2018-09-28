package Pages;

import Utils.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.security.PublicKey;

public class WalletPage extends Page {
    public WalletPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void open() {
        String url = Config.getProperty("url");
        driver.get(url + "/en/wealth");
    }

    //WALLET
    @FindBy(css = ".btc-wallet.h1")
    public WebElement btcWallet;

    @FindBy(xpath = "(//a[@class='btn btn-primary btn-2x btn-width'])[1]")
    public WebElement financialDetailsButton;

    @FindBy(xpath = "(//a[@class='btn btn-primary btn-2x btn-width'])[2]")
    public WebElement buyGecButton;

    @FindBy(xpath = "(//a[@class='btn btn-primary btn-2x btn-width'])[3]")
    public WebElement sellGedButton;

    @FindBy(xpath = "//button[@class='btn btn-secondary btn-2x btn-width']")
    public WebElement refreshButton;

    @FindBy(xpath = "//button[@class='btn btn-secondary btn-2x btn-width btn-spinner btn-timer']")
    public WebElement spinnerButton;

    @FindBy(xpath = "//button[@class = 'btn btn-primary btn-2x btn-width btn-success btn-disabled']")
    public WebElement successButton;




    //SELL GED
    @FindBy(name = "amount")
    public WebElement amountField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[1]")
    public WebElement amountError;

    @FindBy(name = "btcAmount")
    public WebElement btcAmountField;

    @FindBy(name = "btcWalletAddress")
    public WebElement btcWalletAddressField;

    @FindBy(name = "trxPass")
    public WebElement trxPassField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[2]")
    public WebElement trxPassError;

    @FindBy(xpath = "(//div[@class = 'h2'])[2]")
    public WebElement insufficientGedError;


    //BUY GEC
    @FindBy(xpath = "//div[@class = 'h2 text-center']")
    public WebElement gecRate;

    @FindBy(name = "gecAmount")
    public WebElement gecAmountField;

    @FindBy(xpath = "//div[@class='h2 h2-error']")
    public WebElement gecAmountError;

    @FindBy(xpath = "//a[@class='btn btn-primary btn-width btn-2x']")
    public WebElement readyToPayButton;

    @FindBy(xpath = "//a[@class='color-link copy-link']")
    public WebElement copyLink;

    @FindBy(xpath = "//a[@class='color-link copy-link-green']")
    public WebElement copiedLink;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-2x btn-width btn-success']")
    public WebElement successPaidButton;


    //FINANCIAL DETAILS
    @FindBy(name = "password")
    public WebElement passwordField;

    @FindBy(xpath = "//div[@class='h2 h2-error']")
    public WebElement passwordError;

    @FindBy(xpath = "//p[@class='color-red xs-gutter-top']")
    public WebElement passwordAttempts;

    @FindBy(id = "react-tabs-14")
    public WebElement passphraseTab;


    //EDIT BTC ADDRESS
    @FindBy(xpath = "//p[@class='xs-gutter-top wealth-btc']")
    public WebElement btcAddress;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement editBtcAddressButton;

    @FindBy(name = "address")
    public WebElement editBtcAddressField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[1]")
    public WebElement editBtcAddressError;

    @FindBy(name = "password")
    public WebElement changeBtcTrxPassField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[2]")
    public WebElement changeBtcTrxPassError;

    @FindBy(xpath = "//p[@class='xs-gutter-top color-red']")
    public WebElement changeBtcTrxPassAttempts;

    @FindBy(xpath = "(//button[@class='btn btn-primary btn-2x btn-width'])[1]")
    public WebElement editButton;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-success']")
    public WebElement successEditButton;


    //SHOW PASSPHRASE
    @FindBy(name = "password")
    public WebElement showPassphraseTrxPassField;

    @FindBy(xpath = "//div[@class = 'h2 h2-error']")
    public WebElement showPassphraseTrxPassError;

    @FindBy(xpath = "//div[@class = 'input-box']")
    public WebElement passphraseField;


    //RESET TRX PASSWORD
    @FindBy(xpath = "//a[@class = 'reset-trx-pass xs-gutter-top']/span")
    public WebElement resetTRXButton;

    @FindBy(name = "textarea")
    public WebElement resetTrxPassphraseField;

    @FindBy(xpath = "//div[@class = 'h2 h2-error']")
    public WebElement resetTrxPassphraseError;

    @FindBy(name = "newTRXPassword")
    public WebElement newTRXPasswordField;

    @FindBy(xpath = "(//div[@class = 'h2 h2-error'])[1]")
    public WebElement newTRXPasswordError;

    @FindBy(name = "checkTRXPassword")
    public WebElement checkTRXPasswordField;

    @FindBy(xpath = "(//div[@class = 'h2 h2-error'])[2]")
    public WebElement checkTRXPasswordError;

    @FindBy(xpath = "//a[@class = 'reset-trx-pass xs-gutter-top']")
    public WebElement backButton;

    @FindBy(xpath = "//p[@class = 'xs-gutter-top color-red']")
    public WebElement attempts;

    //CHANGE TRX PASSWORD
    @FindBy(xpath = "//li[@class = 'react-tabs__tab']")
    public WebElement trxPassTab;

    @FindBy(name = "currentPassword")
    public WebElement changeTRXCurrentPassField;

    @FindBy(xpath = "(//div[@class = 'h2 h2-error'])[1]")
    public WebElement changeTRXCurrentPassError;

    @FindBy(name = "newPassword")
    public WebElement changeTRXNewPassField;

    @FindBy(xpath = "(//div[@class = 'h2 h2-error'])[2]")
    public WebElement changeTRXNewPassError;

    @FindBy(name = "checkPassword")
    public WebElement changeTRXConfirmPassField;

    @FindBy(xpath = "(//div[@class = 'h2 h2-error'])[3]")
    public WebElement changeTRXConfirmPassError;


    public void sellGed(String amount, String trxPass) {
        type(amountField, amount);
        type(trxPassField, trxPass);
        button.click();
    }

    public void openFinancialDetails(){
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        type(passwordField, "123456Qwe");
        button.click();
    }

    public void resetTrxPassword(String resetPassword, String confirmResetPassword) {
        newTRXPasswordField.click();
        type(newTRXPasswordField, resetPassword);
        type(checkTRXPasswordField, confirmResetPassword);
    }
}