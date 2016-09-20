import java.util.ArrayList;

public class Search implements Command {

    private final String name = "Search";
    private final UI ui;
    private final ArrayList<Contact> contacts;

    public Search(UI ui, ArrayList<Contact> contacts) {
        this.ui = ui;
        this.contacts = contacts;
    }

    @Override
    public void execute() {
        ui.displayAllContacts(searchContacts(ui.userInput(), contacts));
    }

    @Override
    public String name() {
        return name;
    }

    private ArrayList<Contact> searchContacts(String nextSearch, ArrayList<Contact> allContacts) {
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Contact contact : allContacts) {
            if (isMatch(contact, nextSearch)) {
                contacts.add(contact);
            }
        }
        return contacts;
    }

    private boolean isMatch(Contact contact, String nextSearch) {
        return contact.firstName().contains(nextSearch) ||
                contact.lastName().contains(nextSearch) ||
                contact.email().contains(nextSearch);
    }
}
