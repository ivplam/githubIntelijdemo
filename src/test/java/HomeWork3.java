import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

public class HomeWork3 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testOrderStatusDropdownValues() {
        driver.get("https://auto.pragmatic.bg/manage/");
        System.out.println("Page loaded");

        // Login
        driver.findElement(By.id("input-username")).sendKeys("admin");
        driver.findElement(By.id("input-password")).sendKeys("parola123!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        System.out.println("Login submitted");

        // Изчакваме URL да съдържа "dashboard"
        wait.until(ExpectedConditions.urlContains("dashboard"));
        System.out.println("Dashboard loaded");

        // Hover върху менюто "Sales" и клик върху него
        WebElement salesMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-sale")));
        System.out.println("Sales menu found");
        new Actions(driver).moveToElement(salesMenu).click().perform();
        System.out.println("Hovered over and clicked Sales menu");

        // Изчакваме и кликаме на подменю "Orders" чрез по-гъвкав XPath
        WebElement ordersSubmenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'route=sale/order')]")));
        System.out.println("Orders submenu found");
        ordersSubmenu.click();
        System.out.println("Clicked on Orders submenu");

        // Изчакваме dropdown да стане видим
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("filter_order_status")));
        System.out.println("Dropdown found");

        // Работа с dropdown
        Select orderStatusDropdown = new Select(dropdownElement);

        List<String> optionTexts = orderStatusDropdown.getOptions().stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());

        System.out.println("Dropdown options: " + optionTexts);

        // Проверки
        assertTrue(optionTexts.contains("Pending"), "Dropdown must contain 'Pending'");
        assertTrue(optionTexts.contains("Complete"), "Dropdown must contain 'Complete'");
        assertTrue(optionTexts.size() > 1, "Dropdown must have more than 1 option");
    }
}

