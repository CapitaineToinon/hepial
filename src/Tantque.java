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

    Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
