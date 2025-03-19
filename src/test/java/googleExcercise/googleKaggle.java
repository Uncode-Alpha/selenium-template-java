package googleExcercise;
//Imports
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//Declare static constants to be used, including paths for testData

public class googleKaggle {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest logger;

    @BeforeTest
    public void beforeTestMethod(){
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        sparkReporter.config().setTheme(Theme.DARK);
        extent.setSystemInfo("HostName", "RHEL8");
        extent.setSystemInfo("UserName", "root");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Automation Test Results");
    }
    @BeforeMethod
    public void setup(){
        // Driver Manager
        WebDriverManager.chromedriver().browserVersion("134.0.6998.89").setup();
        // Add Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //Here we initialize the driver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));    
    }
    @Test
    public void googleSearchTest(){
        setup();
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.elementToBeClickable(By.className("q")));
        driver.findElement(By.className("q")).sendKeys("Kaggle");
        System.out.print("----------- Keys sent ----------");
    
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    
    @AfterTest
    public void afterTest(){
        //The after test method works for flushing and closing any driver or 
        extent.flush();
    }
}