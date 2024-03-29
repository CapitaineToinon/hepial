package hepial.ast.expression.binaire.relation;

import hepial.Type;
import hepial.ast.ASTVisitor;

/**
 * Represent an equal comparaison expression node inside the AST.
 */
public class SupEgal extends Relation {
    /**
     * Constructor
     */
    public SupEgal(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Get the binary operator
     */
    public String operateur() {
        return ">=";
    }

    /**
     * Apply the operator on the two given values.
     */
    public int apply(int gauche, int droite) {
        return (gauche >= droite) ? 1 : 0;
    }

    /**
     * Apply the operator on the two given values.
     */
    public boolean apply(boolean gauche, boolean droite) {
        return (gauche ? 1 : 0) >= (droite ? 1 : 0);
    }

    /**
     * Accepts a AST visitor
     */
    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }

    public Type GetType() {
        return Type.Booleen;
    }
}
