import java.util.Optional;

public class Condition extends Instruction {

    private Expression condition;
    private Bloc thenInstructions;
    private Optional<Bloc> elseInstructions;

    public Condition(Expression condition, Bloc thenInstructions,
            Optional<Bloc> elseInstructions, String fl, int line, int col) {
        super(fl, line, col);
        this.condition = condition;
        this.thenInstructions = thenInstructions;
        this.elseInstructions = elseInstructions;
    }

    public Expression getCondition() {
        return condition;
    }

    public Bloc getThenInstructions() {
        return thenInstructions;
    }

    public Optional<Bloc> getElseInstructions() {
        return elseInstructions;
    }

    public boolean hasElse() {
        return this.elseInstructions.isPresent();
    }

    Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
