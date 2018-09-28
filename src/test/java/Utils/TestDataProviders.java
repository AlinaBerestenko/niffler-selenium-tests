package Utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDataProviders {
    @DataProvider(name = "passwordFieldCheckedData")
    public Iterator<Object[]> passwordFieldCheckedData() throws IOException {
        return loadFieldCheckedData("src/test/resources/for_translation/password data.csv");
    }

    @DataProvider(name = "emailFieldCheckedData")
    public Iterator<Object[]> emailFieldCheckedData() throws IOException {
        return loadFieldCheckedData("src/test/resources/for_translation/email data.csv");
    }

    @DataProvider(name = "GEDamountFieldCheckedData")
    public Iterator<Object[]> GEDamountFieldCheckedData() throws IOException {
        return loadFieldCheckedData("src/test/resources/for_translation/GED amount data.csv");
    }

    @DataProvider(name = "GECamountFieldCheckedData")
    public Iterator<Object[]> GECamountFieldCheckedData() throws IOException {
        return loadFieldCheckedData("src/test/resources/for_translation/GEC amount data.csv");
    }

    @DataProvider(name = "BTCaddressFieldCheckedData")
    public Iterator<Object[]> BTCaddressFieldCheckedData() throws IOException {
        return loadFieldCheckedData("src/test/resources/for_translation/btc address data.csv");
    }

    private Iterator<Object[]> loadFieldCheckedData(String filePath) throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader( new FileReader(new File(filePath)));
        String line = reader.readLine();
        while (line != null){
            String[] split =  line.split(";");
            list.add(new Object[]{split[0], split[1]});
            line = reader.readLine();
        }
        return list.iterator();
    }
}