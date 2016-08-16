import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

public class ConMan {
    private final UI ui;
    public ArrayList<Contact> allContacts = new ArrayList<>();

    public ConMan(UI ui) {
        this.ui = ui;
    }

    public void add(Contact contact) {
        allContacts.add(contact);
    }

    public void delete(Contact contact) {
        allContacts.remove(allContacts.indexOf(contact));
    }

    public void edit(Contact contact, Contact updatedContact) {
        allContacts.set(allContacts.indexOf(contact), updatedContact);
    }

    private void start() {
        ui.displayMenu(allContacts.size());
        ui.displayAllContacts(searchContacts());
        String userInput = ui.userInput();
        for (Contact contact : searchContacts()) {
            if (userInput.contains(contact.firstName() + userInput.contains(contact.firstName()))) {
                ui.displayContactInfo(contact);
            }
        }
    }

    public ArrayList<Contact> searchContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        String nextSearch = ui.userInput();
        for (Contact contact : allContacts) {
            if (contact.firstName().contains(nextSearch) || contact.lastName().contains(nextSearch) || contact.email().contains(nextSearch)) {
                contacts.add(contact);
            }
        }
        return contacts;
    }

    public static void main(String[] args) {
        Writer writer = new PrintWriter(System.out);
        ConsoleUI consoleUI = new ConsoleUI(System.in, writer);
        ConMan conMan = new ConMan(consoleUI);
        conMan.start();
    }
}
