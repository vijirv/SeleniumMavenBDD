package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import cucumber.api.java.eo.Se;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;
import utilities.AppConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Steps extends BaseClass {

    @Before
    public void setUp() throws IOException {
        logger = Logger.getLogger("SeleniumMavenBDD");
        PropertyConfigurator.configure("log4j.properties");

        prop = new Properties();
        String path = System.getProperty("user.dir")+"//src/test//java//config//config.properties";
        System.out.println(path);
         FileInputStream ip = new FileInputStream(path);
         prop.load(ip);

         String browserName = prop.getProperty("browser");
         System.out.println("BrowserName " +browserName);

         if(browserName.equalsIgnoreCase("chrome")){
             WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();
        }
         else if(browserName.equalsIgnoreCase("firefox")){
             WebDriverManager.firefoxdriver().setup();
             driver = new FirefoxDriver();
         }
         else if(browserName.equalsIgnoreCase("edge")){
             WebDriverManager.edgedriver().setup();
             driver = new EdgeDriver();
         }
         else if(browserName.equalsIgnoreCase("ie")){
             WebDriverManager.iedriver().setup();
             driver = new InternetExplorerDriver();
         }
         else {
             System.out.println("Some problem with the config.properties file");
         }

        logger.info("*******Opening browser ******");

    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Given("Given User Launch Chrome browser")
    public void given_User_Launch_Chrome_browser() {
        loginPage = new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_URL(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        logger.info("*******Opening url ******");
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_Email_as_and_Password_as(String email, String password) {
       loginPage.enterLoginDetails(email,password);
    }

    @When("Click on Login")
    public void click_on_Login() {
        loginPage.clickLogin();
        logger.info("*******Lauching Browser ******");
    }

    @Then("Page Title should be {string}")
    public void page_Title_should_be(String pageTitle) {
        if (driver.getPageSource().contains("Login was unsuccessful.")) {
            driver.close();
            Assert.assertTrue(false);
        } else {
            Assert.assertEquals(pageTitle, driver.getTitle());
        }
    }
    @When("User click on Log out link")
    public void user_click_on_Log_out_link() throws InterruptedException {
            loginPage.doLogout();
    }

    @Then("close browser")
    public void close_browser() {
        logger.info("*******Closing Browser ******");
        driver.quit();
    }

    // Customer features Step definitions
    @Then("User can view Dashboad")
    public void user_can_view_Dashboad() {
        addCustomerPage = new AddCustomerPage(driver);
        String title = addCustomerPage.getPageTitle();
        Assert.assertEquals(AppConstants.DASHBOARD_PAGE_TITLE,title);
    }

    @When("User click on customers Menu")
    public void user_click_on_customers_Menu() {
        addCustomerPage.clickOnCustomersMenu();
    }

    @When("click on customers Menu Item")
    public void click_on_customers_Menu_Item() throws InterruptedException {
        addCustomerPage.clickOnCustomersMenuItem();

    }

    @When("click on Add new button")
    public void click_on_Add_new_button() {
        addCustomerPage.clickOnAddnew();
    }

    @Then("User can view Add new customer page")
    public void user_can_view_Add_new_customer_page() {
        String title = addCustomerPage.getPageTitle();
        Assert.assertEquals(AppConstants.ADDCUSTOMER_PAGE_TITLE,title);
    }

    @When("User enter customer info")
   // public void user_enter_customer_info(String email,String password,String fname, String lname, String gender, String role,String dob, String comname, String vendor,String content ) throws InterruptedException {
    public void user_enter_customer_info() throws InterruptedException {
        String email = randomestring()+"@gmail.com";
        addCustomerPage.addCustomerInfo(email,"password123","Matt","Simon","male","Guest","11/05/1980","Vtec","Vendor 1","This is testing");
    }

    @When("click on Save button")
    public void click_on_Save_button() {
        addCustomerPage.clickOnSave();
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String confirmMsg) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully"));
    }

    //Searching customer by Email id - step definitions
    @When("Enter customer EMail")
    public void enter_customer_EMail() {
        searchCustomerPage = new SearchCustomerPage(driver);
        searchCustomerPage.setEmail("victoria_victoria@nopCommerce.com");
    }

    @When("Click on search button")
    public void click_on_search_button() throws InterruptedException {
        searchCustomerPage.clickSearch();
        Thread.sleep(6000);
    }

    @Then("User should found Email in the Search table")
    public void user_should_found_Email_in_the_Search_table() {
        boolean status = searchCustomerPage.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
        System.out.println("Email search status is " + status);
        Assert.assertEquals(true,status);
    }













}
