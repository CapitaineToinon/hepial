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

public class PourTest extends SemantiqueTest {
    private final String TESTS_FOLDER = "ast/instruction/pour";

    @Test
    public void pour_ok() throws Exception {
        String[] mock = Utils.BuildArgs(TESTS_FOLDER, "1.hepial");

        try {
            App.main(mock);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void pour_const() throws Exception {
        String[] mock = Utils.BuildArgs(TESTS_FOLDER, "2.hepial");
        assertThrows(AssignConstantException.class, () -> App.main(mock));
    }

    @Test
    public void pour_type_check_1() throws Exception {
        String[] mock = Utils.BuildArgs(TESTS_FOLDER, "3.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }

    @Test
    public void pour_type_check_2() throws Exception {
        String[] mock = Utils.BuildArgs(TESTS_FOLDER, "4.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }

    @Test
    public void pour_type_check_3() throws Exception {
        String[] mock = Utils.BuildArgs(TESTS_FOLDER, "5.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }
}
