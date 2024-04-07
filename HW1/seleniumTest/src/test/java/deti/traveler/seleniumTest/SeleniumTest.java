package deti.traveler.seleniumTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import io.github.bonigarcia.seljup.SeleniumJupiter;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
@SpringBootTest
class SeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void startDriver(FirefoxDriver ddriver) {
        driver = ddriver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void killDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testSelenium() {
            // Open the URL
            driver.get("http://localhost:5173");

            // Set window size
            driver.manage().window().setSize(new Dimension(1898, 1004));

            // Find and fill the input fields
            driver.findElement(By.cssSelector("label:nth-child(1) > input")).sendKeys("Aveiro");
            driver.findElement(By.cssSelector("label:nth-child(2) > input")).sendKeys("Paris");
            driver.findElement(By.cssSelector("label:nth-child(4) > input")).click();
            driver.findElement(By.cssSelector("label:nth-child(4) > input")).sendKeys("2024-07-11");
            driver.findElement(By.cssSelector("label:nth-child(5) > input")).sendKeys("2");

            // Click on elements
            driver.findElement(By.cssSelector(".travel-list")).click();
            driver.findElement(By.cssSelector(".css-tj5bde-Svg")).click();
            driver.findElement(By.id("react-select-3-option-3")).click();
            driver.findElement(By.cssSelector("button")).click();

            // Wait for elements to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".travel-item:nth-child(1) .price")));

            // Asserting text using JUnit assertions
            assertEquals("Price: $11.87 GBP", priceElement.getText(), "Price text does not match");


    }
}
