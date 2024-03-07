import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BlazeDemoPage {

    private WebDriver driver;

    @FindBy(name = "fromPort")
    private WebElement fromPortDropdown;

    @FindBy(name = "toPort")
    private WebElement toPortDropdown;

    @FindBy(css = ".btn-primary")
    private WebElement findFlightsButton;

    @FindBy(css = "tr:nth-child(4) .btn")
    private WebElement chooseFlightButton;

    @FindBy(id = "inputName")
    WebElement inputNameField;

    @FindBy(id = "address")
    WebElement addressField;

    @FindBy(id = "city")
    WebElement cityField;

    @FindBy(id = "state")
    WebElement stateField;

    @FindBy(id = "zipCode")
    WebElement zipCodeField;

    @FindBy(id = "cardType")
    WebElement cardTypeDropdown;

    @FindBy(id = "creditCardMonth")
    WebElement creditCardMonthField;

    @FindBy(id = "creditCardYear")
    WebElement creditCardYearField;

    @FindBy(id = "nameOnCard")
    WebElement nameOnCardField;

    @FindBy(css = ".btn-primary")
    WebElement purchaseFlightButton;

    @FindBy(css = "h1")
    private WebElement thankYouHeader;

    @FindBy(css = "tr:nth-child(2) > td:nth-child(2)")
    private WebElement transactionStatus;

    public BlazeDemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectFromPort(String option) {
        // Select the airport of origin
        // You might want to add additional logic here, e.g., handling wait conditions
        Select select = new Select(fromPortDropdown);
        select.selectByVisibleText(option);
    }

    public void selectToPort(String option) {
        // Select the airport of destination
        // You might want to add additional logic here, e.g., handling wait conditions
        Select select = new Select(toPortDropdown);
        select.selectByVisibleText(option);
    }

    public void clickFindFlights() {
        // Click the "Find Flights" button
        findFlightsButton.click();
    }

    public void clickChooseFlight() {
        // Click the "Choose This Flight" button
        chooseFlightButton.click();
    }


    public String getThankYouHeaderText() {
        // Get the text from the "Thank You" header
        return thankYouHeader.getText();
    }

    public String getTransactionStatusText() {
        // Get the text from the transaction status element
        return transactionStatus.getText();
    }
}
