package hepial.ast.expression.unaire;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

import hepial.Utils;
import hepial.ast.exceptions.IncompatibleTypeException;
import hepial.ast.exceptions.SemantiqueException;
import hepial.App;
import hepial.SemantiqueTest;

public class NonTest extends SemantiqueTest {
    private final String TESTS_FOLDER = "ast/expression/unaire/non";

    @Test
    public void non_ok() throws Exception {
        String[] mock = Utils.BuildArgs(TESTS_FOLDER, "1.hepial");

        try {
            App.main(mock);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void non_type_check() throws Exception {
        String[] mock = Utils.BuildArgs(TESTS_FOLDER, "2.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }
}
