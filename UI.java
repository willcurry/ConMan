import java.util.ArrayList;

public interface UI {
    void displayContactInfo(Contact contact);
    void displayAllContacts(ArrayList<Contact> contacts);
    String userInput();
    void displayMenu(int size);
}
