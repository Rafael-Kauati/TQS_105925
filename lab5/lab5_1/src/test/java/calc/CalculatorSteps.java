package calc;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.example.Calculator;
import org.slf4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class CalculatorSteps {

    static final Logger log = getLogger(lookup().lookupClass());

    private Calculator calc;

    @Given("Calculator Test Boostraping")
    public void startup(){
        calc = new Calculator();
    }



    @Then("the result is {double}")
    public  void result(double expected){
        assertEquals(expected,calc.value());
    }

    @When("I multiply {int} by {int}")
    public void iMultiplyBy(int arg0, int arg1) {
        calc.push(arg0);
        calc.push(arg1);
        calc.push("*");
    }



    @When("I divide {int} by {int}")
    public void iDivideBy(int arg0, int arg1) {
        calc.push(arg0);
        calc.push(arg1);
        calc.push("/");
    }

    @When("I subtract {int} to {int}")
    public void iSubtractTo(int arg0, int arg1) {
        calc.push(arg0);
        calc.push(arg1);
        calc.push("-");
    }

    @When("I add {int} and {int}")
    public void iAddAnd(int arg0, int arg1) {
        calc.push(arg0);
        calc.push(arg1);
        calc.push("+");
    }
}