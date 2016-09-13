import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;


public class ConMan {
    private final UI ui;
    private ConsoleMenu menu;
    public ArrayList<Contact> allContacts = new ArrayList<>();

    public ConMan(ConsoleMenu consoleMenu, UI ui) {
        this.menu = consoleMenu;
        this.ui = ui;
        //allContacts.add(new Contact("Will", "Curry", "Tele", "Email"));
    }

    public ArrayList<Contact> allContacts() {
        return allContacts;
    }

    public void shouldContinue() {
        ui.userContinue();
        if (ui.userInput().equals("ok")) {
            start();
        }
    }

    private void start() {
        Writer writer = new PrintWriter(System.out);
        menu = new ConsoleMenu(menuItems(), System.in, writer);
        ui.clearConsole();
        menu.displayItems();
        userSelectItem();
        start();
    }

    public ArrayList<Contact> userSelectItem() {
        String input = ui.userInput();
        ArrayList<MenuItem> items = menu.getItems();
        for (MenuItem item : items) {
             if (input.equals(item.name())) {
                 if (input.equals("Search")) ui.displayAllContacts(userSelectItem());
                 allContacts = (ArrayList<Contact>) item.execute(allContacts);
             }
        }
        return allContacts;
    }

    public static void main(String[] args) {
        Writer writer = new PrintWriter(System.out);
        ConsoleMenu consoleMenu = new ConsoleMenu(new ArrayList<>(), System.in, writer);
        UI ui = new ConsoleUI(System.in, writer);
        ConMan conMan = new ConMan(consoleMenu, ui);
        conMan.start();
    }

    private ArrayList<MenuItem> menuItems() {
        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new Search(ui));
        items.add(new Add(ui));
        items.add(new Delete(ui));
        return items;
    }
}
