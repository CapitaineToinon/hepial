package hepial.ast.instruction;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.nio.file.Path;

import org.junit.Test;

import hepial.SemantiqueTest;
import hepial.Utils;
import hepial.ast.AnalyseSemantique;
import hepial.ast.exceptions.IncompatibleTypeException;
import hepial.ast.exceptions.SemantiqueException;

public class ConditionTest extends SemantiqueTest {
    private final Path TESTS_FOLDER = Utils.resources.resolve("ast/instruction/condition");

    @Test
    public void condition_ok() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("1.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();

        try {
            program.accept(semantique);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void condition_type_check() throws Exception {
        DeclarationProgramme program = Utils.getProgram(TESTS_FOLDER.resolve("2.hepial"));
        AnalyseSemantique semantique = new AnalyseSemantique();
        assertThrows(IncompatibleTypeException.class, () -> program.accept(semantique));
    }
}
