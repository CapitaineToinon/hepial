import java.util.List;

/*
 * Base class that represent an type
*/

public enum Type {
    Entier("entier", List.of(Nombre.class)),
    Booleen("booleen", List.of(Vrai.class, Faux.class));

    public final String label;
    public final List<Class<?>> accepted;

    private Type(String label, List<Class<?>> accepted) {
        this.label = label;
        this.accepted = accepted;
    }
}
