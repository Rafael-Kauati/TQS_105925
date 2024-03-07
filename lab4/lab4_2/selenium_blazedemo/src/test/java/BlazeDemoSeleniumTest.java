import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
public class BlazeDemoSeleniumTest
{
    private WebDriver driver;

    @BeforeEach
    public void StartDriver(FirefoxDriver ddriver) {
        driver = ddriver;
    }

    @Test
    public void testSeleniumIDEConversion() {
        // Abre o site
        driver.get("https://blazedemo.com");

        // Configura o tamanho da janela
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1898, 1124));

        // Seleciona o aeroporto de origem
        Select fromPortDropdown = new Select(driver.findElement(By.name("fromPort")));
        fromPortDropdown.selectByVisibleText("São Paolo");

        // Seleciona o aeroporto de destino
        Select toPortDropdown = new Select(driver.findElement(By.name("toPort")));
        toPortDropdown.selectByVisibleText("Dublin");

        // Clica no botão "Find Flights"
        driver.findElement(By.cssSelector(".btn-primary")).click();

        // Clica no botão "Choose This Flight" para o quarto voo
        driver.findElement(By.cssSelector("tr:nth-child(4) .btn")).click();

        // Preenche informações do passageiro
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("TK");
        driver.findElement(By.id("address")).sendKeys("address");
        driver.findElement(By.id("city")).sendKeys("Xique");
        driver.findElement(By.cssSelector(".control-group:nth-child(4)")).click();
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("Xique-xique");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys("Bahia");
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys("12345");

        // Seleciona o tipo de cartão de crédito
        Select cardTypeDropdown = new Select(driver.findElement(By.id("cardType")));
        cardTypeDropdown.selectByVisibleText("Diner's Club");

        // Preenche informações do cartão de crédito
        driver.findElement(By.id("creditCardMonth")).click();
        driver.findElement(By.id("creditCardMonth")).sendKeys("09");
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).sendKeys("2019");
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys("Funny Valentine");

        // Clica no botão "Purchase Flight"
        driver.findElement(By.cssSelector(".btn-primary")).click();

        // Verifica o texto de agradecimento
        String thankYouText = driver.findElement(By.cssSelector("h1")).getText();
        assertEquals("Thank you for your purchase today!", thankYouText);

        // Verifica o estado da transação
        String transactionStatus = driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText();
        assertEquals("PendingCapture", transactionStatus);
    }

    @AfterEach
    public void KillDriver() {
        driver.quit();
    }
}
