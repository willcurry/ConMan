import java.io.*;
import java.util.ArrayList;

public class ConsoleUI implements UI {
    private final InputStream stream;
    private final BufferedReader inputReader;
    private final Writer writer;

    public ConsoleUI(InputStream stream, Writer writer) {
        this.stream = stream;
        this.writer = writer;
        inputReader = new BufferedReader(new InputStreamReader(stream));
    }

    @Override
    public void displayContactInfo(Contact contact) {
        print(contact.firstName() + " " + contact.lastName());
        print(contact.telephone() + " " + contact.email());
    }

    @Override
    public void displayAllContacts(ArrayList<Contact> contacts) {
        print("Who do want to perform this action on?");
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
    public void displayMenu(int size) {
        print("Hello, you have " + size + " contacts.");
        print("What would you like to do?");
        print("-----------------------------");
        for (Operations operation : Operations.values()) {
            print(operation + operation.description());
        }
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
