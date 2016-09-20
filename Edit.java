import java.util.ArrayList;

public class Edit implements Command{

    private final String name = "Edit";
    private final UI ui;
    private final ArrayList<Contact> contacts;

    public Edit(UI ui, ArrayList<Contact> contacts) {
        this.ui = ui;
        this.contacts = contacts;
    }

    @Override
    public void execute() {
        Contact oldContact = userPickContact(contacts);
        Contact updatedContact = updateContact();
        contacts.set(contacts.indexOf(oldContact), updatedContact);
    }

    @Override
    public String name() {
        return name;
    }

    private Contact updateContact() {
        ui.requestNewContactInformation();
        return new Contact(ui.userInput(), ui.userInput(), ui.userInput(), ui.userInput());
    }

    private Contact userPickContact(ArrayList<Contact> allContacts) {
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
