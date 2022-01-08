package hepial.ast.expression;

import hepial.Type;
import hepial.ast.ASTVisitor;

/**
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
    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }

    public Type GetType() {
        return Type.Booleen;
    }
}
