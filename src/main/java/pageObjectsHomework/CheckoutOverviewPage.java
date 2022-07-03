package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage {
    public WebDriver driver;
    private By cartQuantityOverview = By.xpath("//div[@class='cart_quantity']");
    private By cartQuantityOverviewItem = By.xpath("//div[@class='inventory_item_name']");
    private By finishButton = By.id("finish");


    //Konstruktors
    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    //Geteri Elementiem
    public WebElement getCartQuantityOverview(){
        return driver.findElement(cartQuantityOverview);
    }
    public WebElement getCartQuantityOverviewItem(){
        return driver.findElement(cartQuantityOverviewItem);
    }
    public WebElement getFinishButton(){
        return driver.findElement(finishButton);
    }
}
