package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.AppConstants;
import utilities.ElementActions;

public class LoginPage {

    WebDriver driver;
    ElementActions elementActions;

    //Constructor of page class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    //locators - By
    By emailId = By.id("Email");
    By password = By.id("Password");
    By login =By.xpath("//input[@type='submit']");
    By logout = By.linkText("Logout");

    public void enterLoginDetails(String uname, String pwd){
        elementActions.doSendKeys(emailId,uname);
        elementActions.doSendKeys(password,pwd);
    }

    public void clickLogin(){
        elementActions.doClick(login);
    }

    public void doLogout(){
        elementActions.doClick(logout);
    }

}
