package googleExcercise;

import io.github.bonigarcia.wdm.WebDriverManager;
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

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SeleniumTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "file://Users/josueaguirre/Documents/GitHub/selenium-template-java/src/main/resources/site_1/index.html";
    @BeforeMethod
    public void setup() {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        // Initialize the driver
        driver = new ChromeDriver();
        // Initialize WebDriverWait with a timeout of 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testFlightSearch() {
        // Step 1: Navigate to the URL
        driver.get(URL);

        // Step 2: Go to Flights page and validate the page is displayed
        driver.findElement(By.xpath("//*[contains(text(),'Flights')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-from")));
        Assert.assertTrue(driver.findElement(By.id("flight-to")).isDisplayed(), "Flights page is not displayed");

        // Step 3: Populate flight from
        Select fromDropdown = new Select(driver.findElement(By.id("flight-from")));
        fromDropdown.selectByVisibleText("CDMX");

        // Step 4: Populate flight to
        Select toDropdown = new Select(driver.findElement(By.id("flight-to")));
        toDropdown.selectByVisibleText("La Habana");

        // Step 5: Set departing and returning dates
        LocalDate today = LocalDate.now();
        LocalDate departingDate = today.plusDays(1);
        LocalDate returningDate = today.plusDays(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Corrected date format

        // Step 6: Populate departing and returning dates
        driver.findElement(By.id("departing")).sendKeys(departingDate.format(formatter));
        driver.findElement(By.id("returning")).sendKeys(returningDate.format(formatter));

        // Step 7: Click on the search button
        driver.findElement(By.xpath("//button[@class='btn btn-primary button-ns']")).click();

        // Step 8: Wait for results to load and validate the results header
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Results')]")));

        // Step 9: Click on the sort dropdown and select "Price ascending"
        Select sortDropdown = new Select(driver.findElement(By.id("sort")));
        sortDropdown.selectByVisibleText("Price ascending");

        // Step 10: Wait for the sorted results to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results")));

        // Step 11: Fetch prices and validate they are sorted in ascending order
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
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}