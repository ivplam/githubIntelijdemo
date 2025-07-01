import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class ElementTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://pragmatic.bg/automation/lecture13/DoubleClickDemo.html");
    }

    @Test
    public void testElementText() {
        // Get the message Element
        WebElement message = driver.findElement(By.id("message"));
        // Get the message element's text
        String messageText = message.getText();
        // Verify message element's text displays "Click on me and my color will change"
        assertEquals(messageText, "Click on me and my color will change");

        // Get the area Element
        WebElement area = driver.findElement(By.id("area"));
        // Verify area element's text displays "Div's Text\nSpan's Text"
        assertEquals(area.getText(), "Div's Text\nSpan's Text");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}