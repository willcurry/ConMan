import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ConManTests {

    @Before
    public void setUp() {
    }

    @Test
    public void listOfContactsHoldsAddedContact() {
        InputStream stream = new ByteArrayInputStream("Will\nWill".getBytes());
        Writer writer = new PrintWriter(System.out);
        ConsoleUI consoleUI = new ConsoleUI(stream, writer);
        ConMan conMan = new ConMan(consoleUI);
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        conMan.add(contact);
        for (Contact will : conMan.allContacts) {
            assertThat(will.firstName(), is("Will"));
        }
    }

    @Test
    public void searchingFirstNameReturnsAllContactsWithThatFirstName() {
        InputStream stream = new ByteArrayInputStream("Will\nWill".getBytes());
        Writer writer = new PrintWriter(System.out);
        ConsoleUI consoleUI = new ConsoleUI(stream, writer);
        ConMan conMan = new ConMan(consoleUI);
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        Contact contact2 = new Contact("Will", "NotCurry", "07555555556", "will@emailsite.co.uk");
        Contact contact3 = new Contact("Billy", "Smith", "07555555556", "billy@emailsite.com");
        conMan.add(contact);
        conMan.add(contact2);
        conMan.add(contact3);
        for (Contact will : conMan.searchContacts()) {
            assertThat(will.firstName(), is("Will"));
        }
    }

    @Test
    public void whenPerfromOperationIsCalledWithDeleteItDeletesTheContactChosen() {
        InputStream stream = new ByteArrayInputStream("Will\nWill".getBytes());
        Writer writer = new PrintWriter(System.out);
        ConsoleUI consoleUI = new ConsoleUI(stream, writer);
        ConMan conMan = new ConMan(consoleUI);
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        Contact contact2 = new Contact("Billy", "Smith", "07555555556", "billy@emailsite.com");
        conMan.add(contact);
        conMan.add(contact2);
        conMan.performOperation("delete");
        assertThat(conMan.allContacts.size(), is(1));
    }

    @Test
    public void userCanSelectAContactFromTheListOfContacts() {
        InputStream stream = new ByteArrayInputStream("Will".getBytes());
        Writer writer = new PrintWriter(System.out);
        ConsoleUI consoleUI = new ConsoleUI(stream, writer);
        ConMan conMan = new ConMan(consoleUI);
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        conMan.add(contact);
        assertThat(conMan.userPickContact(), is(contact));
    }

    @Test
    public void whenPerformOperationIsCalledWithEditItUpdatesTheContactsInformation() {
        InputStream stream = new ByteArrayInputStream("Billy\nWill\nCurry\ntelephone\nemail".getBytes());
        Writer writer = new PrintWriter(System.out);
        ConsoleUI consoleUI = new ConsoleUI(stream, writer);
        ConMan conMan = new ConMan(consoleUI);
        Contact contact = new Contact("Billy", "Smith", "07555555556", "billy@emailsite.com");
        conMan.add(contact);
        conMan.performOperation("edit");
        assertThat(conMan.allContacts.get(0).firstName(), is("Will"));
        assertThat(conMan.allContacts.get(0).lastName(), is("Curry"));
        assertThat(conMan.allContacts.get(0).telephone(), is("telephone"));
        assertThat(conMan.allContacts.get(0).email(), is("email"));
    }

    @Test
    public void whenPerformOperationIsCalledWithAddTheUserCanAddACustomContact() {
        InputStream stream = new ByteArrayInputStream("Will\nCurry\ntelephone\nemail".getBytes());
        Writer writer = new PrintWriter(System.out);
        ConsoleUI consoleUI = new ConsoleUI(stream, writer);
        ConMan conMan = new ConMan(consoleUI);
        conMan.performOperation("add");
        assertThat(conMan.allContacts.get(0).firstName(), is("Will"));
        assertThat(conMan.allContacts.get(0).lastName(), is("Curry"));
        assertThat(conMan.allContacts.get(0).telephone(), is("telephone"));
        assertThat(conMan.allContacts.get(0).email(), is("email"));
    }
}
