package main.ast.instruction;

import main.ast.ASTVisitor;
import main.ast.expression.Expression;

public class Ecrire extends Instruction {
    private Expression source;

    public Ecrire(Expression source, String fl, int line, int col) {
        super(fl, line, col);
        this.source = source;
    }

    public Expression GetSource() {
        return this.source;
    }

    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
