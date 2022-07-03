package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    public WebDriver driver;
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By zipPostalCode = By.id("postal-code");
    private By checkoutContinue = By.id("continue");
    private By firstNameError = By.xpath("//h3[.='Error: First Name is required']");
    private By lastNameError = By.xpath("//h3[.='Error: Last Name is required']");
    private By zipPostalCodeError = By.xpath("//h3[.='Error: Postal Code is required']");

    //Konstruktors

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    //Geteri Elementiem
    public WebElement getFirstName() {
        return driver.findElement(firstName);
    }

    public WebElement getLastName() {
        return driver.findElement(lastName);
    }

    public WebElement getZipPostalCode() {
        return driver.findElement(zipPostalCode);
    }

    public WebElement getCheckoutContinue() {
        return driver.findElement(checkoutContinue);
    }

    public WebElement getFirstNameError() {
        return driver.findElement(firstNameError);
    }

    public WebElement getLastNameError(){
        return driver.findElement(lastNameError);
    }
    public WebElement getZipPostalCodeError(){
        return driver.findElement(zipPostalCodeError);
    }
}
