package pageObject.acodemyShop;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MainPage {
    public WebDriver driver;
    private By searchInputField = By.id("woocommerce-product-search-field-0");
    private By searchResults = By.className("product");

    //Konstruktors
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }



    //Geteri Elementiem
    public WebElement getSearchInputField(){
        return driver.findElement(searchInputField);
    }
    public List<WebElement> getSearchResults(){
        return driver.findElements(searchResults);
    }


    //Saistīts ar 45 rindu Testa lapā!
    public void searchProduct(String meklejamaFraze){
        getSearchInputField().clear();
        getSearchInputField().sendKeys(meklejamaFraze);
        // Action pogas SVARĪGI !!!!!
        new Actions(driver).sendKeys(Keys.ENTER).perform();
    }

}
