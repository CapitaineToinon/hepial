package hepial.ast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeNoException;

import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

import hepial.TableDesSymboles;
import hepial.Utils;
import hepial.ast.exceptions.SemantiqueException;
import hepial.ast.instruction.DeclarationProgramme;

public class ByteCodeGeneratorTest {
    private final Path TESTS_FOLDER = Utils.resources.resolve("SourceCodeExamples");

    private static ByteCodeGenerator getGenerator() {
        ByteCodeGenerator generator = new ByteCodeGenerator();
        generator.setPretty(false);
        return generator;
    }

    private static DeclarationProgramme getProgram(Path source) throws Exception {
        DeclarationProgramme program = null;

        try {
            program = Utils.getProgram(source);
            // Do the semantique to populate the table of symbols
            AnalyseSemantique analyseur = new AnalyseSemantique();
            program.accept(analyseur);
            return program;
        } catch (SemantiqueException e) {
            // skip the test if a SemantiqueException happened
            assumeNoException(e);
        }

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

    @Test
    public void generator_teacher_3() throws Exception {
        String hepial = "3.hepial";
        String bytecode = "3.bytecode";

        Path source = TESTS_FOLDER.resolve(hepial);
        DeclarationProgramme program = getProgram(source);
        ByteCodeGenerator generator = getGenerator();
        String expected = Utils.readResource(TESTS_FOLDER.resolve(bytecode));
        String actual = (String) program.accept(generator);
        assertEquals(expected, actual);
    }

    @Test
    public void generator_teacher_4() throws Exception {
        String hepial = "4.hepial";
        String bytecode = "4.bytecode";

        Path source = TESTS_FOLDER.resolve(hepial);
        DeclarationProgramme program = getProgram(source);
        ByteCodeGenerator generator = getGenerator();
        String expected = Utils.readResource(TESTS_FOLDER.resolve(bytecode));
        String actual = (String) program.accept(generator);
        assertEquals(expected, actual);
    }

    @Test
    public void generator_teacher_5() throws Exception {
        String hepial = "5.hepial";
        String bytecode = "5.bytecode";

        Path source = TESTS_FOLDER.resolve(hepial);
        DeclarationProgramme program = getProgram(source);
        ByteCodeGenerator generator = getGenerator();
        String expected = Utils.readResource(TESTS_FOLDER.resolve(bytecode));
        String actual = (String) program.accept(generator);
        assertEquals(expected, actual);
    }

    @Test
    public void generator_teacher_6() throws Exception {
        String hepial = "6.hepial";
        String bytecode = "6.bytecode";

        Path source = TESTS_FOLDER.resolve(hepial);
        DeclarationProgramme program = getProgram(source);
        ByteCodeGenerator generator = getGenerator();
        String expected = Utils.readResource(TESTS_FOLDER.resolve(bytecode));
        String actual = (String) program.accept(generator);
        assertEquals(expected, actual);
    }

    @Test
    public void generator_teacher_7() throws Exception {
        String hepial = "7.hepial";
        String bytecode = "7.bytecode";

        Path source = TESTS_FOLDER.resolve(hepial);
        DeclarationProgramme program = getProgram(source);
        ByteCodeGenerator generator = getGenerator();
        String expected = Utils.readResource(TESTS_FOLDER.resolve(bytecode));
        String actual = (String) program.accept(generator);
        assertEquals(expected, actual);
    }

    @Test
    public void generator_teacher_8() throws Exception {
        String hepial = "8.hepial";
        String bytecode = "8.bytecode";

        Path source = TESTS_FOLDER.resolve(hepial);
        DeclarationProgramme program = getProgram(source);
        ByteCodeGenerator generator = getGenerator();
        String expected = Utils.readResource(TESTS_FOLDER.resolve(bytecode));
        String actual = (String) program.accept(generator);
        assertEquals(expected, actual);
    }

    @Test
    public void generator_teacher_9() throws Exception {
        String hepial = "9.hepial";
        String bytecode = "9.bytecode";

        Path source = TESTS_FOLDER.resolve(hepial);
        DeclarationProgramme program = getProgram(source);
        ByteCodeGenerator generator = getGenerator();
        String expected = Utils.readResource(TESTS_FOLDER.resolve(bytecode));
        String actual = (String) program.accept(generator);
        assertEquals(expected, actual);
    }

    @Test
    public void generator_teacher_12() throws Exception {
        String hepial = "12.hepial";
        String bytecode = "12.bytecode";

        Path source = TESTS_FOLDER.resolve(hepial);
        DeclarationProgramme program = getProgram(source);
        ByteCodeGenerator generator = getGenerator();
        String expected = Utils.readResource(TESTS_FOLDER.resolve(bytecode));
        String actual = (String) program.accept(generator);
        assertEquals(expected, actual);
    }
}
