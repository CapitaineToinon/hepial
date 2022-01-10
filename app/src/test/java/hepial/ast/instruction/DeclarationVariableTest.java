package hepial.ast.instruction;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

import hepial.App;
import hepial.SemantiqueTest;
import hepial.Utils;
import hepial.ast.exceptions.AlreadyDefinedException;
import hepial.ast.exceptions.SemantiqueException;

public class DeclarationVariableTest extends SemantiqueTest {
    private final String TESTS_FOLDER = "ast/instruction/declarationvariable";

    @Test
    public void declarationvariable_ok() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "1.hepial");

        try {
            App.main(mock);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void declarationvariable_already_defined() throws Exception {
        String[] mock = Utils.buildArgs(TESTS_FOLDER, "2.hepial");
        assertThrows(AlreadyDefinedException.class, () -> App.main(mock));
    }
}
