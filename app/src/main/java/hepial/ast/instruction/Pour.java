package hepial.ast.instruction;

import hepial.ast.ASTVisitor;
import hepial.ast.expression.Expression;
import hepial.ast.expression.Idf;

public class Pour extends Instruction {
    private Idf iterator;
    private Expression from;
    private Expression to;
    private Bloc instructions;

    public Pour(Idf iterator, Expression from, Expression to, Bloc instructions, String fl, int line, int col) {
        super(fl, line, col);
        this.SetIterator(iterator);
        this.SetFrom(from);
        this.SetTo(to);
        this.SetInstructions(instructions);
    }

    public Bloc GetInstructions() {
        return instructions;
    }

    public void SetInstructions(Bloc instructions) {
        this.instructions = instructions;
    }

    public Expression GetTo() {
        return to;
    }

    public void SetTo(Expression to) {
        this.to = to;
    }

    public Expression GetFrom() {
        return from;
    }

    public void SetFrom(Expression from) {
        this.from = from;
    }

    public Idf GetIteratorName() {
        return iterator;
    }

    public void SetIterator(Idf iterator) {
        this.iterator = iterator;
    }

    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
