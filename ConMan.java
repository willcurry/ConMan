import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;


public class ConMan {
    private final UI ui;
    private final InputStream stream;
    private ConsoleMenu menu;
    private ArrayList<Contact> allContacts = new ArrayList<>();

    public ConMan(ConsoleMenu consoleMenu, UI ui, InputStream stream) {
        this.menu = consoleMenu;
        this.ui = ui;
        this.stream = stream;
    }

    public ArrayList<Contact> allContacts() {
        return allContacts;
    }

    public void start() {
        Writer writer = new PrintWriter(System.out);
        menu = new ConsoleMenu(commands(), stream, writer);
        menu.runMenu();
    }

    public static void main(String[] args) {
        Writer writer = new PrintWriter(System.out);
        ConsoleMenu consoleMenu = new ConsoleMenu(new ArrayList<>(), System.in, writer);
        UI ui = new ConsoleUI(System.in, writer);
        ConMan conMan = new ConMan(consoleMenu, ui, System.in);
        conMan.start();
    }

    public ArrayList<Command> commands() {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new Search(ui, allContacts));
        commands.add(new Add(ui, allContacts));
        commands.add(new Delete(ui, allContacts));
        commands.add(new Edit(ui, allContacts));
        return commands;
    }
}