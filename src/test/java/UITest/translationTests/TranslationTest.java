package UITest.translationTests;

import UITest.BaseTest;
import Utils.Config;
import org.testng.Reporter;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TranslationTest extends BaseTest {




    public void open(String locale, String page){
        String url = Config.getProperty("url");
        driver.get(url + "/" + locale + "/" + page);
    }

    public void open(String locale){
        String url = Config.getProperty("url");
        driver.get(url + "/" + locale);
    }

    public enum Locale {
        en("src/test/resources/translationEN.csv"), zh("src/test/resources/translationZH.csv");
        Locale(String path){
            this.path = path;
        }
        private String path;
    }

    public static Map<String, String> readTranslationFile( Locale locale) throws IOException {
        Map<String,String> translation = new HashMap<String, String>();
        BufferedReader reader = null;
        try{
            reader  = new BufferedReader(new InputStreamReader(new FileInputStream(locale.path)));
            String line;
            line=reader.readLine();
            while (line != null){
                String [] split = line.split(";");
                translation.put(split[0], split[1]);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return translation;

    }

    public void logBeforeMethod(Method m, Object[] p){
        Reporter.log("test start " + m.getName(),true);
    }


    public void logAfteMethod(Method m, Object[] p){
        Reporter.log("test finished",true) ;
    }
}
