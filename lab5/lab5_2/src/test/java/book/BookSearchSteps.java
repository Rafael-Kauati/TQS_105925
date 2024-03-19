package book;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Book;
import org.example.Library;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookSearchSteps
{
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    Book b = null;

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDate iso8601Date(String year, String month, String day){
        int yearValue = Integer.parseInt(year);
        int monthValue = Integer.parseInt(month);
        int dayValue = Integer.parseInt(day);

        return LocalDate.of(yearValue, monthValue, dayValue);
    }

    /*
    *
    * 1st scenario
    *
     */


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

    /*
    *
    * 2nd scenario
    *
     */

    @Given("a title {string}, written by {string}, published in {iso8601Date}")
    public void aTitleYouZitusWrittenByShōgoKinugasaPublishedIn(String arg0, String arg1, LocalDate arg2) {
        this.result.clear();

        library.addBook(
                new Book(
                arg0, arg1, arg2.atStartOfDay()
                )
        );

    }


    @When("the customer searches for a book titled as {string}")
    public void theCustomerSearchesForABookTitledAsYouZitus(String arg0) {
        this.result = library.findBooksByTitle(arg0);
    }

    @Then("the customer should see the following book details:")
    public void theCustomerShouldSeeTheFollowingBookDetails(DataTable dataTable) {
        List<Map<String, String>> expectedBookDetails = dataTable.asMaps(String.class, String.class);

        final Book found = result.get(0);
        final String actualTitle = found.getTitle();
        final String actualAuthor = found.getAuthor();
        final Date actualPublishedDate = found.getPublished();

        Map<String, String> expectedDetails = expectedBookDetails.get(0);
        final String expectedTitle = expectedDetails.get("Title");
        final String expectedAuthor = expectedDetails.get("Author");
        final String expectedPublishedDate = expectedDetails.get("Published Date");

        // Compare the actual and expected book details
        assertEquals(expectedTitle, actualTitle);
        assertEquals(expectedAuthor, actualAuthor);
        // Format actualPublishedDate to match the expected format and timezone
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC")); // Set the timezone to UTC
        final String formattedActualPublishedDate = sdf.format(actualPublishedDate);

        assertEquals(expectedPublishedDate, formattedActualPublishedDate);;
    }
}
