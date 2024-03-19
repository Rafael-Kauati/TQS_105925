package book;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Book;
import org.example.Library;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookSearchSteps
{
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDate iso8601Date(String year, String month, String day){
        int yearValue = Integer.parseInt(year);
        int monthValue = Integer.parseInt(month);
        int dayValue = Integer.parseInt(day);

        return LocalDate.of(yearValue, monthValue, dayValue);
    }

    @And("Book {int} should have the title {string}")
    public void verifyBookTitle(int index, String name) {
        assertEquals(result.get(index-1).getTitle(), name );
    }

    @Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
    public void aBookWithTheTitleTheLawsOfPowerWrittenByRobertGreenePublishedIn(String arg0, String arg1, LocalDate arg2) {
        //System.out.println("\n\n || "+arg0+" || "+arg1+" || "+ arg2+"\n\n");
        final Book book = new Book(arg0, arg1, arg2.atStartOfDay());
        library.addBook(book);
    }


    @When("the customer searches for books published between {int} and {int}")
    public void theCustomerSearchesForBooksPublishedBetweenAnd(int arg0, int arg1) {
        LocalDateTime dt = LocalDateTime.of(arg0,1,1,0,0);
        LocalDateTime dt2 = LocalDateTime.of(arg1,1,1,0,0);
        Date date1 = Date.from(dt.toInstant(ZoneOffset.UTC));
        Date date2 = Date.from(dt2.toInstant(ZoneOffset.UTC));
        //System.out.println("\n\n || books found : "+library.findBooks(date1,date2));
        this.result = library.findBooks(date1, date2);
    }

    @Then("{int} books should have been found")
    public void booksShouldHaveBeenFound(int arg0) {
        //System.out.println("\n\n || "+result.size()+" || "+arg0+"\n\n");

        assertEquals( arg0, result.size());
    }

}
