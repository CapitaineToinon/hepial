package main.ast.instruction;

import main.ast.ASTVisitor;
import main.ast.expression.Idf;
import main.ast.expression.Expression;

/*
 * Represent an assignment node inside the AST.
 */

public class Affectation extends Instruction {
    /**
     * The source operand (at its right)
     */
    protected Expression source;
    /**
     * The destination variable or array (at its left)
     */
    protected Idf destination;

    /**
     * Constructor
     */
    public Affectation(Idf dest, Expression src, String fl, int line, int col) {
        super(fl, line, col);
        this.destination = dest;
        this.source = src;
    }

    /**
     * Get the destination variable or array (at its left)
     */
    public Idf GetDestination() {
        return this.destination;
    }

    /**
     * Get the source operand (at its right)
     */
    public Expression GetSource() {
        return this.source;
    }

    /**
     * Accepts a AST visitor
     */
    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
