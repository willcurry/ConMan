import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ContactTests {
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
        assertThat(ConsoleSearch.allContacts.get(0).lastName(), is("Curry"));
    }

    @Test
    public void whenContactIsDeletedItIsRemovedFromAllContactsList() {
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        Contact contact1 = new Contact("Will", "Smith", "07555555555", "will@emailsite.com");
        contact.delete(contact);
        assertThat(ConsoleSearch.allContacts.get(0).lastName(), is("Smith"));
    }

    @Test
    public void afterEditingContactFirstNameItIsUpdated() {
        Contact contact = new Contact("Will", "Curry", "07555555555", "will@emailsite.com");
        Contact contact1 = new Contact("Bob", "Curry", "07555555555", "will@emailsite.com");
        contact.edit(contact, contact1);
        assertThat(ConsoleSearch.allContacts.get(0).firstName(), is("Bob"));
    }
}
