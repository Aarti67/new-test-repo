package fi.TestNgs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;  // Import Duration
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestUIAutomation {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Make sure the correct path to chromedriver is set
        System.setProperty("webdriver.chrome.driver", "C:/driver/chromedriver.exe"); // Update path to your chromedriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/"); // Replace with the URL of your test page
    }

    @Test(priority = 1)
    public void testTextbox() {
        // Use WebDriverWait with Duration
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Use Duration
        WebElement textbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fname"))); // Replace with the actual ID
        textbox.sendKeys("Test Automation");
        Assert.assertEquals(textbox.getAttribute("value"), "Test Automation");
    }

    @Test(priority = 2)
    public void testButtonClick() {
        // Wait for the button to be clickable before interacting
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Use Duration
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button-class"))); // Replace with the actual CSS
        button.click();
        String expectedUrl = "https://the-internet.herokuapp.com/"; // Replace with the expected URL after click
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }

    @Test(priority = 3)
    public void testDropdown() {
        // Wait for dropdown to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Use Duration
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='testingDropdown']"))); // Replace with the actual XPath
        Select select = new Select(dropdown);
        select.selectByVisibleText("Option 1");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 1");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
