package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.ElementActions;

public class AddCustomerPage {

    WebDriver driver;
    ElementActions elementActions;

    //Constructor of page class
    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    //locators

    By lnkCustomers_menu=By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
   // By lnkCustomers_menuitem=By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");
    By lnkCustomers_menuitem=By.xpath("//span[@class='menu-item-title' and text()='Customers']");

    By btnAddnew=By.xpath("//a[@class='btn bg-blue']"); //Add new

    By txtEmail=By.id("Email");
    By txtPassword=By.id("Password");

    By txtcustomerRoles=By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");

    By lstitemAdministrators=By.xpath("//li[contains(text(),'Administrators')]");
    By lstitemRegistered=By.xpath("//li[contains(text(),'Registered')]");
    By lstitemGuests=By.xpath("//li[contains(text(),'Guests')]");
    By lstitemVendors=By.xpath("//li[contains(text(),'Vendors')]");


    By drpmgrOfVendor=By.xpath("//*[@id='VendorId']");
    By rdMaleGender=By.id("Gender_Male");
    By rdFeMaleGender=By.id("Gender_Female");

    By txtFirstName=By.id("FirstName");
    By txtLastName=By.id("LastName");

    By txtDob=By.xpath("//input[@id='DateOfBirth']");

    By txtCompanyName=By.xpath("//input[@id='Company']");

    By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");

    By btnSave=By.xpath("//button[@name='save']");

    //Page actions/methods

    public void clickOnCustomersMenu(){
        elementActions.waitForElementPresent(lnkCustomers_menu);
        elementActions.doClick(lnkCustomers_menu);
    }

    public void clickOnCustomersMenuItem() throws InterruptedException {
        elementActions.waitForElementPresent(lnkCustomers_menuitem);
        elementActions.doClick(lnkCustomers_menuitem);
        Thread.sleep(2000);
    }

    public void clickOnAddnew(){
        elementActions.waitForElementPresent(btnAddnew);
        elementActions.doClick(btnAddnew);
    }

    public void setGender(String gender){
        if(gender.equalsIgnoreCase("Male")){
            elementActions.doClick(rdFeMaleGender);
        }
        else if(gender.equalsIgnoreCase("Female")){
            elementActions.doClick(rdFeMaleGender);
        }
        else{
            elementActions.doClick(rdFeMaleGender);
        }
    }

    // Need to refactor this method
    public void setCustomerRoles(String role) throws InterruptedException {
        if (!role.equals("Vendors")) //If role is vendors should not delete Register as per req.
        {
            driver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
        }
        elementActions.doClick(txtcustomerRoles);

        WebElement listitem;

        Thread.sleep(3000);

        if (role.equals("Administrators")) {
            listitem = driver.findElement(lstitemAdministrators);
        } else if (role.equals("Guests")) {
            listitem = driver.findElement(lstitemGuests);
        } else if (role.equals("Registered")) {
            listitem = driver.findElement(lstitemRegistered);
        } else if (role.equals("Vendors")) {
            listitem = driver.findElement(lstitemVendors);
        } else {
            listitem = driver.findElement(lstitemGuests);
        }

        //listitem.click();
        //Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", listitem);

    }

    public void addCustomerInfo(String email,String password,String fname, String lname, String gender,String role,String dob,String comname, String vendor,String content ) throws InterruptedException {

        elementActions.doSendKeys(txtEmail,email);
        elementActions.doSendKeys(txtPassword,password);
        elementActions.doSendKeys(txtFirstName,fname);
        elementActions.doSendKeys(txtLastName,lname);
        setGender(gender);
        setCustomerRoles(role);
        elementActions.doSendKeys(txtDob,dob);
        elementActions.doSendKeys(txtCompanyName,comname);

        elementActions.waitForElementPresent(drpmgrOfVendor);
        elementActions.doSelectDropdown(drpmgrOfVendor,vendor);

        elementActions.waitForElementPresent(txtAdminContent);
        elementActions.doSendKeys(txtAdminContent,content);
    }

    public void clickOnSave() {
        elementActions.waitForElementPresent(btnSave);
        elementActions.doClick(btnSave);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

}
