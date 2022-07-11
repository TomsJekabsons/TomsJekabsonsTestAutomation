package classroomEight;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.acodemyShop.MainPage;
import pageObjectsHomework.CartPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class AcodemyShopTest {
    WebDriver driver;
    WebDriverWait wait;
    public final String ACODEMY_SHOP_URL = "http://shop.acodemy.lv/";


    @BeforeMethod
    public void setupBrowser() throws MalformedURLException {
        System.out.println("Pirms testa!");

//        driver = new ChromeDriver(); // nosakam driveri


        //Remote servera atvēršanai

//        InternetExplorerOptions browserOptions = new InternetExplorerOptions();
//        browserOptions.setCapability("platformName", "Windows 10");
//        browserOptions.setCapability("browserVersion", "11");
//        Map<String, Object> sauceOptions = new HashMap<>();
//        sauceOptions.put("build", "<your build id>");
//        sauceOptions.put("name", "<your test name>");
//        browserOptions.setCapability("sauce:options", sauceOptions);
//        driver = new RemoteWebDriver(new URL("https://oauth-toms.jekabsons25-5923d:20645c37-d737-48ce-96ae-e596050ce040@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), browserOptions);

        //Vai

//        ChromeOptions chromeOptions = new ChromeOptions();
//        driver = new RemoteWebDriver(new URL("https://oauth-toms.jekabsons25-5923d:20645c37-d737-48ce-96ae-e596050ce040@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), chromeOptions);

        // INFO -----> var izmantot arī driver = new SafariDriver();


        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://192.168.8.100:4444/"), chromeOptions);
        driver.manage().window().maximize(); // izmantot maksimālo ekrāna izšķirtspēju
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // gaidīt ja kāds elements uzreiz neiedod response - Netiešā gaidīšana
        //driver = new WebDriver(
                //new URL("https://oauth-toms.jekabsons25-5923d:20645c37-d737-48ce-96ae-e596050ce040@ondemand.eu-central-1.saucelabs.com:443/wd/hub"));
    }

    @Test
    public void searchForItemTest() throws InterruptedException {
        driver.get(ACODEMY_SHOP_URL);
        MainPage mainPage = new MainPage(driver);
//        mainPage.getSearchInputField().sendKeys("Beanie");
//        //Action pogas SVARĪGI !!!!!
//        new Actions(driver).sendKeys(Keys.ENTER).perform();
        mainPage.searchProduct("Beanie");

        System.out.println("Seach results count:" + mainPage.getSearchResults().size());
        Assert.assertEquals(mainPage.getSearchResults().size(), 2);

        List<WebElement>searchResults = mainPage.getSearchResults();
        // iterējam cauri sarakstam!
        for (int i = 0; i < mainPage.getSearchResults().size(); i++) {
            System.out.println(mainPage.getSearchResults().get(i).findElement(By.cssSelector("h2")).getText()); //meklējam visus nosaukumus
            System.out.println(mainPage.getSearchResults().get(i).findElement(By.className("price")).getText()); // meklējam visas cenas
        }
        Thread.sleep(3000);
    }

    @Test
    public void switchTabsTest() throws InterruptedException {
        driver.get("https://www.w3schools.com/");
        //Driveris nolasa atvērto tabu skaitu
        System.out.println("Atvērti tabi: " + driver.getWindowHandles().size());

        driver.findElement(By.id("accept-choices")).click();
        driver.findElement(By.cssSelector("[href='https://www.linkedin.com/company/w3schools.com/']")).click();

        //Tabu pārslēgšanās !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        //Pārslēdzamies uz pirmo tabu
        driver.switchTo().window(tabs.get(1));
        System.out.println(driver.getTitle());
        driver.close();
        driver.switchTo().window(tabs.get(0));
        System.out.println(driver.getTitle());

        //Driveris nolasa atvērto tabu skaitu
        System.out.println("Atvērti tabi: " + driver.getWindowHandles().size());

        Thread.sleep(3000);
    }

    @Test
    public void javaScriptExecutorExampleTest() throws InterruptedException {
        driver.get("https://www.lu.lv/");
        driver.findElement(By.xpath("//div[@class='ccb__button']/button[@class='consent-give']")).click();
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,3000)");
        Thread.sleep(2000);
        driver.findElement(By.className("footer__up")).click();

        WebElement element = driver.findElement(By.linkText("Kontakti"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        Thread.sleep(3000);
    }

    @Test
    public void hoverTest() throws InterruptedException {
        driver.get("https://www.w3schools.com/howto/howto_css_dropdown.asp");
        driver.findElement(By.id("accept-choices")).click();
        WebElement hoverMeButton = driver.findElement(By.cssSelector("div.dropdown2 button"));
        new Actions(driver).moveToElement(hoverMeButton).perform();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Link 1")).click();
    }

    @Test
    public void seleniumDocTest() throws InterruptedException {
        driver.get("https://www.selenium.dev/documentation/");
        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .sendKeys("k")
                .perform();
        Thread.sleep(3000);

        driver.findElement(By.id("docsearch-input")).click();
        driver.findElement(By.id("docsearch-input")).sendKeys("Selenium");
        Thread.sleep(3000);

        new Actions(driver)
                .sendKeys("Selenium")
                .perform();
        Thread.sleep(3000);

    }

    @Test
    public void keyboardClickTest() throws InterruptedException {
        driver.get("https://www.microsoft.com/applied-sciences/projects/anti-ghosting-demo");
        driver.findElement(By.id("clickToUseButton")).click();
        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .keyDown(Keys.ALT)
                .perform();
        Thread.sleep(3000);
        new Actions(driver)
                .keyUp(Keys.ALT)
                .perform();
        Thread.sleep(3000);

        new Actions(driver)
                .sendKeys("J")
                .sendKeys("U")
                .sendKeys("R")
                .sendKeys("S")
                .sendKeys("I")
                .sendKeys("P")
                .perform();
        Thread.sleep(3000);

    }

    @AfterMethod
    public void tearDownBrowser(){
        System.out.println("Pēc testa!");
        driver.quit();
    }


}
