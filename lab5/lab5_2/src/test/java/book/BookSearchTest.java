package book;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/book/bookSearch.feature",
        glue = "src/test/java/book/BookSearchSteps.java"
)
public class BookSearchTest {
}