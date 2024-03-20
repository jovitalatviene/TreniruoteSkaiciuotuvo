package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Skaiciuotuvas {
    public static WebDriver browser;
    public static final String PASSWD = "jovita1983";
    public static final int SECONDS_WAIT_TIME_FOR_ELEMENT = 2;
    public static void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");

        browser = new ChromeDriver(chromeOptions);
        browser.get("http://localhost:8080/");
    }

    public  static void createNewUser(String newWord) {
        WebElement createAccount = browser.findElement(By.xpath("/html/body/div/form/div/h4/a"));
        createAccount.click();
        WebElement newUserInput = browser.findElement(By.id("username"));
        newUserInput.sendKeys(newWord);
        WebElement newPassword = browser.findElement(By.id("password"));
        newPassword.sendKeys(PASSWD);
        WebElement repeatPassword = browser.findElement(By.id("passwordConfirm"));
        repeatPassword.sendKeys(PASSWD);
        clickOnElement(By.xpath("//*[@id=\"userForm\"]/button"));
    //    WebElement nameAlsoExists = browser.findElement(By.id("username.errors"));
    //    WebElement errorPasswd = browser.findElement(By.id("password.errors"));
    //    WebElement notConfirmPasswd = browser.findElement(By.id("passwordConfirm.errors"));
//
    //    if (nameAlsoExists.isDisplayed() || errorPasswd.isDisplayed() || notConfirmPasswd.isDisplayed()){
    //        System.out.println("Netinkamai suvesti duomenys");
    //    }
    //    else {
    //        System.out.println("Vartotoja pavyko uzregistruoti");
    //    }
    //    WebElement createUser = browser.findElement(By.xpath("//*[@id=\"userForm\"]/button"));
    //    createUser.click();

     //   isModalContains(browser.findElement(By.xpath("/html/body/nav/div/ul[2]/a")), "Logout,");

    }

    public static void userConnection(String newWord){
           WebElement userName = browser.findElement(By.xpath("/html/body/div/form/div/input[1]"));
           userName.sendKeys(newWord);
           WebElement userPasswd = browser.findElement(By.xpath("/html/body/div/form/div/input[2]"));
           userPasswd.sendKeys(PASSWD);
    //       WebElement connectUserBtn = browser.findElement(By.xpath("/html/body/div/form/div/button"));
    //       connectUserBtn.click();
           clickOnElement(By.xpath("/html/body/div/form/div/button"));
    //       isModalContains(browser.findElement(By.xpath("/html/body/nav/div/ul[2]/a")), "Logout,");
    }
    public static void countOperation(int newFirstDigit, int newSecondDigit){
        WebElement firstDigit = browser.findElement(By.id("sk1"));
        firstDigit.clear();
        firstDigit.sendKeys(Integer.toString(newFirstDigit));
        WebElement secondDigit = browser.findElement(By.id("sk2"));
        secondDigit.clear();
        secondDigit.sendKeys(Integer.toString(newSecondDigit));
        WebElement dropdown = browser.findElement(By.xpath("//*[@id=\"number\"]/select"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Sudėtis");
        clickOnElement(By.xpath("//*[@id=\"number\"]/input[3]"));
 //       isModalContains(browser.findElement(By.xpath("/html/body/nav/div/ul[1]/li/a")), "Atliktos operacijos");
 //       System.out.println(findNewdUrl("http://localhost:8080/skaiciuoti"));
    }
    public static void waitElement(By locator) {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(SECONDS_WAIT_TIME_FOR_ELEMENT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor)  browser;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    public static void searchOperationShow(){
        clickOnElement(By.xpath("/html/body/nav/div/ul[1]/li/a"));
//        JavascriptExecutor js = (JavascriptExecutor) browser;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        scrollDown();
        WebElement lastElement = browser.findElement(By.xpath("/html/body/div/table/tbody/tr[last()]/td[5]/a[3]"));
        if (lastElement.isDisplayed()){
            System.out.println("Paskutinis įrašas matomas");
        }
        else {
            System.out.println("Paskutinis įrašas nematomas");
        }
        WebElement rodyti = browser.findElement(By.xpath("/html/body/div/table/tbody/tr[last()]/td[5]/a[3]"));
        rodyti.click();
 //       System.out.println(findNewdUrl("http://localhost:8080/rodyti?id="));
    }
    public static void searchOperationChange() {
        clickOnElement(By.xpath("/html/body/a"));
        scrollDown();
        clickOnElement(By.xpath("/html/body/div/table/tbody/tr[last()]/td[5]/a[1]"));
        System.out.println(findNewdUrl("http://localhost:8080/atnaujinti?id="));
        WebElement firstDigit = browser.findElement(By.xpath("//*[@id=\"command\"]/p[1]/input"));
        firstDigit.clear();
        firstDigit.sendKeys("40");
        WebElement secondDigit = browser.findElement(By.xpath("//*[@id=\"command\"]/p[3]/input"));
        secondDigit.clear();
        secondDigit.sendKeys("60");
        clickOnElement(By.xpath("//*[@id=\"command\"]/p[5]/input"));
//        System.out.println(findNewdUrl("http://localhost:8080/rodyti?id="));
    }
    public static void searchOperationDelete(){
        clickOnElement(By.xpath("/html/body/a"));
        scrollDown();
        clickOnElement(By.xpath("/html/body/div/table/tbody/tr[last()]/td[5]/a[2]"));
        Alert alert = browser.switchTo().alert();
        alert.accept();
//        System.out.println(findNewdUrl("http://localhost:8080/trinti?id="));
    }
//   public static  void hoverElement(WebElement element) {
//       Actions act = new Actions(browser);
//       act.moveToElement(element).build().perform();
//   }
    public static boolean isModalContains(WebElement modalInfo, String keyword) {
        String info = modalInfo.getText();
        if (modalInfo.isEnabled() && modalInfo.isDisplayed() || info.contains(keyword)) {
            System.out.println("Pavyko sėkmingai");
            return true;
        }
        else {
            System.out.println("nepavyko");
            return false;
        }
    }
    public static String findNewdUrl(String keyword) {
        String newUrl = browser.getCurrentUrl();
        System.out.println(newUrl);
        if (newUrl.contains(keyword)) {   //numetu dvizenkli id numeri
            return "Url rodo, kad pereita i kita kortele - operacija atlikta";
        }
        return "Url nepasikeite - operacija neatlikta";
    }

    public static void clickOnElement(By locator){
             browser.findElement(locator).click();
    }
    public static void clickLogout(){
        WebElement logout = browser.findElement(By.xpath("/html/body/nav/div/ul[2]/a"));
        logout.click();
    }
    public static String generateUniqueWord() {
        Random randomWord = new Random();
        return "jovi" + randomWord.nextInt(20000);
    }
    public static int generateFirstDigit(){
        Random randDigit = new Random();
        return randDigit.nextInt(100);
    }
    public static int generateSecondDigit(){
        Random randDigit = new Random();
        return randDigit.nextInt(100);
    }
    public static void close(){browser.close();}
    public static void main(String[] args) {
        System.out.println("Testuojame internetinį skaičiuotuvą");

        setup();
        createNewUser(generateUniqueWord());
        isModalContains(browser.findElement(By.xpath("/html/body/nav/div/ul[2]/a")), "Logout,");
        clickLogout();
        userConnection(generateUniqueWord());
        countOperation(generateFirstDigit(),generateSecondDigit());
        isModalContains(browser.findElement(By.xpath("/html/body/nav/div/ul[1]/li/a")), "Atliktos operacijos");
 //       System.out.println(findNewdUrl("http://localhost:8080/skaiciuoti"));
        searchOperationShow();
        System.out.println(findNewdUrl("http://localhost:8080/rodyti?id="));
     //   isModalContains(browser.findElement(By.xpath("/html/body/div/text()")), "Skaičiaus informacija");
     //   System.out.println(findNewdUrl("http://localhost:8080/rodyti?id="));
        searchOperationChange();
        System.out.println(findNewdUrl("http://localhost:8080/rodyti?id="));
        searchOperationDelete();
        System.out.println(findNewdUrl("http://localhost:8080/trinti?id="));
        close();





    }
}