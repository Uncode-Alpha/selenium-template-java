package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class containing many static methods to help testing.
 * 
 * @author ejunior
 *
 */
public class SeleniumUtils {
	private static final String RESULT_IMAGE = "/Users/josueaguirre/Documents/GitHub/selenium-template-java/output/screenshots/";
	private static final Duration DEFAULT_WAIT_TIME = Duration.ofSeconds(25);
	public static Logger logger = LoggerFactory.getLogger(SeleniumUtils.class);

	
	/**
	 * Try to find an element in the page by xpath. If the element is not found,
	 * null is returned.
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static WebElement findElement(WebDriver driver, String xpath) {
		try {
			return driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Try to find elements in the page by xpath. If the elements are not found,
	 * null is returned.
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static List<WebElement> findElements(WebDriver driver, String xpath) {
		try {
			return driver.findElements(By.xpath(xpath));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Wait for an element to be visible on the screen, given a maximum time to wait
	 * 
	 * @param driver
	 * @param xpath
	 * @param timeOutInSeconds
	 * @return
	 */
	public static WebElement waitForElementToBeVisible(WebDriver driver, String xpath, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Wait for an element to be visible on the screen, using the DEFAULT_WAIT_TIME
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static WebElement waitForElementToBeVisible(WebDriver driver, String xpath) {
		return waitForElementToBeVisible(driver, xpath, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for an element to be present in the DOM, given a maximum time to wait
	 * 
	 * @param driver
	 * @param xpath
	 * @param timeOutInSeconds
	 * @return
	 */
	public static WebElement waitForElement(WebDriver driver, String xpath, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Wait for an element to be present in the DOM, using the DEFAULT_WAIT_TIME
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static WebElement waitForElement(WebDriver driver, String xpath) {
		return waitForElement(driver, xpath, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for at least one element to be present in the DOM, given a maximum time to wait
	 * 
	 * @param driver
	 * @param xpath
	 * @param timeOutInSeconds
	 * @return
	 */
	public static List<WebElement> waitForElements(WebDriver driver, String xpath, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Wait for at least one element to be present in the DOM, using the DEFAULT_WAIT_TIME
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static List<WebElement> waitForElements(WebDriver driver, String xpath) {
		return waitForElements(driver, xpath, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for an element to be clickable, given a maximum time to wait
	 * 
	 * @param driver
	 * @param xpath
	 * @param defaultWaitTime
		 * @return
		 */
		public static WebElement waitForElementToBeClickable(WebDriver driver, String xpath, Duration defaultWaitTime) {
			WebDriverWait wait = new WebDriverWait(driver, defaultWaitTime);
		try {
			return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Wait for an element to be clickable, using the DEFAULT_WAIT_TIME
	 * 
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public static WebElement waitForElementToBeClickable(WebDriver driver, String xpath) {
		return waitForElementToBeClickable(driver, xpath, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for a certain condition to occur for up to DEFAULT_WAIT_TIME
	 * 
	 * Usage: waitUntil(driver, ExpectedConditions.elementToBeClickable(By.xpath("/input[@id='q']")))
	 * 
	 * @param condition
	 * @return
	 */
	public <T> T waitUntil(WebDriver driver, Function<? super WebDriver, T> condition) {
		return waitUntil(driver, condition, DEFAULT_WAIT_TIME);
	}
	
	/**
	 * Wait for a certain condition to occur for up to a given time in seconds
	 * 
	 * @param condition
	 * @param timeOutInSeconds
	 * @return
	 */
	public <T> T waitUntil(WebDriver driver, Function<? super WebDriver, T> condition, Duration timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(condition);
	}

	/**
	 * Method to open specific url, click and send keys in the search box
	 * 
	 * @param driver
	 * @param url
	 * @param xpath
	 * @param keys
	 */
	public static void openPageAndSendKeysByXpath(WebDriver driver, String xpath, String keys){
		if(driver == null||xpath == null||xpath.isEmpty()||keys == null||keys.isEmpty()){
			throw new IllegalArgumentException("Parameters are null or empty in openPageAndSendKeys method");
		}
		try{
			WebElement searchBox = waitForElementToBeVisible(driver, xpath);
			searchBox.sendKeys(keys);
			searchBox.sendKeys(Keys.ENTER);
			System.out.println("----------- Keys sent ----------");
		}catch(NoSuchElementException e){
			System.out.println("Element was not found in  openPageAndSendKeys method");
		}catch(TimeoutException e){
			System.out.println("Timeout exception in openPageAndSendKeys method, waited for: "+DEFAULT_WAIT_TIME+" seconds");
		}catch(Exception e){
			System.out.println("Error in openPageAndSendKeys method");
		}
	}
	/**
	 * Method to take a screenshot with png extension, saved to default path
	 * @param driver
	 * @param screenshotName
	 */
	public static void takeScreenshotUtil(WebDriver driver, String screenshotName){
		try{
            //Convert web driver object to TakeScreenshot
            TakesScreenshot scrShot =((TakesScreenshot)driver);
            //Call getScreenshotAs method to create image file
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            //Move image file to new destination
            File DestFile=new File(RESULT_IMAGE+screenshotName+".png");
            //Copy file at destination
            FileUtils.copyFile(SrcFile, DestFile);
            System.out.println("Screenshot saved at: " + DestFile.getAbsolutePath());
        }catch(IOException e){
            System.out.println("Error taking screenshot");
            throw new RuntimeException();
        }
	}
	/**
	 * Method to check if the URL contains a specific text
	 * @param driver
	 * @param text
	 * @return
	 */
	public static boolean urlContainsText(WebDriver driver,String text){
		boolean flag=false;
		if (driver == null || text == null || text.isEmpty()) {
			throw new IllegalArgumentException("Driver or text parameter is null or empty in urlContainsText method");
		}
		try{
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
			flag=wait.until(ExpectedConditions.urlContains(text.toLowerCase()));
			System.out.println("----------- URL contains following text ----------"+"\n"+text);
		} catch (TimeoutException e) {
			System.out.println("Timeout while waiting for URL to contain text: " +"\n"+text);
		} catch (Exception e) {
			System.out.println("Unexpected error in urlContainsText method: " +"\n"+ e.getLocalizedMessage());
		}
		return flag;
	}

	public static void clickElementWithJS(WebDriver driver, WebElement element){
		if(driver == null || element == null){
			logger.error("Driver or element are null inside clickElementWithJS");
			throw new IllegalArgumentException("Parameters cannot be null inside click Element with JS");
		}
		try{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			logger.info("---------- making click on element: "+element);
			js.executeScript("arguments[0].click();", element);
		}catch(Exception e){
			logger.error("Failed while trying making click on element: "+element);
			logger.error("This is the error: "+e.getMessage());
		}
	}

	public static void elementIsNotVisible(WebDriver driver, WebElement element){
		if(driver == null|| element == null){
			logger.error("Driver or element cannot be null inside ElementIsNotVisible method");
			throw new IllegalArgumentException("Driver or element is null in elementIsNotVisible method");
		}
		try{
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
			logger.info("---------- waiting for element to be invisible: "+element);
			wait.until(ExpectedConditions.invisibilityOf(element));
		}catch(Exception e){
			logger.error("Element is still visible after waiting for:"+DEFAULT_WAIT_TIME+" seconds");
			logger.error("This is the error: "+e.getMessage());
		}
	}
}
