public enum Operations {
    Search(" (Search for a contact)"),
    Delete(" (Delete a contact)"),
    Edit(" (Edit a contact)"),
    Add(" (Add a contact)");

    Operations(String description) {
        this.description = description;
    }

    private final String description;

    public String description() {
        return description;
    }
}
