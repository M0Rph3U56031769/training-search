package searchengines;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Google {

    public String url;
    public String trainingtext;

    public Google()throws IOException {

//        Read CSV from file
        List records = new ArrayList();

        CSVReader csvReader = new CSVReader(new FileReader("C:\\Users\\Daniel_Nagy1\\IdeaProjects\\sel02\\src\\searchengines\\google.csv"));

        List url_elements = new ArrayList();
        url_elements.addAll(Arrays.asList(csvReader.readNext()));
        this.url = url_elements.get(1).toString();

        this.trainingtext = Arrays.asList(csvReader.readNext()).get(1).toString();
    }

    public String getUrl(){
        System.out.println("Google URL: "+this.url);
        return this.url;
    }

    public String getTrainingtext(){
        return this.trainingtext;
    }

}
