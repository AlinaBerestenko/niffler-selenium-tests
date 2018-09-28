package Pages;

import Data.PasswordData;
import Data.UserData;
import Utils.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends Page {
    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void open() {
        String url = Config.getProperty("url");
        driver.get(url + "/en/profile");
    }

    //PROFILE
    @FindBy(xpath = "//a[parent::div[@class='text-right']]")
    public WebElement copyLinkButton;

    @FindBy(xpath = "(//div[@class='value-row'])[1]")
    public WebElement usernameText;

    @FindBy(xpath = "(//div[@class='value-row'])[2]")
    public WebElement emailText;

    @FindBy(xpath = "(//div[@class='value-row'])[3]")
    public WebElement dateText;

    @FindBy(xpath = "(//div[@class='value-row'])[4]")
    public WebElement introducerEmail;

    @FindBy(xpath = "(//div[@class='value-row'])[5]")
    public WebElement introducerUserName;

    @FindBy(xpath = "//a[@href='/edit-personal']")
    public WebElement editProfileButton;

    //EDIT PASSWORD
    @FindBy(xpath = "//input[@name='currentPassword']")
    public WebElement currentPasswordField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[1]")
    public WebElement oldPasswordRequiredError;

    @FindBy(xpath = "//input[@name='newPassword']")
    public WebElement newPasswordField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[2]")
    public WebElement newPasswordRequiredError;

    @FindBy(xpath = "//input[@name='checkPassword']")
    public WebElement newPasswordConfirmField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[3]")
    public WebElement newPasswordConfirmRequiredError;

    @FindBy(xpath = "//p[@class='xs-gutter-top color-red']")
    public WebElement passwordAttemptsText;

    @FindBy(xpath = "//button[parent::div[@class='sm-gutter-top']]")
    public WebElement confirmChangingPasswordButton;

    @FindBy(xpath = "//button[@class = 'btn btn-primary btn-2x btn-width btn-success']")
    public WebElement successButton;


    //EDIT INFO
    @FindBy(xpath = "//input[@name='nickName']")
    public WebElement editUsernameField;

    @FindBy(xpath = "//span[@id='react-select-day--value']")
    public WebElement editDayField;

    @FindBy(xpath = "//span[@id='react-select-month--value']")
    public WebElement editMonthField;

    @FindBy(xpath = "//span[@id='react-select-year--value']")
    public WebElement editYearField;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-2x btn-width']")
    public WebElement saveChangesButton;

    @FindBy(xpath = "//a[@class='btn btn-2x btn-width color-link']")
    public WebElement cancelChangesButton;

    public void editPersonalInfo(UserData user) {
        Actions actions = new Actions(driver);

        type(editUsernameField, user.getUsername());
        editDayField.click();
        actions.moveToElement(editDayField).moveByOffset(5, 60).click().build().perform();
        editMonthField.click();
        actions.moveToElement(editMonthField).moveByOffset(5, 60).click().build().perform();
        editYearField.click();
        actions.moveToElement(editYearField).moveByOffset(5, 60).click().build().perform();
        saveChangesButton.click();
    }

    public void changePassword(PasswordData passwordData) {
        scrollToBottom();
        type(currentPasswordField, passwordData.getOldPassword());
        type(newPasswordField, passwordData.getNewPassword());
        type(newPasswordConfirmField, passwordData.getNewConfirmPassword());
    }
}
