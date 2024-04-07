package deti.traveler.seleniumTest;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class PurchaseSteps {
    private WebDriver driver;

    @Given("User is on the home page")
    public void userIsOnHomePage() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:5173");
    }

    @When("User selects departure city {string} and destination city {string} for the journey date {date}")
    public void userSelectsCitiesAndDate(String departure, String destination, String date) {
        // Fill the input fields
        driver.findElement(By.cssSelector("label:nth-child(1) > input")).sendKeys(departure);
        driver.findElement(By.cssSelector("label:nth-child(2) > input")).sendKeys(destination);
        driver.findElement(By.cssSelector("label:nth-child(4) > input")).click();
        driver.findElement(By.cssSelector("label:nth-child(4) > input")).sendKeys(date);
        // Other necessary actions to select cities and date
    }

    @And("User selects {int} seats")
    public void userSelectsSeats(int numSeats) {
        WebElement numSeatsField = driver.findElement(By.id("numSeats"));
        numSeatsField.clear();
        numSeatsField.sendKeys(Integer.toString(numSeats));
    }

    @And("User completes the purchase")
    public void userCompletesPurchase() {
        // Perform actions to complete the purchase
    }

    @Then("User should see the purchase details for the journey")
    public void userShouldSeePurchaseDetails() {
        // Wait for purchase details to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement fromCityLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tr:last-child > td:nth-child(2)")));
        WebElement toCityLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tr:last-child > td:nth-child(3)")));
        WebElement numSeatsSelected = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tr:last-child > td:nth-child(6)")));

        // Assert purchase details
        assertEquals("The last purchase's from city should be Aveiro","Aveiro", fromCityLabel.getText());
        assertEquals("The last purchase's to city should be Paris","Paris", toCityLabel.getText());
        assertEquals("The last purchase's number of seats purchased should be 2","3", numSeatsSelected.getText() );
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }


}
