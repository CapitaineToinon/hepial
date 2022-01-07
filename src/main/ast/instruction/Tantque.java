package main.ast.instruction;

import main.ast.ASTVisitor;
import main.ast.expression.Expression;

public class Tantque extends Instruction {
    private Expression condition;
    private Bloc instructions;

    public Tantque(Expression condition, Bloc instructions, String fl, int line, int col) {
        super(fl, line, col);
        this.setCondition(condition);
        this.setInstructions(instructions);
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public Bloc getInstructions() {
        return instructions;
    }

    public void setInstructions(Bloc instructions) {
        this.instructions = instructions;
    }

    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
