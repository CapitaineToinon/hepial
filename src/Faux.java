
/*
 * Represent a false node inside the AST.
*/

public class Faux extends Expression {
    /**
     * Value contained in this true node
     */
    private boolean valeur;

    /**
     * Constructor
     */
    public Faux(String fl, int line, int col) {
        super(fl, line, col);
        this.valeur = false;
    }

    /**
     * Get the node value
     */
    public boolean getValeur() {
        return this.valeur;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
