package UITest.translationTests;

import Pages.SignInPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class GeneralTranslationTests extends TranslationTest {

    private SignInPage page = PageFactory.initElements(getWebDriver(), SignInPage.class);



    String locale;
    Map<String, String> translation = null;


    @Parameters({"locale"})
    @BeforeClass
    public String getLocale(@Optional("en") String localeFromParameters) {
        this.locale = localeFromParameters;
        return locale;
    }

    @BeforeClass
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


    @BeforeMethod(alwaysRun = true)
    public void logBeforeMethod(Method m, Object[] p){
        Reporter.log("test start " + m.getName(),true);
    }


    @AfterMethod(alwaysRun = true)
    public void logAfteMethod(Method m, Object[] p){
        Reporter.log("test finished",true) ;
    }

    @Test
    public void openingSignInPageSuccess() {
        open(locale, "sign-in");
        assertEquals(page.title.getText(), translation.get("SIGNIN_H1"));
    }

    @Test
    public void openingSignUpPageSuccess() {
        open(locale, "sign-up");
        assertEquals(page.title.getText(), translation.get("SIGNUP_H1"));
    }

    @Test
    public void openingTermsPageSuccess() {
        open(locale, "terms-and-conditions");
        assertEquals(page.h1.getText(), translation.get("TERMS_H1"));
    }

    @Test
    public void openingFaqPAge() {
        open(locale, "faq");
        assertEquals(page.h1.getText(), translation.get("FAQ_H1"));
    }

    @Test
     public void openingContactsPage() {
        open(locale, "contact");
        assertEquals(page.h1.getText(), translation.get("CONTACTS_H1"));
    }

    @Test
    public void openingDiamondTraidingPage() {
        open(locale, "diamond-trading");
        assertEquals(page.diamondTraiding.getText(), translation.get("DIAMOND_H1"));
    }

    @Test
    public void openingBtcTradePage() {
        open(locale, "marketplace/offers");
        assertEquals(page.title.getText(), translation.get("MARKETPLACE_H1"));
    }





}
