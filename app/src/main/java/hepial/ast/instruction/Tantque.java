package hepial.ast.instruction;

import hepial.ast.ASTVisitor;
import hepial.ast.expression.Expression;

public class Tantque extends Instruction {
    private Expression condition;
    private Bloc instructions;

    public Tantque(Expression condition, Bloc instructions, String fl, int line, int col) {
        super(fl, line, col);
        this.SetCondition(condition);
        this.SetInstructions(instructions);
    }

    public Expression GetCondition() {
        return condition;
    }

    public void SetCondition(Expression condition) {
        this.condition = condition;
    }

    public Bloc GetInstructions() {
        return instructions;
    }

    public void SetInstructions(Bloc instructions) {
        this.instructions = instructions;
    }

    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
