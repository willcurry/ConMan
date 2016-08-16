import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ContactTests {
    private ConMan conMan;
    private InputStream stream;
    private Writer writer;
    private ConsoleUI consoleUI;

    @Before
    public void setUp() throws Exception {
        InputStream stream = new ByteArrayInputStream("Will".getBytes());
        Writer writer = new PrintWriter(System.out);
        ConsoleUI consoleUI = new ConsoleUI(stream, writer);
        conMan = new ConMan(consoleUI);
    }

    @Test
    public void contactHasCorrectFirstNameAfterCreation() {
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        assertThat(contact.firstName(), is("Will"));
    }

    @Test
    public void contactHasCorrectLastNameAfterCreation() {
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        assertThat(contact.lastName(), is("Curry"));
    }

    @Test
    public void contactHasCorrectTelephoneAfterCreation() {
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        assertThat(contact.telephone(), is("07555555555"));
    }

    @Test
    public void contactHasCorrectEmailAfterCreation() {
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        assertThat(contact.email(), is("will@emailsite.com"));
    }

    @Test
    public void contactIsAddedToPublicListOfContactsAfterCreation() {
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        conMan.add(contact);
        assertThat(conMan.allContacts.get(0).lastName(), is("Curry"));
    }

    @Test
    public void whenContactIsDeletedItIsRemovedFromAllContactsList() {
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        Contact contact1 = new Contact("Will", "Smith", "07555555555", "will@emailsite.com");
        conMan.add(contact);
        conMan.add(contact1);
        conMan.delete(contact);
        assertThat(conMan.allContacts.get(0).lastName(), is("Smith"));
    }

    @Test
    public void afterEditingContactFirstNameItIsUpdated() {
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        Contact contact1 = new Contact("Bob", "Curry", "07555555555", "will@emailsite.com");
        conMan.add(contact);
        conMan.add(contact1);
        conMan.edit(contact, contact1);
        assertThat(conMan.allContacts.get(0).firstName(), is("Bob"));
    }
}
