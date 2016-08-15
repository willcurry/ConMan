public class Contact {
    private final String lastName;
    private final String firstName;
    private final String telephone;
    private final String email;

    public Contact(String firstName, String lastName, String telephone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        add(this);
    }

    public void add(Contact contact) {
        ConsoleSearch.allContacts.add(contact);
    }

    public void delete(Contact contact) {
        ConsoleSearch.allContacts.remove(ConsoleSearch.allContacts.indexOf(contact));
    }

    public void edit(Contact contact, Contact updatedContact) {
        ConsoleSearch.allContacts.set(ConsoleSearch.allContacts.indexOf(contact), updatedContact);
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String telephone() {
        return telephone;
    }

    public String email() {
        return email;
    }
}
