package hepial;

public class Utils {
    public static String[] BuildArgs(String base, String filename) {
        return new String[] { String.format("src/test/resources/%s/%s", base, filename) };
    }
}
