package itemhistory.model;

public enum Type {
    IN("IN"),
    OUT("OUT");

    private String name;

    private Type(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
