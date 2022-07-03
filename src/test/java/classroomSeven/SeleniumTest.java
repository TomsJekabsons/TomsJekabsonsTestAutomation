package classroomSeven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.InventoryPage;
import pageObject.LoginPage;

import java.time.Duration;
import java.util.List;

public class SeleniumTest {

    WebDriver driver;

    WebDriverWait wait;
    public final String SAUCELABS_URL = "https://www.saucedemo.com/"; // nosakām konstanti, ja dublējās ieraksti!

    @BeforeMethod
    public void setupBrowser(){
        System.out.println("Pirms testa!");
        driver = new ChromeDriver(); // nosakam driveri
        driver.manage().window().maximize(); // izmantot maksimālo ekrāna izšķirtspēju

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // gaidīt ja kāds elements uzreiz neiedod response - Netiešā gaidīšana

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Locālā faila testēšana
    @Test
    public void localHTMLExerciseTest() throws InterruptedException {
        driver.get("file://C:\\Users\\Toms\\majasdarbs\\TomsJekabsonsTestAutomation\\src\\test\\resources\\elements.html");

        WebElement descriptionTextArea = driver.findElement(By.name("description"));
        descriptionTextArea.clear(); // notīram lauku

        descriptionTextArea.sendKeys("Šis ir teksts par mani!");

        WebElement linkElements = driver.findElement(By.linkText("Link Text")); //atrodam linku
        linkElements.click(); // nospiežam linku

        WebElement isStudentCheckbox = driver.findElement(By.id("studentID"));
        Assert.assertEquals(isStudentCheckbox.isSelected(), false);
        isStudentCheckbox.click();
        Assert.assertEquals(isStudentCheckbox.isSelected(), true);

        //krāsu dropdowns ar vairākām iespējām
        Select milakaKrasa = new Select(driver.findElement(By.id("colorsID")));
        milakaKrasa.selectByIndex(0); //pirmā iespēja
        milakaKrasa.selectByIndex(3); //pēdējā iespēja

        List<WebElement> sarakstsArKrasam = milakaKrasa.getOptions(); // iegūt masīvu
        for (int i = 0; i < sarakstsArKrasam.size(); i++) {
            System.out.println(sarakstsArKrasam.get(i).getText()); // izprintēt masīvu
        }

        WebElement nospiedManiPoga = driver.findElement(By.id("checkDataID"));
        nospiedManiPoga.click();

        WebElement vissIrLabiTeksts = driver.findElement(By.id("checkDataResultID"));
        wait.until(ExpectedConditions.elementToBeClickable(vissIrLabiTeksts)); // gaidīšana
        vissIrLabiTeksts.click();


        Thread.sleep(2000);
    }

    @Test
    public void successFullLoginTest() throws InterruptedException {
        System.out.println("Veiskmīgs logins");

        driver.get(SAUCELABS_URL); // izmantojam konstanti

        //Aizpildam lietotājvārdu
        WebElement usernameInputField = driver.findElement(By.id("user-name"));
        usernameInputField.sendKeys("standard_user"); // ierakstīt laukā tekstu

        //Aizpildam paroli ar tukšumu
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("secret_sauce");

        //Nospiežam login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        //Timeouts 1sec
        Thread.sleep(1000);

        //Pārbaudam kurā url atrodās tests
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        //Pārbaudam vai pareizs page title
        WebElement pageTitleText = driver.findElement(By.cssSelector("div[id ='header_container'] span[class ='title']"));
        Assert.assertEquals(pageTitleText.getText(), "PRODUCTS");

        //Dropdown izmantošana, tikai ja klase ir Select!!!!!!
        Select sortDropdown = new Select(driver.findElement(By.cssSelector("select[data-test='product_sort_container']")));
        sortDropdown.selectByValue("hilo");

        Thread.sleep(1000);

        // Gadās ka nepieciešams atkārtoti meklēt elementu
        sortDropdown = new Select(driver.findElement(By.cssSelector("select[data-test='product_sort_container']")));
        sortDropdown.selectByIndex(1);

        WebElement linkedInLink = driver.findElement(By.linkText("LinkedIn"));
        linkedInLink.click();


        Thread.sleep(3000);

    }

    @Test
    public void errorMessageUsernameAndPasswordEmptyTest() throws InterruptedException {
        System.out.println("Pirmais tests!");
        //scenārijs 1
        driver.get(SAUCELABS_URL); // izmantojam konstanti

        //Aizpildam lietotājvārdu
        WebElement usernameInputField = driver.findElement(By.id("user-name"));
        usernameInputField.sendKeys(""); // ierakstīt laukā tekstu
        Thread.sleep(1000); //timeouts uz neilgu laiku

        //Aizpildam paroli ar tukšumu
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("");

        //Nospiežam login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement errorText = driver.findElement(By.cssSelector("h3[data-test='error']")); //meklējam tekstu
        String actualText = errorText.getText();
        String expectedText = "Epic sadface: Username is required";
        Assert.assertEquals(errorText.getText(), expectedText); //salīdzinam error tekstu

    }

    @Test
    public void errorMessageUsernameEmptyTest() throws InterruptedException {
        System.out.println("Otrais tests!");
        //scenārijs 1
        driver.get(SAUCELABS_URL); // izmantojam konstanti

        //Aizpildam lietotājvārdu
        WebElement usernameInputField = driver.findElement(By.id("user-name"));
        usernameInputField.sendKeys(""); // ierakstīt laukā tekstu

        //Aizpildam paroli ar tukšumu
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("Parole");

        //Nospiežam login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement errorText = driver.findElement(By.cssSelector("h3[data-test='error']")); //meklējam tekstu
        String actualText = errorText.getText();
        String expectedText = "Epic sadface: Username is required";
        Assert.assertEquals(errorText.getText(), expectedText); //salīdzinam error tekstu

    }

    @Test
    public void successFullLoginTestPageObject() throws InterruptedException {
        driver.get(SAUCELABS_URL); // izmantojam konstanti
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getUsernameInputField().sendKeys("standard_user");
        loginPage.getPasswordInputField().sendKeys("secret_sauce");
        loginPage.getLoginButton().click();
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle().getText(),"PRODUCTS");
        inventoryPage.getLinkedIn().click();
        Thread.sleep(5000);

    }

