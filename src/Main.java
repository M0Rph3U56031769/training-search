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
        String training_text;
        String searchengine_url;
        String google_input_field;

        String google_search_button;
        String google_category_videos;
        int name_counter=1;
        int link_counter=1;

        Google google_properties = new Google();

        searchengine_url = google_properties.getGoogle_url();
        google_input_field = google_properties.getInput_field();
        training_text = google_properties.getTraining_text();
        google_search_button = google_properties.getGoogle_search_button();
        google_category_videos = google_properties.getGoogle_category_videos();
        ArrayList<Object> google_searchresult_name = new ArrayList<Object>(google_properties.getGoogle_searchresult_name());
        ArrayList<Object> google_searchresult_link = new ArrayList<Object>(google_properties.getGoogle_searchresult_link());


        FirefoxDriver driver=new FirefoxDriver();

        driver.get(searchengine_url);


//        fill the input field
        WebElement element=driver.findElement(By.cssSelector(google_input_field));
        element.sendKeys(training_text);

//        click on search button
        WebElement button=driver.findElement(By.cssSelector(google_search_button));
        button.click();

//        switch to videos
        WebElement vidi = driver.findElement(By.cssSelector(google_category_videos));
        vidi.click();

//        Collecting the 3 training links
        WebElement firstCourseName = driver.findElement(By.cssSelector(google_searchresult_name.get(0) + Integer.toString(name_counter) + google_searchresult_name.get(1)));
        String name1 = firstCourseName.getText();
        String name1Converted = name1.replace(',','-');
        System.out.println(name1Converted);
        names.add(name1Converted);
        name_counter++;

        WebElement firstCourseLink = driver.findElement(By.cssSelector(google_searchresult_link.get(0) + Integer.toString(link_counter) + google_searchresult_link.get(1)));
        String link1 = firstCourseLink.getAttribute("href");
        System.out.println(link1);
        links.add(link1);
        link_counter++;

        WebElement secondCourseName = driver.findElement(By.cssSelector(google_searchresult_name.get(0) + Integer.toString(name_counter) + google_searchresult_name.get(1)));
        String name2 = secondCourseName.getText();
        String name2Converted = name2.replace(',','-');
        System.out.println(name2Converted);
        names.add(name2Converted);
        name_counter++;


        WebElement secondcourse = driver.findElement(By.cssSelector(google_searchresult_link.get(0) + Integer.toString(link_counter) + google_searchresult_link.get(1)));
        String link2 = secondcourse.getAttribute("href");
        System.out.println(link2);
        links.add(link2);
        name_counter++;

        WebElement thirdCourseName = driver.findElement(By.cssSelector(google_searchresult_name.get(0) + Integer.toString(name_counter) + google_searchresult_name.get(1)));
        String name3 = thirdCourseName.getText();
        String name3Converted = name3.replace(',','-');
        System.out.println(name3Converted);
        names.add(name3Converted);

        WebElement thirdcourse = driver.findElement(By.cssSelector(google_searchresult_link.get(0) + Integer.toString(link_counter) + google_searchresult_link.get(1)));
        String link3 = thirdcourse.getAttribute("href");
        System.out.println(link3);
        links.add(link3);


        driver.quit();

        Csvhandler toFile = new Csvhandler();
        toFile.csvhandler(names, links);

    }
}