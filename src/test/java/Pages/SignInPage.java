package Pages;

import Data.UserData;
import Utils.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends Page {
    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void open() {
        String url = Config.getProperty("url");
        driver.get(url + "/en/sign-in");
    }

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[1]")
    public WebElement emailError;

    @FindBy(xpath = "//input[@type='email']")
    public WebElement emailField;

    @FindBy(xpath = "(//div[parent::div[@class='h2 h2-error']])[2]")
    public WebElement passwordError;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordField;

    @FindBy(css = ".ico-input")
    public WebElement showPasswordButton;

    @FindBy(xpath = "//div[@class='ico-input ico-input-active']")
    public WebElement showenPasswordField;

    @FindBy(xpath = "//button[parent::div[@class='mt-input']]")
    public WebElement signInButton;

    @FindBy (css = ".h1")
    public WebElement h1;

    public void loginAs(UserData user) {
        type(emailField, user.getEmail());
        type(passwordField, user.getPassword());
        signInButton.click();
        wait.until(ExpectedConditions.visibilityOf(profileLink));
    }
}
