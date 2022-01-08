package hepial.ast.expression.binaire;

import hepial.ast.expression.Expression;

/*
 * Base class that represent a binary expression node inside the AST.
*/

public abstract class Binaire extends Expression {
    /**
     * The expression at its left
     */
    protected Expression operandeGauche;
    /**
     * The expression at its right
     */
    protected Expression operandeDroite;

    /**
     * Constructor
     */
    public Binaire(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Get the left expression
     */
    public Expression GetGauche() {
        return this.operandeGauche;
    }

    /**
     * Get the right expression
     */
    public Expression GetDroite() {
        return this.operandeDroite;
    }

    /**
     * Get the binary operator.
     * Must be implemented by the child class.
     */
    public abstract String operateur();

    /**
     * Set the left expression
     */
    public void LierGauche(Expression exp) {
        this.operandeGauche = exp;
    }

    /**
     * Set the right expression
     */
    public void LierDroit(Expression exp) {
        this.operandeDroite = exp;
    }

    /**
     * Transform this node into a visualisable string
     */
    public String toString() {
        return this.operandeGauche + " " + this.operateur() + " " + this.operandeDroite;
    }
}
