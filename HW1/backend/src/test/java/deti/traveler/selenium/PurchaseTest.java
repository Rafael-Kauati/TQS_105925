package deti.traveler.selenium;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
@Disabled
class PurchaseTest {

    private WebDriver driver;

    private final String fromCity = "Aveiro, Aveiro", toCity = "Albergaria das Cabras, Arouca", date = "2024-05-20", numOfSeats = "3";


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
            driver.get("http://localhost:5173");

            driver.manage().window().setSize(new Dimension(1898, 1004));

            driver.findElement(By.cssSelector("label:nth-child(1) > input")).sendKeys(fromCity);
            driver.findElement(By.cssSelector("label:nth-child(2) > input")).sendKeys(toCity);
            driver.findElement(By.cssSelector("label:nth-child(4) > input")).click();
            driver.findElement(By.cssSelector("label:nth-child(4) > input")).sendKeys(date);

            driver.findElement(By.cssSelector(".css-w9q2zk-Input2")).click();
            driver.findElement(By.id("react-select-3-option-2")).click();
            driver.findElement(By.cssSelector("button")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement purchaseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".travel-item:nth-child(1) .buttonItem")));

            purchaseButton.click();

            WebElement numSeatsField = driver.findElement(By.id("numSeats"));
            numSeatsField.clear();
            numSeatsField.sendKeys("2");

            // Double click to clear the field
            Actions actions = new Actions(driver);
            actions.doubleClick(numSeatsField).perform();

            // Fill the number of seats field again
            numSeatsField.sendKeys(numOfSeats);

            // Click on a button
            driver.findElement(By.cssSelector(".buttonItem:nth-child(11)")).click();

            WebElement fromCityLabel = driver.findElement(By.cssSelector("tr:last-child > td:nth-child(2)"));
            WebElement toCityLabel = driver.findElement(By.cssSelector("tr:last-child > td:nth-child(3)"));
            WebElement numSeatsSelected = driver.findElement(By.cssSelector("tr:last-child > td:nth-child(6)"));

            assertEquals(fromCity,fromCityLabel.getText(), "The last purchase's from city should be Aveiro, Aveiro" );
            assertEquals(toCity,toCityLabel.getText(), "The last purchase's to city should be Albergaria das Cabras, Arouca" );
            assertEquals(numOfSeats,numSeatsSelected.getText(), "The last purchase's number of seats purchased should be 3" );

        } finally {
            driver.quit();
        }
    }
}
