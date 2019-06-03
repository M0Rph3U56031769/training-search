import org.openqa.selenium.firefox.FirefoxDriver;
import searchengines.Google;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Google google_properties = new Google();

//        Init webdriver
        FirefoxDriver browser = google_properties.initBrowser(3);
        browser.quit();

        System.out.println("NAMES FINAL: "+google_properties.getGoogle_searchresult_name().toString());
        System.out.println("LINKS FINAL: "+google_properties.getGoogle_searchresult_link().toString());
        Google.writeResultsToCsv(google_properties.getGoogle_searchresult_name(),google_properties.getGoogle_searchresult_link());
    }
}