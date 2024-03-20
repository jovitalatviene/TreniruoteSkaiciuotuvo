import org.example.Skaiciuotuvas;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.example.Skaiciuotuvas.browser;

public class SkaiciuotuvasTest {
    public static final String USER_NAME = Skaiciuotuvas.generateUniqueWord();
    @BeforeMethod
    public static void setup() {Skaiciuotuvas.setup();}
    @Test(priority = 1)
    public void createNewUserPositive(){
        Skaiciuotuvas.createNewUser(USER_NAME);
        boolean resultActual = Skaiciuotuvas.isModalContains(browser.findElement(By.xpath("/html/body/nav/div/ul[2]/a")), "Logout,");
        Assert.assertTrue(resultActual);
        Skaiciuotuvas.clickLogout();
    }
    @Test(priority = 2)
    public void createNewUserNegative(){
        Skaiciuotuvas.createNewUser(USER_NAME);
    //    Skaiciuotuvas.waitElement(By.xpath("//*[@id=\"username.errors\"]"));
    //    Skaiciuotuvas.waitElement(By.xpath("/html/body/nav/div/ul[2]/a"));
    //    boolean resultActual =! Skaiciuotuvas.isModalContains(browser.findElement(By.xpath("/html/body/nav/div/ul[2]/a")), "Logout,");
    //    boolean resultActual = Skaiciuotuvas.isModalContains(browser.findElement(By.xpath("//*[@id=\"username.errors\"]")),"Toks vartotojo vardas jau egzistuoja");
    //    Assert.assertTrue(resultActual);
        String resultActual = Skaiciuotuvas.findNewdUrl("http://localhost:8080/registruoti");
        Assert.assertEquals("Url nepasikeite - operacija neatlikta", resultActual);
    //    Skaiciuotuvas.clickLogout();
    }
    @Test(priority = 3)
    public void userConnectionPositive(){
        Skaiciuotuvas.userConnection(USER_NAME);
        Skaiciuotuvas.waitElement(By.xpath("/html/body/nav/div/ul[2]/a"));
        boolean resultActual = Skaiciuotuvas.isModalContains(browser.findElement(By.xpath("/html/body/nav/div/ul[2]/a")), "Logout,");
        Assert.assertTrue(resultActual);
    }
    @Test(priority = 4)
    public void userConnectionNegative(){
        Skaiciuotuvas.userConnection(USER_NAME);
        boolean resultActual =! Skaiciuotuvas.isModalContains(browser.findElement(By.xpath("/html/body/nav/div/ul[2]/a")), "Logout,");
        Assert.assertFalse(resultActual);
    }
    @Test(priority = 5)
    public void countOperationPositive(){
        Skaiciuotuvas.userConnection(USER_NAME);
        Skaiciuotuvas.countOperation(Skaiciuotuvas.generateFirstDigit(), Skaiciuotuvas.generateSecondDigit());
        boolean resultActual = Skaiciuotuvas.isModalContains(browser.findElement(By.xpath("/html/body/nav/div/ul[1]/li/a")), "Atliktos operacijos");
        Assert.assertTrue(resultActual);
    }
    @Test(priority = 6)
    public void countOperationNegative(){
        Skaiciuotuvas.userConnection(USER_NAME);
        Skaiciuotuvas.countOperation(Skaiciuotuvas.generateFirstDigit(), Skaiciuotuvas.generateSecondDigit());
        String resultActual = Skaiciuotuvas.findNewdUrl("http://localhost:8080/skaiciuoti");
        Assert.assertNotEquals("Url nepasikeite - operacija neatlikta", resultActual);
    }
    @Test(priority = 7)
    public void searchOperationShowPositive(){
        Skaiciuotuvas.userConnection(USER_NAME);
        Skaiciuotuvas.searchOperationShow();
        String resultActual = Skaiciuotuvas.findNewdUrl("http://localhost:8080/rodyti?id=");
        Assert.assertEquals("Url rodo, kad pereita i kita kortele - operacija atlikta", resultActual);
    }
    @Test(priority = 8)
    public void searchOperationShowNegative(){
        Skaiciuotuvas.userConnection(USER_NAME);
        Skaiciuotuvas.searchOperationShow();
        String resultActual = Skaiciuotuvas.findNewdUrl("http://localhost:8080/rodyti?id=");
        Assert.assertNotEquals("Url nepasikeite - operacija neatlikta", resultActual);
    }
    @Test(priority = 9)
    public void searchOperationChangePositive(){
        Skaiciuotuvas.userConnection(USER_NAME);
        Skaiciuotuvas.countOperation(Skaiciuotuvas.generateFirstDigit(), Skaiciuotuvas.generateSecondDigit());
        Skaiciuotuvas.searchOperationShow();
        Skaiciuotuvas.searchOperationChange();
        String resultActual = Skaiciuotuvas.findNewdUrl("http://localhost:8080/rodyti?id=");
        Assert.assertEquals("Url rodo, kad pereita i kita kortele - operacija atlikta", resultActual);
    }
    @Test(priority = 10)
    public void searchOperationChangeNegative(){
        Skaiciuotuvas.userConnection(USER_NAME);
        Skaiciuotuvas.countOperation(Skaiciuotuvas.generateFirstDigit(), Skaiciuotuvas.generateSecondDigit());
        Skaiciuotuvas.searchOperationShow();
        Skaiciuotuvas.searchOperationChange();
        String resultActual = Skaiciuotuvas.findNewdUrl("http://localhost:8080/rodyti?id=");
        Assert.assertNotEquals("Url nepasikeite - operacija neatlikta", resultActual);
    }
    @Test(priority = 11)
    public void searchOperationDeletePositive(){
        Skaiciuotuvas.userConnection(USER_NAME);
        Skaiciuotuvas.countOperation(Skaiciuotuvas.generateFirstDigit(), Skaiciuotuvas.generateSecondDigit());
        Skaiciuotuvas.searchOperationShow();
        Skaiciuotuvas.searchOperationDelete();
        String resultActual = Skaiciuotuvas.findNewdUrl("http://localhost:8080/trinti?id=");
        Assert.assertEquals("Url rodo, kad pereita i kita kortele - operacija atlikta", resultActual);
    }
    @Test(priority = 12)
    public void searchOperationDeleteNegative(){
        Skaiciuotuvas.userConnection(USER_NAME);
        Skaiciuotuvas.countOperation(Skaiciuotuvas.generateFirstDigit(), Skaiciuotuvas.generateSecondDigit());
        Skaiciuotuvas.searchOperationShow();
        Skaiciuotuvas.searchOperationDelete();
        String resultActual = Skaiciuotuvas.findNewdUrl("http://localhost:8080/trinti?id=");
        Assert.assertNotEquals("Url nepasikeite - operacija neatlikta", resultActual);
    }
    @AfterMethod
    public static void close() {Skaiciuotuvas.close();}
}
