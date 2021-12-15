
/*
 * Base class that represent a binary expression node inside the AST.
*/

public abstract class Unaire extends Expression {
    /**
     * The expression at its left
     */
    protected Expression operande;

    /**
     * Constructor
     */
    public Unaire(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Get the left expression
     */
    public Expression getOperande() {
        return this.operande;
    }

    /**
     * Get the binary operator.
     * Must be implemented by the child class.
     */
    public abstract String operateur();

    /**
     * Set the left expression
     */
    public void lier(Expression exp) {
        this.operande = exp;
    }

    /**
     * Apply the operator on the two given values.
     * Must be implemented by the child class.
     */
    public abstract int apply(int value);

    /**
     * Apply the operator on the two given values.
     * Must be implemented by the child class.
     */
    public abstract boolean apply(boolean value);

    /**
     * Transform this node into a visualisable string
     */
    public String toString() {
        return this.operateur() + " " + this.operande;
    }
}
