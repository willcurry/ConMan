import java.io.*;
import java.util.ArrayList;

public class ConsoleUI implements UI {
    private final BufferedReader inputReader;
    private final Writer writer;

    public ConsoleUI(InputStream stream, Writer writer) {
        this.writer = writer;
        inputReader = new BufferedReader(new InputStreamReader(stream));
    }

    @Override
    public void displayContactInfo(Contact contact) {
        print("------------------");
        print(contact.firstName() + " " + contact.lastName());
        print(contact.telephone() + " " + contact.email());
        print("------------------");
    }

    @Override
    public void displayAllContacts(ArrayList<Contact> contacts) {
        print("Who would you like to perform this action on?");
        for (Contact contact : contacts) {
            print(contact.firstName() + " " + contact.lastName());
        }
    }

    @Override
    public String userInput() {
        try {
            return inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void requestNewContactInformation() {
        print("New first name:");
        print("New last name:");
        print("New telephone number:");
        print("New email:");
    }

    public void print(String text) {
        try {
            writer.write(text + "\n");
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
