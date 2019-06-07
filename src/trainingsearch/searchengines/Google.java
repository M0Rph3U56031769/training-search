package trainingsearch.searchengines;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import trainingsearch.Columns;
import trainingsearch.filehandling.Csvhandler;
import com.opencsv.CSVReader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Google {

    private String google_url;
    private String input_field;
    private String training_text;
    private String google_search_button;
    private String google_category_videos;
    private List<String> google_searchresult_name;
    private List<String> google_searchresult_link;
    private List<String> google_timestamp;
    private Boolean custom_text = false;
    private String curstomplatform = "";

    public Google(String training_text, String curstomplatform) throws Exception {
        this.curstomplatform = curstomplatform;
        this.training_text = training_text;
        this.custom_text = true;
        this.csvReader();
    }

    public Google(String training_text) throws Exception {
        this.training_text = training_text;
        this.custom_text = true;
        this.csvReader();
    }

    public Google() throws Exception {this.csvReader();}

    @Contract("null -> fail")
    private static void validateNull(@Nullable final Object object) throws Exception {
        if (object == null) {
            throw new Exception();
        }
    }

    private void csvReader() throws Exception {

        InputStream is = this.getClass().getClassLoader().getResourceAsStream("google.csv");
        Scanner scanner;
        validateNull(is);
        scanner = new Scanner(is, StandardCharsets.UTF_8.name());
        String text = scanner.useDelimiter("\\A").next();
        System.out.println("Loading google.csv as a Stream: \n\n"+text);
        CSVReader csvReader = new CSVReader(new StringReader(text));
        List<String> url_elements = new ArrayList<>(Arrays.asList(csvReader.readNext()));
        this.google_url = url_elements.get(1);
        this.input_field = Arrays.asList(csvReader.readNext()).get(1);
        if (!this.custom_text){ this.training_text = Arrays.asList(csvReader.readNext()).get(1);}
        else { csvReader.readNext(); }
        this.google_search_button = Arrays.asList(csvReader.readNext()).get(1);
        this.google_category_videos = Arrays.asList(csvReader.readNext()).get(1);
        this.google_searchresult_name = new ArrayList<>();
        this.google_searchresult_name.add(Arrays.asList(csvReader.readNext()).get(1));
        this.google_searchresult_name.add(Arrays.asList(csvReader.readNext()).get(1));
        this.google_searchresult_link = new ArrayList<>();
        this.google_searchresult_link.add(Arrays.asList(csvReader.readNext()).get(1));
        this.google_searchresult_link.add(Arrays.asList(csvReader.readNext()).get(1));
        this.google_timestamp = new ArrayList<>();
        this.google_timestamp.add(Arrays.asList(csvReader.readNext()).get(1));
        this.google_timestamp.add(Arrays.asList(csvReader.readNext()).get(1));
    }

    private static void writeResultsToCsv(List names, @NotNull List links, List timestamps) throws IOException{
        var toFile = new Csvhandler();
        toFile.csvhandler(names, links, timestamps);
    }

    private void readResults(int course_amount, FirefoxDriver driver) {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> links = new ArrayList<>();
        ArrayList<String> timestamps = new ArrayList<>();

        if (course_amount > 11){
            course_amount = 11;
        }

        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.g:nth-child(11) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > h3:nth-child(1)")));
//        driver.findElement(By.cssSelector("/html/body/div[5]/div[3]/div[11]/div/div/div/div[2]"));
//        driver.findElement(By.linkText("Videók"));

        for (int i=0; i<course_amount; i++){
            try {
                if ( i == 6 ){i=7;}
                WebElement timestamp = driver.findElement(By.cssSelector(google_timestamp.get(0) + (i+1) + google_timestamp.get(1)));
                String timestampvalue = timestamp.getText();

                try { timestamps.add(timestampvalue.substring(2));}
                catch (StringIndexOutOfBoundsException e){
                    timestamps.add("N/A");
                }

                WebElement firstCourseName = driver.findElement(By.cssSelector(google_searchresult_name.get(0) + (i+1) + google_searchresult_name.get(1)));
                String name1 = firstCourseName.getText();
                String name1Converted = name1.replace(',','-');
                System.out.println("[ course number: "+i+" - " + timestampvalue + " - "+name1Converted+" ]");
                names.add(name1Converted);



                WebElement firstCourseLink = driver.findElement(By.cssSelector(google_searchresult_link.get(0) + (i+1) + google_searchresult_link.get(1)));
                String link1 = firstCourseLink.getAttribute("href");
                System.out.println("[ "+link1+" ]\n");
                links.add(link1);
            }
            catch (org.openqa.selenium.NoSuchElementException e){
                throw new NoSuchElementException("The course_amount Value is too high. Use a number(int) between 1 and 10! Otherwise it will be limited to 11!");
            }
        }
        this.google_searchresult_name = names;
        this.google_searchresult_link = links;
        this.google_timestamp = timestamps;
    }

    @Contract(pure = true)
    private String getGoogle_url(){ return this.google_url; }
    @Contract(pure = true)
    private String getTraining_text(){ return this.training_text; }
    @Contract(pure = true)
    private String getInput_field() { return this.input_field; }
    @Contract(pure = true)
    private String getGoogle_search_button() {return this.google_search_button; }
    @Contract(pure = true)
    private String getGoogle_category_videos() { return this.google_category_videos; }
    @Contract(pure = true)
    private List getGoogle_searchresult_name() { return this.google_searchresult_name; }
    @Contract(pure = true)
    private List getGoogle_searchresult_link() { return this.google_searchresult_link; }
    @Contract(pure = true)
    private List getGoogle_timestamp() { return this.google_timestamp; }

    public void searchTrainings(int course_amount) throws IOException{
        FirefoxDriver driver=new FirefoxDriver();
        this.goStartPage(driver);
        this.fillInputField(driver);
        this.clickSearchButton(driver);
        this.switchCategoryToVideo(driver);
        this.readResults(course_amount, driver);
        driver.close();
        writeResultsToCsv(this.getGoogle_searchresult_name(),this.getGoogle_searchresult_link(),this.getGoogle_timestamp());
        this.printResults();
    }


    private void printResults() throws IOException{
        BufferedReader csvfile = new BufferedReader(new FileReader("google_links.csv"));
        Columns col = new Columns();
        String row;
        while ((row = csvfile.readLine()) != null) {
            col.addLine(row.split(","));
        }
        System.out.println("Reading values from: google_links.csv\n\n");
        col.print();
        csvfile.close();
    }

    private void goStartPage(@NotNull FirefoxDriver browser){
        browser.get(this.getGoogle_url());
    }
    private void fillInputField(@NotNull FirefoxDriver browser) {
        WebElement element=browser.findElement(By.cssSelector(this.getInput_field()));
        element.sendKeys(String.format("site %s %s training", this.curstomplatform, this.getTraining_text()));
    }
    private void clickSearchButton(@NotNull FirefoxDriver browser){
        WebElement button=browser.findElement(By.cssSelector(this.getGoogle_search_button()));
        button.click();
    }
    private void switchCategoryToVideo(@NotNull FirefoxDriver browser){
        try {
            WebElement vidi = browser.findElement(By.partialLinkText(getGoogle_category_videos()));
            vidi.click();
        }
        catch (NoSuchElementException e ){
            System.out.println("[ ERROR ] Can't find Video category.\n");
            System.exit(1);
        }
    }

}
