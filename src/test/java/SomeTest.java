import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class SomeTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = null; // ще актуализираме тази част с конкретна инициализация
        driver.get("http://pragmatic.bg/automation/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testExample1() {
        // Тук ще добавим примери
    }

    @Test
    public void testExample2() {
        // Тук ще добавим примери
    }
}