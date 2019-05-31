import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import searchengines.Google;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList names = new ArrayList();
        ArrayList links = new ArrayList();
        String trainingtext = "selenium training";
        String searchengine_url = "http://google.com/";
        String google_input_field = "input[name=q]";
        String google_search_button = "center:nth-child(1) > input:nth-child(1)";
        String google_category_videos = "div.hdtb-mitem:nth-child(3) > a:nth-child(1)";
        Integer namecounter=1;
        int linkcounter=1;

        List google_searchresult_name = new ArrayList();
        google_searchresult_name.add("div.g:nth-child(");
        google_searchresult_name.add(") > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > h3:nth-child(1)");

        List google_searchresult_link = new ArrayList();
        google_searchresult_link.add("div.g:nth-child(");
        google_searchresult_link.add(") > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)");

        Google google_properties = new Google();
        FirefoxDriver driver=new FirefoxDriver();

        System.out.println(google_properties.url);
        driver.get(searchengine_url);

//        fill the input field
        WebElement element=driver.findElement(By.cssSelector(google_input_field));
        element.sendKeys(trainingtext);

//        click on search button
        WebElement button=driver.findElement(By.cssSelector(google_search_button));
        button.click();

//        switch to videos
        WebElement vidi = driver.findElement(By.cssSelector(google_category_videos));
        vidi.click();

//        Collecting the 3 training links
        WebElement firstCourseName = driver.findElement(By.cssSelector(google_searchresult_name.get(0)+namecounter.toString()+google_searchresult_name.get(1)));
        String name1 = firstCourseName.getText();
        String name1Converted = name1.replace(',','-');
        System.out.println(name1Converted);
        names.add(name1Converted);
        namecounter++;
        System.out.println(namecounter);

        WebElement firstcourse = driver.findElement(By.cssSelector(google_searchresult_link.get(0)+ Integer.toString(linkcounter) +google_searchresult_link.get(1)));
        String link1 = firstcourse.getAttribute("href");
        System.out.println(link1);
        links.add(link1);
        linkcounter++;

        WebElement secondCourseName = driver.findElement(By.cssSelector(google_searchresult_name.get(0)+namecounter.toString()+google_searchresult_name.get(1)));
        String name2 = secondCourseName.getText();
        String name2Converted = name2.replace(',','-');
        System.out.println(name2Converted);
        names.add(name2Converted);
        namecounter++;
        System.out.println(namecounter);


        WebElement secondcourse = driver.findElement(By.cssSelector(google_searchresult_link.get(0)+ Integer.toString(linkcounter) +google_searchresult_link.get(1)));
        String link2 = secondcourse.getAttribute("href");
        System.out.println(link2);
        links.add(link2);
        namecounter++;

        WebElement thirdCourseName = driver.findElement(By.cssSelector(google_searchresult_name.get(0)+namecounter.toString()+google_searchresult_name.get(1)));
        String name3 = thirdCourseName.getText();
        String name3Converted = name3.replace(',','-');
        System.out.println(name3Converted);
        names.add(name3Converted);
        System.out.println(namecounter);

        WebElement thirdcourse = driver.findElement(By.cssSelector(google_searchresult_link.get(0)+ Integer.toString(linkcounter) +google_searchresult_link.get(1)));
        String link3 = thirdcourse.getAttribute("href");
        System.out.println(link3);
        links.add(link3);


        driver.quit();

        Csvhandler tofile = new Csvhandler();
        tofile.csvhandler(names, links);

    }
}