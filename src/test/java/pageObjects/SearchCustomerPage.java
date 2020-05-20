package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utilities.ElementActions;

import java.util.List;

public class SearchCustomerPage {
    WebDriver driver;
    ElementActions elementActions;

    public SearchCustomerPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    //locator
    By txtEmail = By.id("SearchEmail");
    By txtFirstName = By.id("SearchFirstName");
    By txtLastName= By.id("SearchLastName");
    By btnSearch = By.id("search-customers");

    //Page actions/methods

    public void setEmail(String email){
        elementActions.waitForElementPresent(txtEmail);
        elementActions.doSendKeys(txtEmail,email);
    }
    public void clickSearch(){
        elementActions.waitForElementPresent(btnSearch);
        elementActions.doClick(btnSearch);
    }
    public boolean searchCustomerByEmail(String email) {
        String beforeXpath = "//table[@id='customers-grid']/tbody/tr[";
        String afterXpath = "]/td[2]";
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='customers-grid']/tbody/tr"));
        int rowCount = rows.size();
        boolean flag = false;

        for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
            String actualXpath = beforeXpath + rowNum + afterXpath;
            String ColValue = driver.findElement(By.xpath(actualXpath)).getText();
            if (ColValue.equals(email)) {
                flag = true;
                break;
            }
        }
        return flag;
    }









}
