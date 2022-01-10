package hepial.ast.instruction;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

import hepial.App;
import hepial.SemantiqueTest;
import hepial.Utils;
import hepial.ast.exceptions.AssignConstantException;
import hepial.ast.exceptions.IncompatibleTypeException;
import hepial.ast.exceptions.SemantiqueException;

public class AffectationTest extends SemantiqueTest {
    private final String TESTS_FOLDER = "ast/instruction/affectation";

    @Test
    public void affectation_ok() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "1.hepial");

        try {
            App.main(mock);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void affectation_const() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "2.hepial");
        assertThrows(AssignConstantException.class, () -> App.main(mock));
    }

    @Test
    public void affectation_type_check() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "3.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }
}
