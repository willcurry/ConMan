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
    private ConsoleMenu<ArrayList<Contact>> menu;
    private ConMan conMan;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        stream = new ByteArrayInputStream("Will".getBytes());
        output = new ByteArrayOutputStream();
        Writer writer = new PrintWriter(output);
        consoleUI = new ConsoleUI(stream, writer);
        menu = new ConsoleMenu<ArrayList<Contact>>(menuItems(), stream, writer);
        conMan = new ConMan(menu, consoleUI);
    }

    private ArrayList<MenuItem> menuItems() {
        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new Search(consoleUI));
        items.add(new Add(consoleUI));
        return items;
    }

    private Contact billy() {
         return new Contact("Billy", "Smith", "07555555556", "billy@emailsite.com");
    }

    private Contact will() {
         return new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
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
        conMan.allContacts.add(contact);
        consoleUI.displayAllContacts(conMan.allContacts);
        assertThat(output.toString(), containsString("Smith"));
    }
}
