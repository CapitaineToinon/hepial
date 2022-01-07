package main.ast.expression;

import main.Type;
import main.ast.ASTVisitor;

/**
 * Represent a true node inside the AST.
 */
public class Vrai extends Expression {
    /**
     * Value contained in this true node
     */
    private boolean valeur;

    /**
     * Constructor
     */
    public Vrai(String fl, int line, int col) {
        super(fl, line, col);
        this.valeur = true;
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
