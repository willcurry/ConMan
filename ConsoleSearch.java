import java.io.*;
import java.util.ArrayList;

public class ConsoleSearch {
    private final InputStream stream;
    private final BufferedReader inputReader;
    public static ArrayList<Contact> allContacts = new ArrayList<>();
    private final Writer writer;

    public ConsoleSearch(InputStream stream, Writer writer) {
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

    public ArrayList<Contact> searchContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        String nextSearch = null;
        try {
            nextSearch = inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Contact contact : allContacts) {
            if (contact.firstName().contains(nextSearch) || contact.lastName().contains(nextSearch) || contact.email().contains(nextSearch)) {
                contacts.add(contact);
            }
        }
        return contacts;
    }

    private void start() {
        print("Hello, you have " + allContacts.size() + " contacts. \n");
        searchContacts();
    }

    public static void main(String[] args) {
        Writer writer = new PrintWriter(System.out);
        ConsoleSearch consoleSearch = new ConsoleSearch(System.in, writer);
        consoleSearch.start();
    }
}
