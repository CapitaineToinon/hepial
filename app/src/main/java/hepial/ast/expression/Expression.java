package hepial.ast.expression;

import hepial.ast.ASTNode;
import hepial.Type;

/*
 * Base class that represent an expression node inside the AST.
 */
public abstract class Expression extends ASTNode {
    public Expression(String fl, int line, int col) {
        super(fl, line, col);
    }

    public abstract Type GetType();
}
