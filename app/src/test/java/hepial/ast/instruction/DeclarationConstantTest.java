package hepial.ast.instruction;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.nio.file.Path;

import org.junit.Test;

import hepial.SemantiqueTest;
import hepial.Utils;
import hepial.ast.AnalyseSemantique;
import hepial.ast.exceptions.AlreadyDefinedException;
import hepial.ast.exceptions.IncompatibleTypeException;
import hepial.ast.exceptions.SemantiqueException;

public class DeclarationConstantTest extends SemantiqueTest {
    private final Path TESTS_FOLDER = Utils.resources.resolve("ast/instruction/declarationconstant");

    @Test
    public void declarationconstant_ok() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("1.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();

        try {
            program.accept(semantique);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void declarationconstant_type_check_1() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("2.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();
        assertThrows(IncompatibleTypeException.class, () -> program.accept(semantique));
    }

    @Test
    public void declarationconstant_type_check_2() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("3.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();
        assertThrows(IncompatibleTypeException.class, () -> program.accept(semantique));
    }

    @Test
    public void declarationconstant_already_defined() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("4.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();
        assertThrows(AlreadyDefinedException.class, () -> program.accept(semantique));
    }
}
