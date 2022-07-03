package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    public WebDriver driver;
    private By usernameInputField = By.id("user-name");
    private By passworsInputField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorText = By.cssSelector("h3[data-test='error']");


    //Konstruktors
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Geteris userneimam!
    public WebElement getUsernameInputField(){
        return driver.findElement(usernameInputField);
    }

    // Geteris parolei
    public WebElement getPasswordInputField(){
        return driver.findElement(passworsInputField);
    }

    public WebElement getLoginButton(){
        return driver.findElement(loginButton);
    }

    public WebElement getErrorText(){
        return driver.findElement(errorText);
    }

    public void login(String username, String password){
        getUsernameInputField().sendKeys(username);
        getPasswordInputField().sendKeys(password);
        getLoginButton().click();
    }

}
