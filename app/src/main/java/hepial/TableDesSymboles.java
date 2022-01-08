package hepial;

import java.util.Map;
import java.util.Optional;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;

public class TableDesSymboles {
    private static Optional<String> programme = Optional.empty();
    public static final Map<String, SimpleEntry<Type, Boolean>> variables = new HashMap<>();
    public static final Map<String, Type> constants = new HashMap<String, Type>();

    public static Type GetType(String name) {
        if (programme.isPresent() && programme.get().compareTo(name) == 0) {
            return Type.Programme;
        }

        if (variables.containsKey(name)) {
            return variables.get(name).getKey();
        }

        if (constants.containsKey(name)) {
            return constants.get(name);
        }

        return Type.Aucun;
    }

    public static Optional<String> GetProgramme() throws Exception {
        return programme;
    }

    public static void SetProgramme(String nom) throws Exception {
        programme = Optional.of(nom);
    }

    public static boolean IsAvailable(String nom) {
        return GetType(nom) == Type.Aucun;
    }

    public static boolean Exists(String nom) {
        return !IsAvailable(nom);
    }

    public static void clear() {
        programme = Optional.empty();
        variables.clear();
        constants.clear();
    }
}
