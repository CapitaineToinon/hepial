package hepial.ast.expression.binaire.arithmetique;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

import hepial.Utils;
import hepial.ast.exceptions.DivideByZeroException;
import hepial.ast.exceptions.IncompatibleTypeException;
import hepial.ast.exceptions.SemantiqueException;
import hepial.App;
import hepial.SemantiqueTest;

public class DivisionTest extends SemantiqueTest {
    private final String TESTS_FOLDER = "ast/expression/binaire/arithmetique/division";

    @Test
    public void division_ok() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "1.hepial");

        try {
            App.main(mock);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void division_type_check_1() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "2.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }

    @Test
    public void division_type_check_2() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "3.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }

    @Test
    public void division_zero() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "4.hepial");
        assertThrows(DivideByZeroException.class, () -> App.main(mock));
    }
}
