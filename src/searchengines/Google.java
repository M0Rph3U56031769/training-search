package searchengines;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Google {

    public String url;

    public void init()throws IOException {
        List records = new ArrayList();
        try (CSVReader csvReader = new CSVReader(new FileReader("google.csv"))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }
        this.url = records.get(1).toString();
    }

    public String getUrl(){
        String address=null;

        return address;
    }

}
