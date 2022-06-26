package mavenTestNGHomework;

import org.openqa.selenium.json.JsonOutput;
import org.testng.Assert;
import org.testng.annotations.*;

public class CalculatorTestNG {

    @BeforeSuite
    public void pirmsTestaSuite(){
        System.out.println("Testējam Calculator klasi");
    }

    @BeforeMethod
    public void pirmsTesta(){
        System.out.println("Tests ir sācies!");
    }

    Calculator calculator;

    @BeforeTest
    public void calculatorObjekts(){
        calculator = new Calculator();
    }

    @Test
    public void add (){
        Assert.assertEquals(calculator.add(10,20),30);
    }

    @Test
    public void substract(){
        Assert.assertEquals(calculator.substract(30,15),15);
    }

    @Test
    public void multiply(){
        Assert.assertEquals(calculator.multiply(6,6),36);
    }

    @Test
    public void divide(){
        Assert.assertEquals(calculator.divide(9,3),3);
    }

    @AfterClass
    public void pēcTesta(){
        System.out.println("Tests ir beidzies!");
    }
}
