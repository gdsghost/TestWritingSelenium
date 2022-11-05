package org.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class testCasesSelenium {
    public WebDriver driver;

    @BeforeAll
    public static void setDriver(){
        System.setProperty("webdriver.chrome.driver","D:\\\\SEM7\\\\Online\\\\IS3440 - IT Quality Assurance\\\\Assignments\\\\IS3440_GroupAssignment\\\\Chrome Driver\\\\chromedriver.exe");
    }

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }

    @AfterEach
    public void quit(){
        driver.quit();
    }

    @Test
    @DisplayName("Check components of landing page")
    public void landingPageCheck() throws InterruptedException {
        driver.get("https://urban.lk/");
        TimeUnit.SECONDS.sleep(5);

        String actualTitle = driver.getTitle();
        String expectedTitle = "Urban Premium Online Store| Online Shopping Sri Lanka|eCommerce Sri Lanka | URBAN";

        if(expectedTitle.equals(actualTitle)){
            System.out.println("Test Pass - Landing Page Title");
        }
        else{
            System.out.println("Test Failed - Landing Page Title");
        }

        assertEquals("Actual title is not the expected title",expectedTitle, actualTitle);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement actualFooter = driver.findElement(By.xpath("//body/main[1]/div[1]/footer[1]/div[1]/div[2]/div[1]/div[1]/div[1]"));
        String expectedFooter = "Copyright © 2022 Urban Utopia. All Rights Reserved.";

        if(expectedFooter.equals(actualFooter.getText())){
            System.out.println("Test Pass - Landing Page Footer");
        }
        else{
            System.out.println("Test Failed - Landing Page Footer");
        }

        assertEquals("Actual footer texts are not same as expected footer texts",expectedFooter, actualFooter.getText());
    }

    @Test
    @DisplayName("Login with credentials and verify login")
    public void loginCheck() throws InterruptedException {
        driver.get("https://urban.lk/");
        TimeUnit.SECONDS.sleep(5);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.linkText("My Account")).click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlContains("https://urban.lk/customer/account/login/"));

        String actualTitle = driver.getTitle();
        String expectedTitle = "Customer Login | URBAN";

        if(expectedTitle.equals(actualTitle)){
            System.out.println("Test Pass - Customer Login Title");
        }
        else{
            System.out.println("Test Failed - Customer Login Title");
        }

        TimeUnit.SECONDS.sleep(6);

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("gdsudam@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("abc123");
        driver.findElement(By.xpath("//button[contains(@name,'send')]")).click();


        WebDriverWait wait1 = new WebDriverWait(driver,10);
        wait1.until(ExpectedConditions.urlToBe("https://urban.lk/customer/account/index/"));

        String actualTitle1 = driver.getTitle();
        String expectedTitle1 = "My Account | URBAN";

        if(expectedTitle1.equals(actualTitle1)){
            System.out.println("Test Pass - Customer Account");
        }
        else{
            System.out.println("Test Failed - Customer Account");
        }
    }

    @Test
    @DisplayName("Search with the keyword iphones and verfiy results")
    public void searchCheck() throws InterruptedException {
        driver.get("https://urban.lk/");
        TimeUnit.SECONDS.sleep(5);

        driver.findElement(By.name("q")).sendKeys("iphones");
        driver.findElement(By.xpath("//header/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/button[1]")).click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlToBe("https://urban.lk/catalogsearch/result/?q=iphones"));

        String actualTitle = driver.getTitle();
        String expectedTitle = "Search results for: 'iphones' | URBAN";

        if(expectedTitle.equals(actualTitle)){
            System.out.println("Test Pass - Search for iphones");
        }
        else{
            System.out.println("Test Failed - Search for iphones");
        }
    }

    @Test
    @DisplayName("Check product description")
    public void productDetailsCheck() throws InterruptedException {
        driver.get("https://urban.lk/apple-watch-series-7-41");
        TimeUnit.SECONDS.sleep(5);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String actualTitle = driver.getTitle();
        String expectedTitle = "Apple Watch Series 7 (GPS, 41mm) | Buy Original Urban.lk | URBAN";
        assertEquals("Expected title is differ form the actual title",expectedTitle, actualTitle);

        WebElement productName = driver.findElement(By.xpath("//body/main[1]/div[1]/section[1]/div[1]/h1[1]/span[1]"));
        String expectedProductName = "Apple Watch Series 7 (GPS, 41mm)";
        assertEquals("Actual product name is differ from the expected product name",expectedProductName, productName.getText());

        WebElement productActualPrice = driver.findElement(By.xpath("//span[contains(text(),'Rs. 159,799.00')]"));
        String expectedProductPrice = "Rs. 159,799.00";
        assertEquals("Actual product price is differ from the expected product price",expectedProductPrice, productActualPrice.getText());

        WebElement productInitialPrice = driver.findElement(By.xpath("//span[contains(text(),'Rs. 184,799.00')]"));
        String expectedProductInitialPrice = "Rs. 184,799.00";
        assertEquals("Actual product name is differ from the expected product name",expectedProductInitialPrice, productInitialPrice.getText());
    }

    @Test
    @DisplayName("Checking customer contact page and redirecting to landing page")
    public void contactDetailsCheck() throws InterruptedException {
        driver.get("https://urban.lk/contact-us.html");
        TimeUnit.SECONDS.sleep(5);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String actualTitle = driver.getTitle();
        String expectedTitle = "Contact Us | URBAN";
        assertEquals(expectedTitle, actualTitle);

        WebElement actualPage = driver.findElement(By.xpath("//span[contains(text(),'Contact US')]"));
        String expectedPage = "Contact US";
        assertEquals("Expected page name is differ form the actual page name",expectedPage, actualPage.getText());

        WebElement actualEmail = driver.findElement(By.xpath("//span[contains(text(),'support@urban.lk')]"));
        String expectedEmail = "support@urban.lk";
        assertEquals("Actual email is differ from the expected email",expectedEmail, actualEmail.getText());

        WebElement ActualInquiries = driver.findElement(By.xpath("//span[contains(text(),'sales@urban.lk')]"));
        String expectedInquiries = "sales@urban.lk";
        assertEquals("Actual inquiries email is differ from the expected inquiries email",expectedInquiries, ActualInquiries.getText());

        WebElement actualCareers = driver.findElement(By.xpath("//span[contains(text(),'careers@urban.lk')]"));
        String expectedCareers = "careers@urban.lk";
        assertEquals("Actual product name is differ from the expected product name",expectedCareers, actualCareers.getText());

        TimeUnit.SECONDS.sleep(5);

        driver.findElement(By.xpath("//header/div[2]/div[1]/div[1]/div[1]/a[1]/img[1]")).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlToBe("https://urban.lk/"));

        String actualTitle2 = driver.getTitle();
        String expectedTitle2 = "Urban Premium Online Store| Online Shopping Sri Lanka|eCommerce Sri Lanka | URBAN";

        if(expectedTitle2.equals(actualTitle2)){
            System.out.println("Test Pass - Menu");
        }
        else{
            System.out.println("Test Failed - Menu");
        }
    }
}
