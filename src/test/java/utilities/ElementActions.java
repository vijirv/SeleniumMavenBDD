package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {
    WebDriver driver;
    WebDriverWait wait;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, AppConstants.DEFAULT_EXPLICIT_TIME_OUT);
    }

    public WebElement getElement(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (Exception e) {
            System.out.println("Some exception occured while creating the webelement :" + locator);
        }
        return element;
    }

    public void doSendKeys(By locator, String value) {
        getElement(locator).clear();
        getElement(locator).sendKeys(value);
    }

    public void doClick(By locator) {
        getElement(locator).click();
    }

    public void waitForElementPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void doSelectDropdown(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(value);
    }

    public String doGetPageTitle() {
        return driver.getTitle();
    }
}
