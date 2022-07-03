package pageObjectsHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage {

    public static WebDriver driver;
    private static By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private static By shoppingCartLink = By.xpath("//div[@id='shopping_cart_container']/a[1]");

    //Konstruktors
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    //Geteri elementiem
    public static WebElement getAddToCartButton(){
        return driver.findElement(addToCartButton);
    }

    public static WebElement getShoppingCartLink(){
        return driver.findElement(shoppingCartLink);
    }
}
