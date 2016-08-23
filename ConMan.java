import java.io.IOException;
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

    public ArrayList<Contact> searchContacts() {
        String nextSearch = ui.userInput();
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Contact contact : allContacts) {
            if (contact.firstName().contains(nextSearch) || contact.lastName().contains(nextSearch) || contact.email().contains(nextSearch)) {
                contacts.add(contact);
            }
        }
        return contacts;
    }

    private void start() {
        ui.clearConsole();
        ui.displayMenu(allContacts.size());
        String userInput = ui.userInput();
        for (Operations operation : Operations.values()) {
            if (userInput.equals(operation.toString().toLowerCase()) || userInput.equals(operation.toString().toUpperCase())) {
                performOperation(operation.toString().toLowerCase());
            }
        }
    }

    public void shouldContinue() {
        ui.userContinue();
        if (ui.userInput().equals("ok")) {
            start();
        }
    }

    public void performOperation(String operation) {
        if (operation.equals("search")) {
            ui.clearConsole();
            for (Contact contact : searchContacts()) {
                ui.displayContactInfo(contact);
            }
            shouldContinue();
        } else if (operation.equals("delete")) {
            ui.clearConsole();
            Contact contact = userPickContact();
            delete(contact);
            shouldContinue();
        } else if (operation.equals("add")) {
            ui.clearConsole();
            Contact contact = userEditedContact();
            add(contact);
            shouldContinue();
        } else if (operation.equals("edit")) {
            ui.clearConsole();
            Contact contact = userPickContact();
            Contact contact2 = userEditedContact();
            edit(contact, contact2);
            shouldContinue();
        }
    }

    public boolean isConManEnded() {
        return ui.userInput().equals("end");
    }

    private Contact userEditedContact() {
        ui.requestNewContactInformation();
        return new Contact(ui.userInput(), ui.userInput(), ui.userInput(), ui.userInput());
    }

    public Contact userPickContact() {
        ui.displayAllContacts(allContacts);
        String search = ui.userInput();
        for (Contact contact : allContacts) {
            if (search.contains(contact.firstName()) || search.contains(contact.firstName())) {
                return contact;
            }
        }
        return userPickContact();
    }

    public static void main(String[] args) {
        Writer writer = new PrintWriter(System.out);
        ConsoleUI consoleUI = new ConsoleUI(System.in, writer);
        ConMan conMan = new ConMan(consoleUI);
        conMan.start();
    }
}
