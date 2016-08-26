import java.util.ArrayList;

public class Search implements MenuItem<ArrayList<Contact>> {

    private final String name = "Search";
    private final UI ui;

    public Search(UI ui) {
        this.ui = ui;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public ArrayList<Contact> execute(ArrayList<Contact> contacts) {
        return searchContacts(ui.userInput(), contacts);
    }

    public ArrayList<Contact> searchContacts(String nextSearch, ArrayList<Contact> allContacts) {
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Contact contact : allContacts) {
            if (contact.firstName().contains(nextSearch) || contact.lastName().contains(nextSearch) || contact.email().contains(nextSearch)) {
                contacts.add(contact);
            }
        }
        return contacts;
    }
}
