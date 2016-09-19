import java.util.ArrayList;

public class Add implements Command{

    private final String name = "Add";
    private final UI ui;
    private final ArrayList<Contact> contacts;

    public Add(UI ui, ArrayList<Contact> contacts) {
        this.ui = ui;
        this.contacts = contacts;
    }

    @Override
    public void execute() {
        Contact contact = userEditedContact();
        contacts.add(contact);
    }

    @Override
    public String name() {
        return name;
    }

    private Contact userEditedContact() {
        ui.requestNewContactInformation();
        return new Contact(ui.userInput(), ui.userInput(), ui.userInput(), ui.userInput());
    }

}
