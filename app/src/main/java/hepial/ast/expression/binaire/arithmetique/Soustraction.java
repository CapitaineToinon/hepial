package hepial.ast.expression.binaire.arithmetique;

import hepial.Type;
import hepial.ast.ASTVisitor;

/**
 * Represent an subtraction expression node inside the AST.
 */
public class Soustraction extends Arithmetique {
    /**
     * Constructor
     */
    public Soustraction(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Get the binary operator
     */
    public String operateur() {
        return "-";
    }

    /**
     * Apply the operator on the two given values.
     */
    public int apply(int gauche, int droite) {
        return gauche - droite;
    }

    /**
     * Apply the operator on the two given values.
     */
    public boolean apply(boolean gauche, boolean droite) {
        return ((gauche ? 1 : 0) - (droite ? 1 : 0)) != 0;
    }

    /**
     * Accepts a AST visitor
     */
    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }

    @Override
    public Type GetType() {
        return Type.Entier;
    }
}
