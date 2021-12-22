public class DeclarationConstant extends Instruction {

    private Type type;
    private Expression expression;
    private Idf identifier;

    public DeclarationConstant(Type type, Idf identifier, Expression expression, String fl, int line, int col) {
        super(fl, line, col);
        this.type = type;
        this.identifier = identifier;
        this.expression = expression;
    }

    Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
