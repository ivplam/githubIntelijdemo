import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.assertTrue;
import java.time.Duration;

public class HomeWork1 {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://auto.pragmatic.bg/manage/");
    }

    @Test
    public void testValidLogin() {

        WebElement usernameField = driver.findElement(By.id("input-username"));
        WebElement passwordField = driver.findElement(By.id("input-password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameField.sendKeys("admin");
        passwordField.sendKeys("parola123!");
        loginButton.click();

        boolean isDashboardVisible = driver.getPageSource().contains("container-fluid");
        assertTrue(isDashboardVisible, "Грешка: Логинът не беше успешен – 'Dashboard' не е намерен!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
