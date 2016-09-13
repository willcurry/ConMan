import java.util.ArrayList;

public class Delete implements MenuItem<ArrayList<Contact>>{

    private final String name = "Delete";
    private final UI ui;

    public Delete(UI ui) {
        this.ui = ui;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public ArrayList<Contact> execute(ArrayList<Contact> contacts) {
        Contact contact = userPickContact(contacts);
        if (contact != null) contacts.remove(contacts.indexOf(contact));
        return contacts;
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
