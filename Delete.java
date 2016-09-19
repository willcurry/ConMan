import java.util.ArrayList;

public class Delete implements Command{

    private final String name = "Delete";
    private final UI ui;
    private final ArrayList<Contact> contacts;

    public Delete(UI ui, ArrayList<Contact> contacts) {
        this.ui = ui;
        this.contacts = contacts;
    }

    @Override
    public void execute() {
        contacts.remove(contacts.indexOf(userPickContact(contacts)));
    }

    @Override
    public String name() {
        return name;
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
