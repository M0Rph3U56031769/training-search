package searchengines;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Google {

    private String google_url;
    private String input_field;
    private String training_text;
    private String google_search_button;
    private String google_category_videos;
    private List<String> google_searchresult_name;
    private List<String> google_searchresult_link;


    public Google()throws IOException {

//        Read CSV from file
        try (CSVReader csvReader = new CSVReader(new FileReader("C:\\Users\\Daniel_Nagy1\\IdeaProjects\\sel02\\src\\searchengines\\google.csv"))) {

            List<String> url_elements = new ArrayList<>(Arrays.asList(csvReader.readNext()));
            this.google_url = url_elements.get(1);
            this.input_field = Arrays.asList(csvReader.readNext()).get(1);
            this.training_text = Arrays.asList(csvReader.readNext()).get(1);
            this.google_search_button = Arrays.asList(csvReader.readNext()).get(1);
            this.google_category_videos = Arrays.asList(csvReader.readNext()).get(1);
            this.google_searchresult_name = new ArrayList<>();
            this.google_searchresult_name.add(Arrays.asList(csvReader.readNext()).get(1));
            this.google_searchresult_name.add(Arrays.asList(csvReader.readNext()).get(1));
            this.google_searchresult_link = new ArrayList<>();
            this.google_searchresult_link.add(Arrays.asList(csvReader.readNext()).get(1));
            this.google_searchresult_link.add(Arrays.asList(csvReader.readNext()).get(1));
        }

    }

    public String getGoogle_url(){ return this.google_url; }
    public String getTraining_text(){
        return this.training_text;
    }
    public String getInput_field() { return this.input_field; }
    public String getGoogle_search_button() {return this.google_search_button; }
    public String getGoogle_category_videos() { return this.google_category_videos; }
    public List getGoogle_searchresult_name() { return google_searchresult_name; }
    public List getGoogle_searchresult_link() { return google_searchresult_link; }

}
