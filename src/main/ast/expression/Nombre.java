package main.ast.expression;

import main.Type;
import main.ast.ASTVisitor;

/**
 * Represent a number node inside the AST.
 */
public class Nombre extends Expression {
    /**
     * Value contained in this number node
     */
    private int valeur;

    /**
     * Constructor
     */
    public Nombre(int val, String fl, int line, int col) {
        super(fl, line, col);
        this.valeur = val;
    }

    /**
     * Get the node value
     */
    public int getValeur() {
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
        return Type.Entier;
    }
}
