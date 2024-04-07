package deti.traveler.seleniumTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/deti/traveler/seleniumTest", glue = "deti.traveler.seleniumTest")
public class PurchaseTestRunner {
}
