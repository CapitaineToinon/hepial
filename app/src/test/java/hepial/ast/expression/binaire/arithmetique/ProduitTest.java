package hepial.ast.expression.binaire.arithmetique;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

import hepial.Utils;
import hepial.ast.exceptions.IncompatibleTypeException;
import hepial.ast.exceptions.SemantiqueException;
import hepial.App;
import hepial.SemantiqueTest;

public class ProduitTest extends SemantiqueTest {
    private final String TESTS_FOLDER = "ast/expression/binaire/arithmetique/produit";

    @Test
    public void produit_ok() throws Exception {
        String[] mock = Utils.BuildArgs(TESTS_FOLDER, "1.hepial");

        try {
            App.main(mock);
        } catch (SemantiqueException e) {
            fail("should not have failed");
        }
    }

    @Test
    public void produit_type_check_1() throws Exception {
        String[] mock = Utils.BuildArgs(TESTS_FOLDER, "2.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }

    @Test
    public void produit_type_check_2() throws Exception {
        String[] mock = Utils.BuildArgs(TESTS_FOLDER, "3.hepial");
        assertThrows(IncompatibleTypeException.class, () -> App.main(mock));
    }
}