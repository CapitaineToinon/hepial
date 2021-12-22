/*
 * Represent an equal comparaison expression node inside the AST.
 */

public class Diff extends Relation {
    /**
     * Constructor
     */
    public Diff(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Get the binary operator
     */
    public String operateur() {
        return "<>";
    }

    /**
     * Apply the operator on the two given values.
     */
    public int apply(int gauche, int droite) {
        return (gauche != droite) ? 1 : 0;
    }

    /**
     * Apply the operator on the two given values.
     */
    public boolean apply(boolean gauche, boolean droite) {
        return gauche != droite;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
