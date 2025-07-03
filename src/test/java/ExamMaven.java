import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ExamMaven {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {


        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testRegisterNewUser() {
        driver.get("https://auto.pragmatic.bg/");

        WebElement myAccount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));
        myAccount.click();

        WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register")));
        registerLink.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));

        // Вземи всички задължителни полета с клас 'row mb-3 required'
        List<WebElement> requiredRows = driver.findElements(By.cssSelector(".row.mb-3.required"));

        int uniqueCounter = 1;
        for (WebElement row : requiredRows) {
            // Във всяка row търсим input полето
            List<WebElement> inputs = row.findElements(By.tagName("input"));
            if (inputs.isEmpty()) {
                inputs = row.findElements(By.tagName("textarea"));
            }

            for (WebElement input : inputs) {
                String type = input.getAttribute("type");
                if (type == null) type = "";

                // Ако е текстово поле, email или парола, попълваме уникална стойност
                if (type.equals("text") || type.equals("email") || type.equals("password") || input.getTagName().equals("textarea")) {
                    String uniqueValue = "test" + uniqueCounter + "@example.com";

                    if (type.equals("email")) {
                        uniqueValue = "user" + System.currentTimeMillis() + "@example.com";
                    } else if (type.equals("password")) {
                        uniqueValue = "Password123!";
                    } else if (input.getTagName().equals("textarea")) {
                        uniqueValue = "Some address " + uniqueCounter;
                    } else {
                        uniqueValue = "User" + uniqueCounter;
                    }

                    input.clear();
                    input.sendKeys(uniqueValue);
                    uniqueCounter++;
                }
                else if (type.equals("checkbox")) {
                    if (!input.isSelected()) {
                        input.click();
                    }
                }
            }
        }

        // Допълнителен чекбокс за съгласие (напр. Privacy Policy), ако има
        WebElement privacyCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.name("agree")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", privacyCheckbox);
        if (!privacyCheckbox.isSelected()) {
            privacyCheckbox.click();
        }

        WebElement continueButton = driver.findElement(By.cssSelector("input[type='submit'][value='Continue']"));
        continueButton.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#content h1")));

        Assert.assertTrue(successMessage.getText().contains("Your Account Has Been Created"),
                "Регистрацията е успешна!");
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}