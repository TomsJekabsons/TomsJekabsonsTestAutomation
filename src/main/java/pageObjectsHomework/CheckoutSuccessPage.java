package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutSuccessPage {

    public WebDriver driver;
    private By checkoutComplete = By.xpath("//span[@class='title']");
    private By completeHeader = By.cssSelector("h2[class='complete-header']");
    private By backHome = By.id("back-to-products");

    //Konstruktors

    public CheckoutSuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    //Geteri Elementiem
    public WebElement getCheckoutComplete(){
        return driver.findElement(checkoutComplete);
    }
    public WebElement getCompleteHeader(){
        return driver.findElement(completeHeader);
    }
    public WebElement getBackHome(){
        return driver.findElement(backHome);
    }
}
