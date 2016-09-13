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
    public void displayMenu(int size) {
        print("\033[31m" + "\033[0;1m" + "Hello, you have " + "\033[33m" + "\033[0;1m" + size + "\033[31m" + "\033[0;1m" + " contacts.");
        print("\033[31m" + "What would you like to do?");
        print("\033[35m" + "-----------------------------");
        for (Operations operation : Operations.values()) {
            print("\033[36m" + operation + "\033[34m" + operation.description());
        }
        print("\033[35m" + "-----------------------------");
    }

    @Override
    public void requestNewContactInformation() {
        print("New first name:");
        print("New last name:");
        print("New telephone number:");
        print("New email:");
    }

    @Override
    public void clearConsole() {
        try {
            writer.write("\033[H\033[2J");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userContinue() {
        print("Type 'ok' to continue or 'end' to end!");
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
