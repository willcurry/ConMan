import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConManTests {

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

    private Contact will() {
         return new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
    }

    private void buildConMan(String streamInfo) {
        stream = new ByteArrayInputStream(streamInfo.getBytes());
        output = new ByteArrayOutputStream();
        writer = new PrintWriter(output);
        menu = new ConsoleMenu(new ArrayList<>(), stream, writer);
        consoleUI = new ConsoleUI(new ByteArrayInputStream("Will\nBilly".getBytes()), writer);
        conMan = new ConMan(menu, consoleUI, stream);
    }

    @Test
    public void canAddToListOfContacts() {
        buildConMan("Add");
        menu = new ConsoleMenu(conMan.commands(), stream, writer);
        menu.userSelectCommand();
        assertThat(conMan.allContacts().get(0).firstName(), is("Will"));
    }

    @Test
    public void searchingFirstNameReturnsAllContactsWithThatFirstName() {
        buildConMan("Search");
        conMan.allContacts().add(will());
        menu = new ConsoleMenu(conMan.commands(), stream, writer);
        menu.userSelectCommand();
        assertThat(output.toString(), containsString("Will Curry"));
    }

    @Test
    public void deletingAnItemRemovesItFromAllContactsList() {
        buildConMan("Delete");
        conMan.allContacts().add(billy());
        conMan.allContacts().add(will());
        menu = new ConsoleMenu(conMan.commands(), stream, writer);
        menu.userSelectCommand();
        assertThat(conMan.allContacts().size(), is(1));
    }

    @Test
    public void editUpdatesTheContactInAllContactsList() {
        buildConMan("Edit");
        conMan.allContacts().add(will());
        menu = new ConsoleMenu(conMan.commands(), stream, writer);
        menu.userSelectCommand();
        assertThat(conMan.allContacts().get(0).firstName(), is("Billy"));
    }
}
