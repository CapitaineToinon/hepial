public class Pour extends Instruction {

    private Idf iterator;

    private Expression from;

    private Expression to;

    private Bloc instructions;

    public Pour(Idf iterator, Expression from, Expression to, Bloc instructions, String fl, int line, int col) {
        super(fl, line, col);
        this.setIterator(iterator);
        this.setFrom(from);
        this.setTo(to);
        this.setInstructions(instructions);
    }

    public Bloc getInstructions() {
        return instructions;
    }

    public void setInstructions(Bloc instructions) {
        this.instructions = instructions;
    }

    public Expression getTo() {
        return to;
    }

    public void setTo(Expression to) {
        this.to = to;
    }

    public Expression getFrom() {
        return from;
    }

    public void setFrom(Expression from) {
        this.from = from;
    }

    public Idf getIteratorName() {
        return iterator;
    }

    public void setIterator(Idf iterator) {
        this.iterator = iterator;
    }

    Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
