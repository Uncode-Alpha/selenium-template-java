package docker.dockerExcercise;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.SeleniumUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class DockerTest{
    RemoteWebDriver chromeDriver;
    RemoteWebDriver edgeDriver;
    RemoteWebDriver firefoxDriver;
    public static Logger logger = LoggerFactory.getLogger(SeleniumUtils.class);
    private static final String LOCALHOST = "http://localhost:4444/wd/hub";
    private static final String URL="https://api.nasa.gov";
    private static final String TITLE_MESSAGE ="Page title is : {}";

    @BeforeTest
    public void setUp(){
        logger.info("Setting up the test environment");
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions = new EdgeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        try{
            URL url = new URL(LOCALHOST);
            chromeDriver = createNewDriver(url,chromeOptions);
            edgeDriver = createNewDriver(url, edgeOptions);
            firefoxDriver = createNewDriver(url, firefoxOptions);
        }catch(MalformedURLException e){
        logger.error("Malformed URL passed as LocalHost: {}", e.getMessage());
        }
    }
    private RemoteWebDriver createNewDriver(URL url, Object options){
        return new RemoteWebDriver(url, (Capabilities) options);
    }
    private void testGridDriver(RemoteWebDriver gridDriver){
        gridDriver.get(URL);
        String title = gridDriver.getTitle();
        logger.info(TITLE_MESSAGE,title);
        gridDriver.quit();
    }

    @Test
    public void testDockerGridChrome(){
        testGridDriver(chromeDriver);
    }

    @Test
    public void testDockerGridEdge(){
        testGridDriver(edgeDriver);
    }

    @Test
    public void testDockerGridFirefox(){
        testGridDriver(firefoxDriver);
    }
}