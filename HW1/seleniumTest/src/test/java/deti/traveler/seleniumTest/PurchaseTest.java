package deti.traveler.seleniumTest;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import io.github.bonigarcia.seljup.SeleniumJupiter;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
@SpringBootTest
@Slf4j
class PurchaseTest {

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
    public void testSelenium() {
        try {
            // Open the URL
            driver.get("http://localhost:5173");

            // Set window size
            driver.manage().window().setSize(new Dimension(1898, 1004));

            // Find and fill the input fields
            driver.findElement(By.cssSelector("label:nth-child(1) > input")).sendKeys("Aveiro");
            driver.findElement(By.cssSelector("label:nth-child(2) > input")).sendKeys("Paris");
            driver.findElement(By.cssSelector("label:nth-child(4) > input")).click();
            driver.findElement(By.cssSelector("label:nth-child(4) > input")).sendKeys("2024-07-11");

            // Click on elements
            driver.findElement(By.cssSelector(".css-w9q2zk-Input2")).click();
            driver.findElement(By.id("react-select-3-option-2")).click();
            driver.findElement(By.cssSelector("button")).click();

            // Wait for elements to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement purchaseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".travel-item:nth-child(1) .buttonItem")));

            // Click on purchase button
            purchaseButton.click();

            // Fill the number of seats field
            WebElement numSeatsField = driver.findElement(By.id("numSeats"));
            numSeatsField.clear();
            numSeatsField.sendKeys("2");

            // Double click to clear the field
            Actions actions = new Actions(driver);
            actions.doubleClick(numSeatsField).perform();

            // Fill the number of seats field again
            numSeatsField.sendKeys("3");

            // Click on a button
            driver.findElement(By.cssSelector(".buttonItem:nth-child(10)")).click();

            WebElement fromCityLabel = driver.findElement(By.cssSelector("tr:last-child > td:nth-child(2)"));
            WebElement toCityLabel = driver.findElement(By.cssSelector("tr:last-child > td:nth-child(3)"));
            WebElement numSeatsSelected = driver.findElement(By.cssSelector("tr:last-child > td:nth-child(6)"));

            assertEquals("Aveiro",fromCityLabel.getText(), "The last purchase's from city should be Aveiro" );
            assertEquals("Paris",toCityLabel.getText(), "The last purchase's to city should be Paris" );
            assertEquals("3",numSeatsSelected.getText(), "The last purchase's number of seats purchased should be 2" );
            // Perform assertions or further actions as needed

        } finally {
            // Close the browser window
            driver.quit();
        }
    }
}
