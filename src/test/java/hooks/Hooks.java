package hooks;

import config.ConfigReader;
import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.Beta;
import org.openqa.selenium.WebDriver;

import java.sql.Driver;

public class Hooks {
    private WebDriver driver;

    @Before
    public void setUp(){
        ConfigReader.loadProperties();
        String browser = ConfigReader.getProperty("browser");
        driver = DriverFactory.initializeDriver(browser);

        String baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl);
        System.out.println("Opened URL: " + baseUrl);
    }

    @After
    public void tearDown(){
        DriverFactory.quitDriver();
        System.out.println("Scenario done, Driver is closed.");
    }
}
