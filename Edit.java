import java.util.ArrayList;

public class Edit implements MenuItem<ArrayList<Contact>>{

    private final String name = "Edit";
    private final UI ui;

    public Edit(UI ui) {
        this.ui = ui;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public ArrayList<Contact> execute(ArrayList<Contact> contacts) {
        Contact contact = userPickContact(contacts);
        Contact updatedContact = updateContact();
        contacts.set(contacts.indexOf(contact), updatedContact);
        return contacts;
    }

    private Contact updateContact() {
        ui.requestNewContactInformation();
        return new Contact(ui.userInput(), ui.userInput(), ui.userInput(), ui.userInput());
    }

    public Contact userPickContact(ArrayList<Contact> allContacts) {
        ui.displayAllContacts(allContacts);
        String search = ui.userInput();
        for (Contact contact : allContacts) {
            if (search.contains(contact.firstName()) || search.contains(contact.firstName())) {
                return contact;
            }
        }
        return userPickContact(allContacts);
    }
}
