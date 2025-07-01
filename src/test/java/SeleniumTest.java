import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.*;

public class SeleniumTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://pragmatic.bg/automation/example4");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testExamples() {
        WebElement element = driver.findElement(By.id("nextBid"));
        element.sendKeys("100");
    }
}