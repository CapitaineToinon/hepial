package hepial.ast;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

import hepial.TableDesSymboles;
import hepial.Utils;
import hepial.ast.instruction.DeclarationProgramme;

public class ByteCodeGeneratorTest {
    private final Path TESTS_FOLDER = Utils.resources.resolve("SourceCodeExamples");

    private static ByteCodeGenerator getGenerator() {
        ByteCodeGenerator generator = new ByteCodeGenerator();
        generator.setPretty(false);
        return generator;
    }

    private static DeclarationProgramme getProgram(Path source) throws Exception {
        DeclarationProgramme program = Utils.getProgram(source);
        AnalyseSemantique analyseur = new AnalyseSemantique();
        program.accept(analyseur);
        return program;
    }

    @Before
    public void clear() {
        TableDesSymboles.clear();
    }

    @Test
    public void generator_teacher_1() throws Exception {
        String hepial = "1.hepial";
        String bytecode = "1.bytecode";

        Path source = TESTS_FOLDER.resolve(hepial);
        DeclarationProgramme program = getProgram(source);
        ByteCodeGenerator generator = getGenerator();
        String expected = Utils.readResource(TESTS_FOLDER.resolve(bytecode));
        String actual = (String) program.accept(generator);
        assertEquals(expected, actual);
    }

    @Test
    public void generator_teacher_2() throws Exception {
        String hepial = "2.hepial";
        String bytecode = "2.bytecode";

        Path source = TESTS_FOLDER.resolve(hepial);
        DeclarationProgramme program = getProgram(source);
        ByteCodeGenerator generator = getGenerator();
        String expected = Utils.readResource(TESTS_FOLDER.resolve(bytecode));
        String actual = (String) program.accept(generator);
        assertEquals(expected, actual);
    }
}
