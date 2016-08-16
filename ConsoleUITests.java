import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class ConsoleUITests {
    private ConMan conMan;
    private InputStream stream;
    private Writer writer;
    private ConsoleUI consoleUI;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        stream = new ByteArrayInputStream("Will".getBytes());
        output = new ByteArrayOutputStream();
        writer = new PrintWriter(output);
        consoleUI = new ConsoleUI(stream, writer);
        conMan = new ConMan(consoleUI);
    }

    @Test
    public void displaysTheCorrectContactInformation() {
        Contact contact = new Contact("Bob", "Smith", "07333444333", "smith@ConMan.com");
        consoleUI.displayContactInfo(contact);
        assertThat(output.toString(), containsString("Smith"));
        assertThat(output.toString(), containsString("Bob"));
        assertThat(output.toString(), containsString("07333444333"));
        assertThat(output.toString(), containsString("smith@ConMan.com"));
    }

    @Test
    public void displayAllContactsDisplaysEverySingleContactsFirstAndLastName() {
        Contact contact = new Contact("Bob", "Smith", "07333444333", "smith@ConMan.com");
        Contact contact2 = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        conMan.add(contact);
        conMan.add(contact2);
        consoleUI.displayAllContacts(conMan.allContacts);
        assertThat(output.toString(), containsString("Smith"));
        assertThat(output.toString(), containsString("Curry"));
    }
}
