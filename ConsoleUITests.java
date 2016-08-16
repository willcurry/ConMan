import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleUITests {
    private ConMan conMan;
    private InputStream stream;
    private Writer writer;
    private ConsoleUI consoleUI;

    @Before
    public void setUp() {
        InputStream stream = new ByteArrayInputStream("Will".getBytes());
        Writer writer = new PrintWriter(System.out);
        ConsoleUI consoleUI = new ConsoleUI(stream, writer);
        ConMan conMan = new ConMan(consoleUI);
    }

    @Test
    public void searchingFirstNameReturnsContactWithThatFirstName() {
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        for (Contact will : conMan.allContacts) {
            assertThat(will.firstName(), is("Will"));
        }
    }

    @Test
    public void searchingFirstNameReturnsAllContactsWithThatFirstName() {
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        Contact contact2 = new Contact("Will", "NotCurry", "07555555556", "will@emailsite.co.uk");
        Contact contact3 = new Contact("Billy", "Smith", "07555555556", "billy@emailsite.com");
        for (Contact will : conMan.searchContacts()) {
            assertThat(will.firstName(), is("Will"));
        }
    }
}
