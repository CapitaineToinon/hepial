package hepial.ast.instruction;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.nio.file.Path;

import org.junit.Test;

import hepial.SemantiqueTest;
import hepial.Utils;
import hepial.ast.AnalyseSemantique;
import hepial.ast.exceptions.AssignConstantException;
import hepial.ast.exceptions.IncompatibleTypeException;
import hepial.ast.exceptions.SemantiqueException;

public class PourTest extends SemantiqueTest {
    private final Path TESTS_FOLDER = Utils.resources.resolve("ast/instruction/pour");

    @Test
    public void pour_ok() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("1.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();

        try {
            program.accept(semantique);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void pour_const() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("2.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();
        assertThrows(AssignConstantException.class, () -> program.accept(semantique));
    }

    @Test
    public void pour_type_check_1() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("3.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();
        assertThrows(IncompatibleTypeException.class, () -> program.accept(semantique));
    }

    @Test
    public void pour_type_check_2() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("4.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();
        assertThrows(IncompatibleTypeException.class, () -> program.accept(semantique));
    }

    @Test
    public void pour_type_check_3() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("5.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();
        assertThrows(IncompatibleTypeException.class, () -> program.accept(semantique));
    }
}
