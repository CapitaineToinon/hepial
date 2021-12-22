public class Ecrire extends Instruction {
    private Expression source;

    public Ecrire(Expression source, String fl, int line, int col) {
        super(fl, line, col);
        this.source = source;
    }

    public Expression getSource() {
        return this.source;
    }

    Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }

}
