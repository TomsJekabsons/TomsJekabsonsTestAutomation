package mavenTestNGHomework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OtraisUzdevums {

    WebDriver chromeBrowseris;

    @BeforeSuite
    public void pirmsTesta(){
        System.out.println("Sākam testēt lapas nosaukumus!");
    }

    @BeforeTest
    public void atversana(){
        chromeBrowseris = new ChromeDriver();
    }

    @BeforeMethod
    public void testsSakas(){
        System.out.println("Tests sākas!");
    }

    @Test
    public void lapaViens(){
        String url = "https://www.facebook.com/";
        chromeBrowseris.get(url);
        String expectedTitle = "Facebook - log in or sign up";
        String actualTitle = chromeBrowseris.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void lapaDivi(){
        String url = "https://www.rigasmezi.lv/";
        chromeBrowseris.get(url);
        String expectedTitle = "Rīgas meži";
        String actualTitle = chromeBrowseris.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @AfterMethod
    public void testsBeidzas(){
        System.out.println("Tests beidzas!");
    }

    @AfterTest
    public void aizversana(){
        chromeBrowseris.quit();
    }

    @AfterSuite
    public void pecTesta(){
        System.out.println("Beidzam testēt lapas nosaukumus!");
    }
}
