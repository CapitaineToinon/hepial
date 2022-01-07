package main;

/**
 * Base class that represent an type
 */
public enum Type {
    Entier("entier"),
    Booleen("booleen"),
    Chaine("chaine"),
    Programme("programme"),
    Aucun("aucun");

    private final String label;

    private Type(String label) {
        this.label = label;
    }

    public String GetLabel() {
        return this.label;
    }
}
