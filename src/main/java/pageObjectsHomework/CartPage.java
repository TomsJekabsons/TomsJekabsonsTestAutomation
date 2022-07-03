package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    public WebDriver driver;
    public By cartQuantity = By.xpath("//div[@class='cart_quantity']");
    public By cartItem = By.xpath("//div[@class='inventory_item_name']");
    public By checkoutButton = By.id("checkout");

    //Konstruktors
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    //Geteri Elementiem
    public WebElement getCartQuantity(){
        return driver.findElement(cartQuantity);
    }
    public WebElement getCartItem(){
        return driver.findElement(cartItem);
    }
    public WebElement getCheckoutButton(){
        return driver.findElement(checkoutButton);
    }
}
