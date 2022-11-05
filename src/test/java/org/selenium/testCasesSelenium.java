package org.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class testCasesSelenium {
    public WebDriver driver;
    public Properties obj;

    @BeforeAll
    public static void setDriver(){
        System.setProperty("webdriver.chrome.driver","D:\\\\SEM7\\\\Online\\\\IS3440 - IT Quality Assurance\\\\Assignments\\\\IS3440_GroupAssignment\\\\Chrome Driver\\\\chromedriver.exe");
    }

    @BeforeEach
    public void setup() throws IOException {
        driver = new ChromeDriver();
        obj = readingProperties();
    }

    @AfterEach
    public void quit(){
        driver.quit();
    }

    public Properties readingProperties() throws IOException {
        Properties obj = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\application.properties");
        obj.load(objfile);
        return obj;
    }

    @Test
    @DisplayName("1.Check components of landing page")
    public void landingPageCheck() throws InterruptedException, IOException {
        driver.get(obj.getProperty("landingPage"));
        driver.manage().window().maximize();
        TimeUnit.SECONDS.sleep(5);

        String actualTitle = driver.getTitle();
        String expectedTitle = obj.getProperty("landingPageTitle");

        if(expectedTitle.equals(actualTitle)){
            System.out.println("Test Pass - Landing Page Title");
        }
        else{
            System.out.println("Test Failed - Landing Page Title");
        }

        assertEquals("Actual title is not the expected title",expectedTitle, actualTitle);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement actualFooter = driver.findElement(By.xpath(obj.getProperty("footerElement")));
        String expectedFooter = obj.getProperty("footerElementText");

        if(expectedFooter.equals(actualFooter.getText())){
            System.out.println("Test Pass - Landing Page Footer");
        }
        else{
            System.out.println("Test Failed - Landing Page Footer");
        }

        assertEquals("Actual footer texts are not same as expected footer texts",expectedFooter, actualFooter.getText());
    }

    @Test
    @DisplayName("2.Login with credentials and verify login")
    public void loginCheck() throws InterruptedException, IOException {
        driver.get(obj.getProperty("landingPage"));
        driver.manage().window().maximize();
        TimeUnit.SECONDS.sleep(5);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.linkText(obj.getProperty("myAccountLinkText"))).click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlContains(obj.getProperty("loginUrl")));

        String actualTitle = driver.getTitle();
        String expectedTitle = obj.getProperty("loginTitle");

        if(expectedTitle.equals(actualTitle)){
            System.out.println("Test Pass - Customer Login Title");
        }
        else{
            System.out.println("Test Failed - Customer Login Title");
        }

        TimeUnit.SECONDS.sleep(6);

        driver.findElement(By.xpath(obj.getProperty("emailTextBox"))).sendKeys(obj.getProperty("email"));
        driver.findElement(By.id(obj.getProperty("passwordTextBox"))).sendKeys(obj.getProperty("password"));
        driver.findElement(By.xpath(obj.getProperty("loginButton"))).click();


        WebDriverWait wait1 = new WebDriverWait(driver,10);
        wait1.until(ExpectedConditions.urlToBe(obj.getProperty("accountUrl")));

        String actualTitle1 = driver.getTitle();
        String expectedTitle1 = obj.getProperty("myAccountTitle");

        if(expectedTitle1.equals(actualTitle1)){
            System.out.println("Test Pass - Customer Account");
        }
        else{
            System.out.println("Test Failed - Customer Account");
        }
    }

    @Test
    @DisplayName("3.Search with the keyword iphones and verfiy results")
    public void searchCheck() throws InterruptedException, IOException {
        driver.get(obj.getProperty("landingPage"));
        driver.manage().window().maximize();
        TimeUnit.SECONDS.sleep(5);

        driver.findElement(By.name(obj.getProperty("searchElement"))).sendKeys(obj.getProperty("searchKey"));
        driver.findElement(By.xpath(obj.getProperty("searchButton"))).click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlToBe(obj.getProperty("searchResultUrl")));

        String actualTitle = driver.getTitle();
        String expectedTitle = obj.getProperty("searchResultTitle");

        if(expectedTitle.equals(actualTitle)){
            System.out.println("Test Pass - Search for iphones");
        }
        else{
            System.out.println("Test Failed - Search for iphones");
        }
    }

    @Test
    @DisplayName("4.Check product description")
    public void productDetailsCheck() throws InterruptedException, IOException {
        driver.get(obj.getProperty("productDescriptionUrl"));
        driver.manage().window().maximize();
        TimeUnit.SECONDS.sleep(5);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String actualTitle = driver.getTitle();
        String expectedTitle = obj.getProperty("productDescriptionTitle");
        assertEquals("Expected title is differ form the actual title",expectedTitle, actualTitle);

        WebElement productName = driver.findElement(By.xpath(obj.getProperty("productNameElement")));
        String expectedProductName = obj.getProperty("productName");
        assertEquals("Actual product name is differ from the expected product name",expectedProductName, productName.getText());

        WebElement productActualPrice = driver.findElement(By.xpath(obj.getProperty("productActualPriceElement")));
        String expectedProductPrice = obj.getProperty("productActualPrice");
        assertEquals("Actual product price is differ from the expected product price",expectedProductPrice, productActualPrice.getText());

        WebElement productInitialPrice = driver.findElement(By.xpath(obj.getProperty("productInitialPriceElement")));
        String expectedProductInitialPrice = obj.getProperty("productInitialPrice");
        assertEquals("Actual product name is differ from the expected product name",expectedProductInitialPrice, productInitialPrice.getText());
    }

    @Test
    @DisplayName("5.Checking customer contact page and redirecting to landing page")
    public void contactDetailsCheck() throws InterruptedException, IOException {
        driver.get(obj.getProperty("contactUsUrl"));
        driver.manage().window().maximize();
        TimeUnit.SECONDS.sleep(5);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String actualTitle = driver.getTitle();
        String expectedTitle = obj.getProperty("contactUsTitle");
        assertEquals(expectedTitle, actualTitle);

        WebElement actualPage = driver.findElement(By.xpath(obj.getProperty("contactUsPageNameElement")));
        String expectedPage = obj.getProperty("contactUsPageName");
        assertEquals("Expected page name is differ form the actual page name",expectedPage, actualPage.getText());

        WebElement actualEmail = driver.findElement(By.xpath(obj.getProperty("actualEmailElement")));
        String expectedEmail = obj.getProperty("actualEmail");
        assertEquals("Actual email is differ from the expected email",expectedEmail, actualEmail.getText());

        WebElement ActualInquiries = driver.findElement(By.xpath(obj.getProperty("inquiriesEmailElement")));
        String expectedInquiries = obj.getProperty("inquiriesEmail");
        assertEquals("Actual inquiries email is differ from the expected inquiries email",expectedInquiries, ActualInquiries.getText());

        WebElement actualCareers = driver.findElement(By.xpath(obj.getProperty("careersElement")));
        String expectedCareers = obj.getProperty("careersEmail");
        assertEquals("Actual product name is differ from the expected product name",expectedCareers, actualCareers.getText());

        TimeUnit.SECONDS.sleep(5);

        driver.findElement(By.xpath(obj.getProperty("iconElement"))).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlToBe(obj.getProperty("landingPage")));

        String actualTitle2 = driver.getTitle();
        String expectedTitle2 = obj.getProperty("landingPageTitle");

        if(expectedTitle2.equals(actualTitle2)){
            System.out.println("Test Pass - Menu");
        }
        else{
            System.out.println("Test Failed - Menu");
        }
    }
}
