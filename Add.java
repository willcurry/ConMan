import java.util.ArrayList;

public class Add implements MenuItem <ArrayList<Contact>>{

    private final String name = "Add";
    private final UI ui;

    public Add(UI ui) {
        this.ui = ui;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public ArrayList<Contact> execute(ArrayList<Contact> contacts) {
       return add(contacts);
    }

    public ArrayList<Contact> add(ArrayList<Contact> contacts) {
        contacts.add(userEditedContact());
        System.out.println(contacts);
        return contacts;
    }

    private Contact userEditedContact() {
        ui.requestNewContactInformation();
        return new Contact(ui.userInput(), ui.userInput(), ui.userInput(), ui.userInput());
    }

}
