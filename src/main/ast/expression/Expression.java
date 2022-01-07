package main.ast.expression;

import main.ast.ASTNode;
import main.Type;

/*
 * Base class that represent an expression node inside the AST.
 */
public abstract class Expression extends ASTNode {
    public Expression(String fl, int line, int col) {
        super(fl, line, col);
    }

    public abstract Type GetType();
}
