package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public abstract class Page {

    protected static WebDriver driver;
    protected static WebDriverWait wait;


    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    @FindBy(css = ".h1")
    public WebElement title;

    @FindBy(tagName = "h1")
    public WebElement h1;

    @FindBy(xpath = "(//p[@class='text-left h1'])[1]")
    public WebElement h1Game;

    @FindBy(xpath = "//a[@href='/sign-in']")
    public WebElement signIn;

    @FindBy(xpath = "//a[@href='/sign-up']")
    public WebElement signUpButton;

    @FindBy(xpath = "//a[@class='header-logout-link']")
    public WebElement logOutButton;

    @FindBy(xpath = "//a[@href='/profile']")
    public WebElement profileLink;

    @FindBy(xpath = "(//li[parent::ul[@class='language']])[3]")
    public WebElement footerLangEnButton;

    @FindBy(xpath = "(//li[parent::ul[@class='language']])[2]")
    public WebElement footerLangZhChButton;

    @FindBy(xpath = "(//li[parent::ul[@class='language']])[1]")
    public WebElement footerLangZhButton;

    @FindBy(css = ".terms")
    public WebElement termsAndConditionsLink;

    @FindBy(css = ".policy")
    public WebElement faqLink;

    @FindBy(css = ".support")
    public WebElement contactUsLink;

    @FindBy(tagName = "h2")
    public WebElement diamondTraiding;

    @FindBy(xpath = "//a[@href='/diamond-trading']")
    public WebElement diamondTraidingButton;

    @FindBy(xpath = "//div[@class = 'custom-link']")
    public WebElement btcTradeButton;

    @FindBy(xpath = "//a[@href='/marketplace/offers']")
    public WebElement marketplaceButton;

    @FindBy(xpath = "//a[@href = '/wealth']")
    public WebElement walletButton;

    @FindBy(xpath = "//a[@href = '/referral-tree']")
    public WebElement referralButton;

    @FindBy(xpath = "//div[@class='h2 h2-error']")
    public WebElement error;

    @FindBy(xpath = "//button[@class = 'btn btn-primary btn-2x btn-width']")
    public WebElement button;


    public void type(WebElement webElement, String text) {
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
    }
    public void type(WebElement webElement, Keys keys) {
        webElement.click();
        webElement.sendKeys(keys);
    }


    public void select(WebElement webElement, String optionLocator) {
        Select dropdown = new Select(webElement);
        dropdown.selectByVisibleText(optionLocator);
    }

    public abstract void open();

    public void isElementPresent(int seconds, WebElement locator){
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clicOnLink(WebElement Element){
        Element.click();
        wait.until(ExpectedConditions.stalenessOf(Element));
    }


}
