package hepial.ast.instruction;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

import hepial.App;
import hepial.SemantiqueTest;
import hepial.Utils;
import hepial.ast.exceptions.AlreadyDefinedException;
import hepial.ast.exceptions.IncompatibleTypeException;
import hepial.ast.exceptions.SemantiqueException;

public class DeclarationConstantTest extends SemantiqueTest {
    private final String TESTS_FOLDER = "ast/instruction/declarationconstant";

    @Test
    public void declarationconstant_ok() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "1.hepial");

        try {
            App.main(mock);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void declarationconstant_type_check_1() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "2.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }

    @Test
    public void declarationconstant_type_check_2() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "3.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }

    @Test
    public void declarationconstant_already_defined() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "4.hepial");
        assertThrows(AlreadyDefinedException.class, () -> App.main(mock));
    }
}
