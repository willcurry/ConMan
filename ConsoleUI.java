import java.io.*;
import java.util.ArrayList;

public class ConsoleUI implements UI {
    private final InputStream stream;
    private final BufferedReader inputReader;
    private final Writer writer;

    @Override
    public void displayContactInfo(Contact contact) {
        print(contact.firstName() + " " + contact.lastName() + "\n");
        print(contact.telephone() + " " + contact.email() + "\n");
    }

    @Override
    public void displayAllContacts(ArrayList<Contact> contacts) {
        for (Contact contact : contacts) {
            print(contact.firstName() + " " + contact.lastName() + "\n");
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
        print("Hello, you have " + size + " contacts. \n");
        print("Find one by searching any bit of their info! \n");    }

    public ConsoleUI(InputStream stream, Writer writer) {
        this.stream = stream;
        this.writer = writer;
        inputReader = new BufferedReader(new InputStreamReader(stream));
    }

    public void print(String text) {
        try {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
