package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class BaseClass {

    public WebDriver driver;
    public Properties prop;
    public LoginPage loginPage;
    public AddCustomerPage addCustomerPage;
    public SearchCustomerPage searchCustomerPage;
    public static Logger logger;
/*
    public WebDriver init_driver(Properties prop) {

        String browserName = prop.getProperty("browser");
        System.out.println("Running on ----> " + browserName + " browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            System.out.println(browserName + " is not found, please pass the right browser Name");
        }
        return driver;
    }

    public Properties init_properties(){
        prop = new Properties();
        String path = "C:\\Users\\Viji\\IdeaProjects\\SeleniumMavenBDD\\src\\test\\java\\config\\config.properties";
        try {
            FileInputStream ip = new FileInputStream(path);
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  prop;
    }
*/
    //Created for generating random string for email
    public static String randomestring() {
        String generatedString1 =  RandomStringUtils.randomAlphabetic(5);
        return (generatedString1);
    }





}
