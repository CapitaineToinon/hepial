package hepial.ast.instruction;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.nio.file.Path;

import org.junit.Test;

import hepial.SemantiqueTest;
import hepial.Utils;
import hepial.ast.AnalyseSemantique;
import hepial.ast.exceptions.AssignConstantException;
import hepial.ast.exceptions.SemantiqueException;

public class LireTest extends SemantiqueTest {
    private final Path TESTS_FOLDER = Utils.resources.resolve("ast/instruction/lire");

    @Test
    public void lire_ok() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("1.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();

        try {
            program.accept(semantique);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void lire_const() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("2.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();
        assertThrows(AssignConstantException.class, () -> program.accept(semantique));
    }
}
