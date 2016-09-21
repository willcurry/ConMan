import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;

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

}
