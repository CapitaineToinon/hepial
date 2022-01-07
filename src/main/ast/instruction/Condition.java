package main.ast.instruction;

import main.ast.ASTVisitor;
import main.ast.expression.Expression;
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

    public Expression GetCondition() {
        return condition;
    }

    public Bloc GetThenInstructions() {
        return thenInstructions;
    }

    public Optional<Bloc> GetElseInstructions() {
        return elseInstructions;
    }

    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
