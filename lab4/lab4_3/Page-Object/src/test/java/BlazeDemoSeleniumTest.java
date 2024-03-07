import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
public class BlazeDemoSeleniumTest {

    private WebDriver driver;
    private BlazeDemoPage blazeDemoPage;



    @Test
    public void testBlazeDemoPage() {
        blazeDemoPage.selectFromPort("São Paolo");
        blazeDemoPage.selectToPort("Dublin");
        blazeDemoPage.clickFindFlights();
        blazeDemoPage.clickChooseFlight();

        blazeDemoPage.inputNameField.sendKeys("TK");
        blazeDemoPage.addressField.sendKeys("address");
        blazeDemoPage.cityField.sendKeys("Xique");
        blazeDemoPage.cityField.click();
        blazeDemoPage.cityField.sendKeys("Xique-xique");
        blazeDemoPage.stateField.click();
        blazeDemoPage.stateField.sendKeys("Bahia");
        blazeDemoPage.zipCodeField.click();
        blazeDemoPage.zipCodeField.sendKeys("12345");

        Select cardTypeDropdown = new Select(blazeDemoPage.cardTypeDropdown);
        cardTypeDropdown.selectByVisibleText("Diner's Club");

        blazeDemoPage.creditCardMonthField.click();
        blazeDemoPage.creditCardMonthField.sendKeys("09");
        blazeDemoPage.creditCardYearField.click();
        blazeDemoPage.creditCardYearField.sendKeys("2019");
        blazeDemoPage.nameOnCardField.click();
        blazeDemoPage.nameOnCardField.sendKeys("Funny Valentine");

        blazeDemoPage.purchaseFlightButton.click();

        assertEquals("Thank you for your purchase today!", blazeDemoPage.getThankYouHeaderText());
        assertEquals("PendingCapture", blazeDemoPage.getTransactionStatusText());
    }

    @BeforeEach
    public void StartDriver(FirefoxDriver ddriver) {
        driver = ddriver;
        blazeDemoPage = new BlazeDemoPage(driver);
        driver.get("https://blazedemo.com");
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1898, 1124));
    }

    @AfterEach
    public void KillDriver() {
        driver.quit();
    }
}
