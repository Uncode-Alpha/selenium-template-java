package pages.nasa;

//Imports
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.SeleniumUtils;


//Declare static constants to be used, including paths for testData

public class NasaTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest logger;

    //TODO: create and move to ENUM
    public static final String URL_NASA = "https://www.nasa.gov";
    //Xpaths
    public static final String NASA_SEARCH = "//*[@id=\"search-field-en-small--desktop\"]";
    public static final String RESULT_PAGE_HEADING = "//*[contains(text(),'Search Results for:')]";
/*
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
*/
    @BeforeMethod
    public void setup(){
        // Driver Manager
        WebDriverManager.chromedriver().browserVersion("134.0.6998.89").setup();
        // Add Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //Here we initialize the driver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }
    @Test
    public void mainMethodTest() throws InterruptedException{
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        //Example of implicit wait
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL_NASA);
        driver.manage().window().maximize();
        SeleniumUtils.openPageAndSendKeysByXpath(driver,NASA_SEARCH,"Mars");
        WebElement resultPage = SeleniumUtils.waitForElementToBeVisible(driver,RESULT_PAGE_HEADING);
        Assert.assertTrue(resultPage.isDisplayed());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}