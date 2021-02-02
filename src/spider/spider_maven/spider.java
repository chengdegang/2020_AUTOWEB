package spider_maven;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//1.0.888888

public class spider {
    static WebDriver driver;

    @BeforeClass(enabled = true)
    public static void beforeclass() {
        //System.setProperty("webdriver.chrome.driver","E:/extend/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.sogou.com/");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    @Test(dataProvider = "data")
    public void sou(String name) throws InterruptedException{
        WebElement souElement = driver.findElement(By.id("query"));
        // sousuo=String.valueOf(System.currentTimeMillis())+name;
        souElement.sendKeys(name);

        WebElement chaungjianElement = driver.findElement(By.id("stb"));
        chaungjianElement.click();

        //打印判断
        String jieguo = driver.findElement(By.xpath("//span[@class='haoma-tag']")).getText();
        if(jieguo== null || jieguo.length() <= 0){
        	System.out.println("没有获取到内容");
		}
        else System.out.println(jieguo);

    }

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"18600000043"},
				{"18600000045"},
                {"15560000038"},
                {"15560000000"},
                {"CESCES"}
        };

    }

    @AfterMethod
    public void afterMthod() {
//		  WebElement shanElement=driver.findElement(By.id("top_qreset"));
//          shanElement.click();
//
//          WebElement chaungjianElement=driver.findElement(By.id("upquery"));
//          chaungjianElement.click();
        driver.quit();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.sogou.com/");
    }
}



