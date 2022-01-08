package hepial.ast.expression;

import hepial.Type;
import hepial.ast.ASTVisitor;

/*
 * Represent a string of characters node inside the AST.
 */
public class Chaine extends Expression {
    /**
     * Value contained in this string node
     */
    private String valeur;

    /**
     * Constructor
     */
    public Chaine(String val, String fl, int line, int col) {
        super(fl, line, col);
        this.valeur = val;
    }

    /**
     * Get the node value
     */
    public String getValeur() {
        return this.valeur;
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
        return Type.Chaine;
    }
}
