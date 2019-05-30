import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {
    public static void main(String[] args) throws IOException {
        FirefoxDriver driver=new FirefoxDriver();
        driver.get("http://google.com/");

//        fill the input field
        WebElement element=driver.findElement(By.cssSelector("input[name=q]"));
        element.sendKeys("selenium training");

//        click on search button
        WebElement button=driver.findElement(By.cssSelector("center:nth-child(1) > input:nth-child(1)"));
        button.click();

//        switch to videos
        WebElement vidi = driver.findElement(By.cssSelector("div.hdtb-mitem:nth-child(3) > a:nth-child(1)"));
        vidi.click();

//        Collecting the 3 training links
        WebElement firstCourseName = driver.findElement(By.cssSelector("div.g:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > h3:nth-child(1)"));
        String name1 = firstCourseName.getText();
        String name1Converted = name1.replace(',','-');
        System.out.println(name1Converted);

        WebElement firstcourse = driver.findElement(By.cssSelector("div.g:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
        String link1 = firstcourse.getAttribute("href");
        System.out.println(link1);

        WebElement secondCourseName = driver.findElement(By.cssSelector("div.g:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > h3:nth-child(1)"));
        String name2 = secondCourseName.getText();
        String name2Converted = name2.replace(',','-');
        System.out.println(name2Converted);

        WebElement secondcourse = driver.findElement(By.cssSelector("div.g:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
        String link2 = secondcourse.getAttribute("href");
        System.out.println(link2);

        WebElement thirdCourseName = driver.findElement(By.cssSelector("div.g:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > h3:nth-child(1)"));
        String name3 = thirdCourseName.getText();
        String name3Converted = name3.replace(',','-');
        System.out.println(name3Converted);

        WebElement thirdcourse = driver.findElement(By.cssSelector("div.g:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
        String link3 = thirdcourse.getAttribute("href");
        System.out.println(link3);

        driver.quit();

//        Write links to csv file
        String csvFile = "links.csv";
        Writer fileWriter = new FileWriter(csvFile);

        fileWriter.write("name,link\n"+name1Converted+","+link1+"\n"+name2Converted+","+link2+"\n"+name3Converted+","+link3);
        fileWriter.close();

    }
}