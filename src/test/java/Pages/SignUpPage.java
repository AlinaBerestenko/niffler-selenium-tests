package Pages;

import Data.UserData;
import Utils.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignUpPage extends Page {
    public SignUpPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void open() {
        String url = Config.getProperty("url");
        driver.get(url + "/en/sign-up");
    }

    @FindBy(css = ".hint-text")
    public WebElement hintText;

    @FindBy(id = "react-tabs-0")
    public WebElement withoutIntroTab;

    @FindBy(xpath = "//span[text()='referral link']")
    public WebElement withReferralTab;

    @FindBy(xpath = "//*[@class='h2-image ']/div")
    public WebElement imageText;

    @FindBy(xpath = "//*[@id=\"react-tabs-1\"]/div/div/div[1]/div[1]/div/div/span")
    public WebElement imageError;

    @FindBy(xpath = ".//input[@type='file']")
    public WebElement imageUploadButton;

    @FindBy(xpath = "//input[@name='nickName']")
    public WebElement usernameField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[1]")
    public WebElement usernameRequiredError;

    @FindBy(xpath ="(//div[@class='h2 h2-error'])[1]")
    public WebElement usernameError;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[2]")
    public WebElement dateRequiredField;

    @FindBy(css = "div.wr-select.wr-select-date.select-day")
    public WebElement dateField;

    @FindBy(css = "div.wr-select.wr-select-date.select-month")
    public WebElement monthField;

    @FindBy(css = "div.wr-select.wr-select-date.select-year")
    public WebElement yearField;

    @FindBy(xpath = "//input[@name='loginEmail']")
    public WebElement emailField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[3]")
    public WebElement emailRequiredError;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[3]")
    public WebElement emailExistsError;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[4]")
    public WebElement passwordRequiredError;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[4]")
    public WebElement passwordError;

    @FindBy(xpath = "//input[@name='checkPassword']")
    public WebElement passwordConfirmField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[5]")
    public WebElement confirmPasswordRequiredError;

    @FindBy(xpath = "//label[@for='link-form']/span")
    public WebElement agreeCheckbox;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[6]")
    public WebElement agreeCheckboxRequiredError;

    @FindBy(xpath = "//label//a[contains(@href,'/terms-and-conditions')]")
    public WebElement termsLink;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-2x btn-width']")
    public WebElement signUpButton;

    @FindBy(css = "label div")
    public WebElement checkboxAgreeText;

    @FindBy(css= ".column.text-center ")
    public  WebElement haveAnAccount;

    //WITH INTRO

    @FindBy(id = "react-tabs-2")
    public WebElement withIntroTab;

    @FindBy(xpath = "//input[@name='intrEmail']")
    public WebElement introEmailField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[1]")
    public WebElement introEmailRequiredError;

    @FindBy(xpath = "//input[@name='intrNickName']")
    public WebElement introUsernameField;

    @FindBy(id = "react-tabs-4")
    public WebElement referralTab;

    @FindBy(css = "textarea[name='textarea']")
    public WebElement referralField;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[1]")
    public WebElement referralRequiredError;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[1]")
    public WebElement referralWrongError;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-2x btn-width']")
    public WebElement referralNextButton;



    //SECURITY QUESTIONS
    @FindBy(css = "p.h3")
    public WebElement securityQuestionstitle;

    @FindBy(css = "span#react-select-q1--value div.Select-placeholder")
    public WebElement question1;

    @FindBy(css = ".mt-input:first-child .wr-select.wr-input.select-error .h2.h2-error" )
    public WebElement question1RequiredError;

    @FindBy(css = "input[name='answerId1']")
    public WebElement answer1;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[1]")
    public WebElement answer1RequiredError;

    @FindBy(css = "span#react-select-q2--value div.Select-placeholder")
    public WebElement question2;

    @FindBy(css = ".mt-input:nth-child(2) .wr-select.wr-input.select-error .h2.h2-error")
    public WebElement question2RequiredError;

    @FindBy(css = "input[name='answerId2']")
    public WebElement answer2;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[2]")
    public WebElement answer2RequiredError;

    @FindBy(css = "span#react-select-q3--value div.Select-placeholder")
    public WebElement question3;

    @FindBy(css = ".mt-input:nth-child(3) .wr-select.wr-input.select-error .h2.h2-error")
    public WebElement question3RequiredError;

    @FindBy(css = "input[name='answerId3']")
    public WebElement answer3;

    @FindBy(xpath = "(//div[@class='h2 h2-error'])[3]")
    public WebElement answer3RequiredError;

    @FindBy(css = "div>a.color-link")
    public WebElement previousLink;

    @FindBy(css = "span>a.color-link")
    public WebElement signInLink;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-2x btn-width']")
    public WebElement confirmButton;

    //SUCCESS PAGE
    @FindBy(css = ".markdown>h1")
    public WebElement accountCreatedText1; // for all lang(en/cz)

    @FindBy(css = "div.markdown p:nth-child(2)")
    public WebElement accountCreatedText2;

    //@FindBy(xpath = "//*[@id=\"app\"]/div/div/content/div/div/div/div/div/div/div[1]/span")
    //public WebElement accountCreatedTextZH;

    @FindBy(css = "p a[href^='/game']")
    public WebElement closeButton;





    public void registerWithSubmit(UserData user) {
        Actions actions = new Actions(driver);

        type(usernameField, user.getUsername());
        dateField.click();
        actions.moveToElement(dateField).moveByOffset(5, 60).click().build().perform();
        monthField.click();
        actions.moveToElement(monthField).moveByOffset(5, 60).click().build().perform();
        yearField.click();
        actions.moveToElement(yearField).moveByOffset(5, 60).click().build().perform();
        type(emailField, user.getEmail());
        type(passwordField, user.getPassword());
        type(passwordConfirmField, user.getConfirmPassword());
        agreeCheckbox.click();
        wait.until(ExpectedConditions.visibilityOf(signUpButton));
        signUpButton.click();
}

    public void registerWithoutSubmit(UserData user) {
        Actions actions = new Actions(driver);

        type(usernameField, user.getUsername());
        dateField.click();
        type(emailField, user.getEmail());
        type(passwordField, user.getPassword());
        type(passwordConfirmField, user.getConfirmPassword());
    }

    public void uploadImage(String file) throws InterruptedException {
        imageUploadButton.sendKeys(file);
        Thread.sleep(1000);
        signUpButton.click();
    }

    public void fillingQuestionsAndAnswers() throws InterruptedException {
        Actions actions = new Actions(driver);

        question1.click();
        wait.until(ExpectedConditions.visibilityOf(question1));
        actions.moveToElement(question1).moveByOffset(5, 60).click().build().perform();
        type(answer1, "1");

        question2.click();
        wait.until(ExpectedConditions.visibilityOf(question2));
        actions.moveToElement(question2).moveByOffset(5, 60).click().build().perform();
        type(answer2, "2");

        question3.click();
        wait.until(ExpectedConditions.visibilityOf(question3));
        actions.moveToElement(question3).moveByOffset(5, 60).click().build().perform();
        type(answer3, "3");
    }


}
