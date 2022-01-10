package hepial.ast.expression.binaire.arithmetique;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.nio.file.Path;

import org.junit.Test;

import hepial.Utils;
import hepial.ast.AnalyseSemantique;
import hepial.ast.exceptions.IncompatibleTypeException;
import hepial.ast.exceptions.SemantiqueException;
import hepial.ast.instruction.DeclarationProgramme;
import hepial.SemantiqueTest;

public class AdditionTest extends SemantiqueTest {
    private final Path TESTS_FOLDER = Utils.resources.resolve("ast/expression/binaire/arithmetique/addition");

    @Test
    public void addition_ok() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("1.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();

        try {
            program.accept(semantique);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void addition_type_check_1() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("2.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();
        assertThrows(IncompatibleTypeException.class, () -> program.accept(semantique));
    }

    @Test
    public void addition_type_check_2() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("3.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();
        assertThrows(IncompatibleTypeException.class, () -> program.accept(semantique));
    }
}
