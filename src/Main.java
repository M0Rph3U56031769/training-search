import org.openqa.selenium.firefox.FirefoxDriver;
import trainingsearch.searchengines.Google;

import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws IOException {

        Google google_properties = new Google();

//        Init webdriver
        try {
            FirefoxDriver browser = google_properties.initBrowser(700);
            browser.quit();
        }
        catch (NoSuchElementException e){
            System.exit(1);
        }


        System.out.println("NAMES FINAL: "+google_properties.getGoogle_searchresult_name().toString());
        System.out.println("LINKS FINAL: "+google_properties.getGoogle_searchresult_link().toString());
        Google.writeResultsToCsv(google_properties.getGoogle_searchresult_name(),google_properties.getGoogle_searchresult_link());
    }
}