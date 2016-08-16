import com.sun.tools.javac.code.Attribute;

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
            print(inputReader.readLine());
            return inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void displayMenu(int size) {
        print("Hello, you have " + size + " contacts. \n");
        print("What would you like to do?\n");
        print("-----------------------------\n");
        for (Operations operation : Operations.values()) {
            print(operation + operation.description() + "\n");
        }
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
