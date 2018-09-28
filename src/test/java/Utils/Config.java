package Utils;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Config {

    private static Properties PROPERTIES;

    static{
        PROPERTIES = new Properties();
        URL prop = ClassLoader.getSystemResource("config");
        try{
            PROPERTIES.load(prop.openStream());
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return PROPERTIES.getProperty(key);
    }
}
