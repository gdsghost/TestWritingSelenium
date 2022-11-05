package org.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class seleniumTesting {
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver","D:\\\\SEM7\\\\Online\\\\IS3440 - IT Quality Assurance\\\\Assignments\\\\IS3440_GroupAssignment\\\\Chrome Driver\\\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://urban.lk/");
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.urlToBe("https://urban.lk/"));


    }
}
