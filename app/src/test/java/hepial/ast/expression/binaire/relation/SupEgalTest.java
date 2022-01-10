package hepial.ast.expression.binaire.relation;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

import hepial.Utils;
import hepial.ast.exceptions.IncompatibleTypeException;
import hepial.ast.exceptions.SemantiqueException;
import hepial.App;
import hepial.SemantiqueTest;

public class SupEgalTest extends SemantiqueTest {
    private final String TESTS_FOLDER = "ast/expression/binaire/relation/supegal";

    @Test
    public void supegal_ok() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "1.hepial");

        try {
            App.main(mock);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void supegal_type_check_1() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "2.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }

    @Test
    public void supegal_type_check_2() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "3.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }
}
