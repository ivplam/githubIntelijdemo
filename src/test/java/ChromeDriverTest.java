import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ChromeDriverTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.pragmatic.bg/automation/example4");
    }

    @Test
    public void verifyButtonExists() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // намираме бутона по CSS селектор с двата класа
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dt-sc-button.small")));
        Assert.assertTrue(button.isDisplayed(), "Button should be visible.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}