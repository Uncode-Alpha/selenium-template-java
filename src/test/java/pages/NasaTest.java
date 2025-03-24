package pages;

//Imports
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.CommonUtils;
import utils.SeleniumUtils;
import java.io.File;
import java.time.Duration;
import javax.print.attribute.standard.MediaSize.NA;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


//Declare static constants to be used, including paths for testData

public class NasaTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public ExtentTest logger;

    //TODO: create and move to ENUM
    public static final String URL_GOOGLE = "https://www.google.com";
    public static final String URL_NASA = "https://api.nasa.gov";
    //Endpoints
    public static final String APOD_BASE = "https://api.nasa.gov/planetary/apod";
    public static final String APOD_ENDPOINT = "https://api.nasa.gov/";
    public static final String APOD_PARAM_API_KEY = "api_key";
    public static final String APOD_PARAM_API_KEY_VALUE = "DEMO_KEY";

    @BeforeTest
    public void beforeTestMethod(){
        CommonUtils.sparkReports();
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }
    @Test
    public void mainMethodTest() throws InterruptedException{
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        //Example of implicit wait
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL_NASA);
    }

    @Test
    public void restfulTest(){
        //Using GET response
        /*
        Response response = RestAssured
        .given()
        .baseUri(APOD_BASE)
        .queryParam(APOD_PARAM_API_KEY, APOD_PARAM_API_KEY_VALUE)
        .when()
        .get(APOD_ENDPOINT);
        */
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