package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageObject.InventoryPage;
import pageObject.LoginPage;

import java.time.Duration;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    public final String SAUCELABS_URL = "https://www.saucedemo.com/";

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }


    @Given("user navigates to {string}")
    public void user_navigates_to(String url) {
        driver.get(url);
    }



    @Given("User is navigated to saucelabs page")
    public void user_is_navigated_to_saucelabs_page() {
        driver.get(SAUCELABS_URL);
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }
    @When("user enters {string} and {string}")
    public void user_enters_and(String username, String password) {
        loginPage.getUsernameInputField().sendKeys(username);
        loginPage.getPasswordInputField().sendKeys(password);
    }
    @When("user clicks login button")
    public void user_clicks_login_button() {
        loginPage.getLoginButton().click();
    }
    @Then("user sees inventory page")
    public void user_sees_inventory_page() {
        Assert.assertEquals(inventoryPage.getPageTitle().getText(), "PRODUCTS");
    }

    @After
    public void tearDown(){
        driver.quit();
    }



    @Then("user sees page title {string}")
    public void userSeesPageTitlePagetitle(String title) {
        Assert.assertEquals(driver.getTitle(), title);
    }

    @Then("user sees error message {string}")
    public void userSeesErrorMessageErrormessage(String errormessage) {
        Assert.assertEquals(loginPage.getErrorText().getText(), errormessage);
    }
}
