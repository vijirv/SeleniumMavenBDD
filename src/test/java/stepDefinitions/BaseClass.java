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

    //Created for generating random string for email
    public static String randomestring() {
        String generatedString1 =  RandomStringUtils.randomAlphabetic(5);
        return (generatedString1);
    }





}
