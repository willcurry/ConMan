import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ConManTests {

    private UI consoleUI;
    private ByteArrayInputStream stream;
    private ConsoleMenu<ArrayList<Contact>> menu;
    private ConMan conMan;
    private Writer writer;

    @Before
    public void setUp() {
        stream = new ByteArrayInputStream("".getBytes());
        writer = new PrintWriter(System.out);
        consoleUI = new ConsoleUI(stream, writer);
        menu = new ConsoleMenu<ArrayList<Contact>>(menuItems(), stream, writer);
        conMan = new ConMan(menu, consoleUI);
    }

    private ArrayList<MenuItem> menuItems() {
        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new Search(consoleUI));
        items.add(new Add(consoleUI));
        items.add(new Delete(consoleUI));
        items.add(new Edit(consoleUI));
        return items;
    }

    private Contact billy() {
         return new Contact("Billy", "Smith", "07555555556", "billy@emailsite.com");
    }

    private Contact will() {
         return new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
    }

    @Test
    public void canAddToListOfContacts() {
        stream = new ByteArrayInputStream("Add\nWill\nCurry\nTele\nEmail".getBytes());
        writer = new PrintWriter(System.out);
        consoleUI = new ConsoleUI(stream, writer);
        menu = new ConsoleMenu<ArrayList<Contact>>(menuItems(), stream, writer);
        conMan = new ConMan(menu, consoleUI);
        conMan.userSelectItem();
        assertThat(conMan.allContacts.get(0).firstName(), is("Will"));
    }

    @Test
    public void searchingFirstNameReturnsAllContactsWithThatFirstName() {
        stream = new ByteArrayInputStream("Search\nWill\nWill\nWill\nWill".getBytes());
        writer = new PrintWriter(System.out);
        consoleUI = new ConsoleUI(stream, writer);
        menu = new ConsoleMenu<ArrayList<Contact>>(menuItems(), stream, writer);
        conMan = new ConMan(menu, consoleUI);
        Contact contact = will();
        conMan.allContacts.add(contact);
        conMan.userSelectItem();
        System.out.println(conMan.userSelectItem());
        for (Contact will : conMan.userSelectItem()) {
            assertThat(will.firstName(), is("Will"));
        }
    }

    @Test
    public void deletingAnItemRemovesItFromAllContactsList() {
        stream = new ByteArrayInputStream("Delete\nBilly".getBytes());
        writer = new PrintWriter(System.out);
        consoleUI = new ConsoleUI(stream, writer);
        menu = new ConsoleMenu<ArrayList<Contact>>(menuItems(), stream, writer);
        conMan = new ConMan(menu, consoleUI);
        conMan.allContacts.add(billy());
        conMan.allContacts.add(will());
        conMan.userSelectItem();
        assertThat(conMan.allContacts.size(), is(1));
    }

    @Test
    public void editUpdatesTheContactInAllContactsList() {
        stream = new ByteArrayInputStream("Edit\nBilly\nWill\nCurry\ntelephone\nemail".getBytes());
        writer = new PrintWriter(System.out);
        consoleUI = new ConsoleUI(stream, writer);
        menu = new ConsoleMenu<ArrayList<Contact>>(menuItems(), stream, writer);
        conMan = new ConMan(menu, consoleUI);
        conMan.allContacts.add(billy());
        conMan.userSelectItem();
        assertThat(conMan.allContacts.get(0).firstName(), is("Will"));
        assertThat(conMan.allContacts.get(0).lastName(), is("Curry"));
        assertThat(conMan.allContacts.get(0).telephone(), is("telephone"));
        assertThat(conMan.allContacts.get(0).email(), is("email"));
    }
}