    @Test
    public void errorMessagePasswordEmptyPageObjektTest(){
        driver.get(SAUCELABS_URL); // izmantojam konstanti

        //Īsā versija
        LoginPage loginPage = new LoginPage(driver); // no konstruktora
        loginPage.login("Toms", "");

        loginPage.getErrorText().getText();
        Assert.assertEquals(loginPage.getErrorText().getText(), "Epic sadface: Password is required");
    }

    @Test
    public void errorMessageUsernameEmptyPageObjektTest(){
        driver.get(SAUCELABS_URL); // izmantojam konstanti

        LoginPage loginPage = new LoginPage(driver); // no konstruktora
        loginPage.getUsernameInputField().sendKeys("");
        loginPage.getPasswordInputField().sendKeys("Parole");
        loginPage.getLoginButton().click();
        loginPage.getErrorText().getText();
        Assert.assertEquals(loginPage.getErrorText().getText(), "Epic sadface: Username is required");
    }


    @Test
    public void errorMessagePasswordEmptyTest() throws InterruptedException {
        System.out.println("Trešais tests!");
        //scenārijs 1
        driver.get(SAUCELABS_URL); // izmantojam konstanti

        WebElement acceptedUsernamesText = driver.findElement(By.xpath("//div[@id='login_credentials']//h4")); //XPath izmantošana
        System.out.println(acceptedUsernamesText.getText()); //Teksta izvadīšana

        //Aizpildam lietotājvārdu
        WebElement usernameInputField = driver.findElement(By.id("user-name"));
        usernameInputField.sendKeys("Toms"); // ierakstīt laukā tekstu

        //Aizpildam paroli ar tukšumu
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("");

        //Nospiežam login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement errorText = driver.findElement(By.cssSelector("h3[data-test='error']")); //meklējam tekstu
        String actualText = errorText.getText();
        String expectedText = "Epic sadface: Password is required";
        Assert.assertEquals(errorText.getText(), expectedText); //salīdzinam error tekstu

    }

    @Test
    public void errorMessageWrongCredentials() throws InterruptedException {
        System.out.println("Ceturtais tests!");
        //scenārijs 1
        driver.get(SAUCELABS_URL); // izmantojam konstanti

        //Aizpildam lietotājvārdu
        WebElement usernameInputField = driver.findElement(By.id("user-name"));
        usernameInputField.sendKeys("Toms"); // ierakstīt laukā tekstu

        //Aizpildam paroli ar tukšumu
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("Parole");

        //Nospiežam login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement errorText = driver.findElement(By.cssSelector("h3[data-test='error']")); //meklējam tekstu
        String actualText = errorText.getText();
        String expectedText = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(errorText.getText(), expectedText); //salīdzinam error tekstu

    }

    @AfterMethod
    public void tearDownBrowser(){
        System.out.println("Pēc testa!");

        driver.quit(); // slēdzam browseri
    }


}
