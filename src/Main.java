import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import searchengines.Google;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> links = new ArrayList<>();
//        String training_text;
//        String searchengine_url;
//        String google_input_field;
//
//        String google_search_button;
//        String google_category_videos;
        int name_counter=1;
        int link_counter=1;
        int course_amount=3;

        Google google_properties = new Google();

//        searchengine_url = google_properties.getGoogle_url();
//        google_input_field = google_properties.getInput_field();
//        training_text = google_properties.getTraining_text();
//        google_search_button = google_properties.getGoogle_search_button();
//        google_category_videos = google_properties.getGoogle_category_videos();
        ArrayList<Object> google_searchresult_name = new ArrayList<Object>(google_properties.getGoogle_searchresult_name());
        ArrayList<Object> google_searchresult_link = new ArrayList<Object>(google_properties.getGoogle_searchresult_link());

//        Init webdriver
        FirefoxDriver browser = google_properties.initBrowser();


//        Collecting the 3 training links
        for (int i=0; i<course_amount; i++){
            WebElement firstCourseName = browser.findElement(By.cssSelector(google_searchresult_name.get(0) + Integer.toString(name_counter) + google_searchresult_name.get(1)));
            String name1 = firstCourseName.getText();
            String name1Converted = name1.replace(',','-');
            System.out.println(name1Converted);
            names.add(name1Converted);
            name_counter++;

            WebElement firstCourseLink = browser.findElement(By.cssSelector(google_searchresult_link.get(0) + Integer.toString(link_counter) + google_searchresult_link.get(1)));
            String link1 = firstCourseLink.getAttribute("href");
            System.out.println(link1);
            links.add(link1);
            link_counter++;
        }

        browser.quit();

        Csvhandler toFile = new Csvhandler();
        toFile.csvhandler(names, links);

    }
}