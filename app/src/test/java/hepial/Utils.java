package hepial;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import hepial.ast.instruction.DeclarationProgramme;

public class Utils {
    public static final Path resources = Path.of("src/test/resources");

    public static String[] buildArgs(String base, String filename) {
        return new String[] { String.format("%s/%s/%s", resources.toString(), base, filename) };
    }

    public static String readResource(Path file) throws Exception {
        if (file == null) {
            throw new IOException();
        }

        return Files.readString(file);
    }

    public static DeclarationProgramme getProgram(Path source) throws Exception {
        FileReader file = new FileReader(source.toString());
        Lexer myTP = new Lexer(file);
        parser parser = new parser(myTP, source.getFileName().toString());
        return (DeclarationProgramme) parser.parse().value;
    }
}
