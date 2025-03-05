package googleExcercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SeleniumTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "file:////Users/josueaguirre/Downloads/index.html";

    @BeforeMethod
    public void setup() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/Users/josueaguirre/Documents/Programas/chromedriver-mac-arm64/chromedriver");

        // Initialize the driver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testFlightSearch() {
        driver.get(URL);
        //Go to Fights page and validate page is displayed
        driver.findElement(By.xpath("//*[contains(text(),'Flights')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-from")));
        Assert.assertTrue(driver.findElement(By.id("flight-to")).isDisplayed(), "Flights page is not displayed");

        //Populate flight from
        Select fromDropdown = new Select(driver.findElement(By.id("flight-from")));
        fromDropdown.selectByVisibleText("CDMX");
        //Populate flight to
        Select toDropdown = new Select(driver.findElement(By.id("flight-to")));
        toDropdown.selectByVisibleText("La Habana");
        //Set departing and returning dates
        LocalDate today = LocalDate.now();
        LocalDate departingDate = today.plusDays(1);
        LocalDate returningDate = today.plusDays(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy"); //Set correct date format
        //Populate departing and returning dates
        driver.findElement(By.id("departing")).sendKeys(departingDate.format(formatter));
        driver.findElement(By.id("returning")).sendKeys(returningDate.format(formatter));
        //Click on search button
        driver.findElement(By.xpath("//button[@class='btn btn-primary button-ns']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("h1['Results']")));
        //Click on sort dropdown and select Price ascending
        Select sortDropdown = new Select(driver.findElement(By.id("sort")));
        sortDropdown.selectByVisibleText("Price ascending");

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results")));
        List<WebElement> priceElements = driver.findElements(By.className("price"));
        List<Double> prices = priceElements.stream()
                .map(WebElement::getText)
                .map(text -> text.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        List<Double> sortedPrices = prices.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(prices, sortedPrices, "Prices are not sorted in ascending order");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}