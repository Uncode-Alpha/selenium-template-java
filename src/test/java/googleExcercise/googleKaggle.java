package googleExcercise;

//Imports
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.SeleniumUtils;
import java.io.File;
import java.time.Duration;
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

public class GoogleKaggle {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest logger;

    //TODO: create and move to ENUM
    public static final String URL_GOOGLE = "https://www.google.com";
    public static final String URL_NASA = "https://api.nasa.gov";
    public static final String URL_KAGGLE = "https://www.kaggle.com";
    public static final String KAGGLE_SEARCH_BAR = "//*[@id=\":r5:\"]";
    String loadingSymbol = "//*[@role='progressbar']";
    String modelingXpath = "//span[contains(text(),'Preparing to modeling')]";
    String modelSectionXpath = "//h2[@id='4.-Preparing-to-modeling-']";
    String conclusionXpath = "//*[@aria-label='7. Conclusion , 10 of 10']";
    String keys = "Test Automation";
    String first_result_xpath = "//*[@class='MuiList-root km-list css-1uzmcsd']//li[1]";
    String screenshotName = "KaggleFirstResult";
    public static final String FINAL_URL_TEXT= "heart-disease";

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }
    @Test
    public void googleSearchTest() throws InterruptedException{
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        //Open Kaggle and send keys
        SeleniumUtils.openPageAndSendKeysByXpath(driver,URL_KAGGLE,KAGGLE_SEARCH_BAR,keys);
        //Select first result
        SeleniumUtils.elementIsNotVisible(driver,driver.findElement(By.xpath(loadingSymbol)));
        WebElement firstResult = SeleniumUtils.waitForElementToBeVisible(driver, first_result_xpath);
        System.out.println("----------- Landed on results page ----------");
        SeleniumUtils.clickElementWithJS(driver,firstResult);
        System.out.println("----------- Clicked on first result ----------");
        //Landed on result after clicking
        softAssert.assertTrue(SeleniumUtils.urlContainsText(driver, FINAL_URL_TEXT));
        WebElement modelingLink = SeleniumUtils.waitForElementToBeClickable(driver, modelingXpath);
        actions.moveToElement(SeleniumUtils.findElement(driver, conclusionXpath)).perform();
        wait.until(ExpectedConditions.visibilityOf(modelingLink));
        modelingLink.click();
        System.out.println("----------- Clicked on modeling link ----------");
        actions.scrollToElement(driver.findElement(By.xpath(modelSectionXpath)));
        SeleniumUtils.takeScreenshotUtil(driver,screenshotName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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