import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleSearchTests {
    @Test
    public void searchingFirstNameReturnsContactWithThatFirstName() {
        InputStream stream = new ByteArrayInputStream("Will".getBytes());
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        Writer writer = new PrintWriter(System.out);
        ConsoleSearch consoleSearch = new ConsoleSearch(stream, writer);
        for (Contact will : consoleSearch.searchContacts()) {
            assertThat(will.firstName(), is("Will"));
        }
    }

    @Test
    public void searchingFirstNameReturnsAllContactsWithThatFirstName() {
        InputStream stream = new ByteArrayInputStream("Will".getBytes());
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        Contact contact2 = new Contact("Will", "NotCurry", "07555555556", "will@emailsite.co.uk");
        Contact contact3 = new Contact("Billy", "Smith", "07555555556", "billy@emailsite.com");
        Writer writer = new PrintWriter(System.out);
        ConsoleSearch consoleSearch = new ConsoleSearch(stream, writer);
        for (Contact will : consoleSearch.searchContacts()) {
            assertThat(will.firstName(), is("Will"));
        }
    }
}
