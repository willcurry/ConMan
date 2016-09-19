import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class ConsoleUITests {
    private UI consoleUI;
    private ByteArrayInputStream stream;
    private ConsoleMenu menu;
    private ConMan conMan;
    private Writer writer;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        buildConMan("");
    }

    private Contact billy() {
        return new Contact("Billy", "Smith", "07555555556", "billy@emailsite.com");
    }

    private void buildConMan(String streamInfo) {
        stream = new ByteArrayInputStream(streamInfo.getBytes());
        output = new ByteArrayOutputStream();
        writer = new PrintWriter(output);
        menu = new ConsoleMenu(new ArrayList<>(), stream, writer);
        consoleUI = new ConsoleUI(new ByteArrayInputStream("Will\nBilly".getBytes()), writer);
        conMan = new ConMan(menu, consoleUI);
    }

    @Test
    public void displaysTheCorrectContactInformation() {
        consoleUI.displayContactInfo(billy());
        assertThat(output.toString(), containsString("Smith"));
        assertThat(output.toString(), containsString("Billy"));
        assertThat(output.toString(), containsString("billy@emailsite.com"));
        assertThat(output.toString(), containsString("07555555556"));
    }

    @Test
    public void displayAllContactsDisplaysEverySingleContactsFirstAndLastName() {
        conMan.allContacts().add(billy());
        consoleUI.displayAllContacts(conMan.allContacts());
        assertThat(output.toString(), containsString("Smith"));
    }
}
