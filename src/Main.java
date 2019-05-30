import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    public static void main(String[] args){
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
        WebElement firstcourse = driver.findElement(By.cssSelector("div.g:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
        String link1 = firstcourse.getAttribute("href");
        System.out.println(link1);

        WebElement secondcourse = driver.findElement(By.cssSelector("div.g:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
        String link2 = secondcourse.getAttribute("href");
        System.out.println(link2);

        WebElement thirdcourse = driver.findElement(By.cssSelector("div.g:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
        String link3 = thirdcourse.getAttribute("href");
        System.out.println(link3);

        driver.quit();
    }
}