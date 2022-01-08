package hepial.ast.instruction;

import hepial.ast.ASTVisitor;
import hepial.ast.expression.Expression;

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
