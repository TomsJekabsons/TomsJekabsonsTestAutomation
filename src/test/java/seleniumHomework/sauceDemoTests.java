package seleniumHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectsHomework.*;

public class sauceDemoTests {

    WebDriver driver;

    public final String SAUCELABS_URL= "https://www.saucedemo.com/";

    @BeforeMethod
    public void browserSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void pirmaisTestaScenarijs() throws InterruptedException {
        //Navigēt uz saiti
        driver.get(SAUCELABS_URL);
        LoginPage loginpage = new LoginPage(driver);

        //Ielogoties ar pareizu lietotāja vārdu un paroli
        loginpage.getUsernameInputField().sendKeys("standard_user");
        loginpage.getPasswordInputField().sendKeys("secret_sauce");
        loginpage.getLoginButton().click();

        //Pārbaudīt, ka lietotājs ir ielogojies
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        //Ievietot grozā 1 produktu
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getAddToCartButton().click();

        //Doties uz grozu
        inventoryPage.getShoppingCartLink().click();

        //Pārbaudīt, ka šī prece ir grozā
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartQuantity().getText(),"1");
        Assert.assertEquals(cartPage.getCartItem().getText(),"Sauce Labs Backpack");

        //Doties uz Checkout
        cartPage.getCheckoutButton().click();

        //Ievadīt vārdu/uzvārdu/pasta indeksu
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.getFirstName().sendKeys("Toms");
        checkoutPage.getLastName().sendKeys("Jēkabsons");
        checkoutPage.getZipPostalCode().sendKeys("LV-1015");

        //Doties uz Checkout overview lapu, pārbaudīt datus
        checkoutPage.getCheckoutContinue().click();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertEquals(checkoutOverviewPage.getCartQuantityOverview().getText(),"1");
        Assert.assertEquals(checkoutOverviewPage.getCartQuantityOverviewItem().getText(), "Sauce Labs Backpack");

        //Doties uz finish lapu un pārbaudīt vai viss bija veiksmīgi
        checkoutOverviewPage.getFinishButton().click();
        CheckoutSuccessPage checkoutSuccessPage = new CheckoutSuccessPage(driver);
        Assert.assertEquals(checkoutSuccessPage.getCheckoutComplete().getText(), "CHECKOUT: COMPLETE!");
        Assert.assertEquals(checkoutSuccessPage.getCompleteHeader().getText(), "THANK YOU FOR YOUR ORDER");

        //Doties atpakaļ uz pirmo lapu ar pogu "Back Home"
        checkoutSuccessPage.getBackHome().click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        Thread.sleep(5000);
    }

    @Test
    public void otraisTestaScenārijs() throws InterruptedException {
        //Navigēt uz saiti
        driver.get(SAUCELABS_URL);

        //Ielogoties ar pareizu lietotāja vārdu un paroli
        LoginPage loginpage = new LoginPage(driver);
        loginpage.getUsernameInputField().sendKeys("standard_user");
        loginpage.getPasswordInputField().sendKeys("secret_sauce");
        loginpage.getLoginButton().click();

        //Doties uz grozu
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getShoppingCartLink().click();

        //Doties uz Chekout
        CartPage cartPage = new CartPage(driver);
        cartPage.getCheckoutButton().click();

        //Pārbaudīt, ka FirstName/LastName/Zip code ir obligāts
        //Pārbaudīt, ka forma parāda pareizu kļūdas paziņojumu pie katra neievadītā lauka
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.getCheckoutContinue().click();
        Assert.assertEquals(checkoutPage.getFirstNameError().getText(), "Error: First Name is required");
        checkoutPage.getFirstName().sendKeys("Toms");
        checkoutPage.getCheckoutContinue().click();
        Assert.assertEquals(checkoutPage.getLastNameError().getText(), "Error: Last Name is required");
        checkoutPage.getLastName().sendKeys("Jēkabsons");
        checkoutPage.getCheckoutContinue().click();
        Assert.assertEquals(checkoutPage.getZipPostalCodeError().getText(), "Error: Postal Code is required");
        checkoutPage.getZipPostalCode().sendKeys("LV-1015");
        checkoutPage.getCheckoutContinue().click();

        Thread.sleep(5000);
    }

    @AfterMethod
    public void browserTearDown(){
        driver.quit();
    }
}
