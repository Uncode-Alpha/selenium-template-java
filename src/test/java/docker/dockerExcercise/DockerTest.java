package docker.dockerExcercise;

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
            chromeDriver = new RemoteWebDriver(url,chromeOptions);
            edgeDriver = new RemoteWebDriver(url, edgeOptions);
            firefoxDriver = new RemoteWebDriver(url, firefoxOptions);
        }catch(MalformedURLException e){
        logger.error("Malformed URL passed as LocalHost: {}", e.getMessage());
        }
    }

    @Test
    public void testDockerGridChrome(){
        chromeDriver.get(URL);
        String title = chromeDriver.getTitle();
        logger.info(TITLE_MESSAGE,title);
        chromeDriver.quit();
    }

    @Test
    public void testDockerGridEdge(){
        edgeDriver.get(URL);
        String title = edgeDriver.getTitle();
        logger.info(TITLE_MESSAGE,title);
        edgeDriver.quit();
    }

    @Test
    public void testDockerGridFirefox(){
        firefoxDriver.get(URL);
        String title = firefoxDriver.getTitle();
        logger.info(TITLE_MESSAGE,title);
        firefoxDriver.quit();
    }
}