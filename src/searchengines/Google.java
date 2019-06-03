package searchengines;

import com.opencsv.CSVReader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
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
    private String google_csv="\\src\\searchengines\\google.csv";


    public Google()throws IOException {
        String cwd = new File("").getAbsolutePath();
        String google_csv = cwd+this.google_csv;

//        Read CSV from file
        try (CSVReader csvReader = new CSVReader(new FileReader(google_csv))) {

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

    @Contract(pure = true)
    private String getGoogle_url(){ return this.google_url; }
    @Contract(pure = true)
    private String getTraining_text(){
        return this.training_text;
    }
    @Contract(pure = true)
    private String getInput_field() { return this.input_field; }
    @Contract(pure = true)
    private String getGoogle_search_button() {return this.google_search_button; }
    @Contract(pure = true)
    private String getGoogle_category_videos() { return this.google_category_videos; }
    public List getGoogle_searchresult_name() { return google_searchresult_name; }
    public List getGoogle_searchresult_link() { return google_searchresult_link; }


    public FirefoxDriver initBrowser(){
        FirefoxDriver driver=new FirefoxDriver();
        this.goStartPage(driver);
        this.fillInputField(driver);
        this.clickSearchButton(driver);
        this.switchCategoryToVideo(driver);
        return driver;
    }

    @Contract("_ -> param1")
    private FirefoxDriver goStartPage(@NotNull FirefoxDriver browser){
        browser.get(this.getGoogle_url());
        return browser;
    }
    @Contract("_ -> param1")
    private FirefoxDriver fillInputField(@NotNull FirefoxDriver browser) {
        WebElement element=browser.findElement(By.cssSelector(this.getInput_field()));
        element.sendKeys(this.getTraining_text());
        return browser;
    }
    @Contract("_ -> param1")
    private FirefoxDriver clickSearchButton(@NotNull FirefoxDriver browser){
        WebElement button=browser.findElement(By.cssSelector(this.getGoogle_search_button()));
        button.click();
        return browser;
    }
    @Contract("_ -> param1")
    private FirefoxDriver switchCategoryToVideo(@NotNull FirefoxDriver browser){
        WebElement vidi = browser.findElement(By.cssSelector(this.getGoogle_category_videos()));
        vidi.click();
        return browser;
    }

}
