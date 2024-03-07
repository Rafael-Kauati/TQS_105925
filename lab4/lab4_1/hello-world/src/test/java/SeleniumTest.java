import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.assertj.core.api.Assertions.assertThat;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;
public class SeleniumTest
{
    final Logger logger = (Logger) getLogger(lookup().lookupClass());

    private WebDriver driver;


    @Test
    void test() {
        // Exercise
        String sutUrl = "https://www.w3schools.com/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        logger.debug("The title of {} is {}", sutUrl, title);

        assertEquals(title, "W3Schools Online Web Tutorials");
    }


    @BeforeEach
    public void StartDriver()
    {
        driver = new FirefoxDriver();
    }

    @AfterEach
    void killDriver() {
        driver.quit();
    }
}
