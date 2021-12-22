public class DeclarationConstant extends Instruction {

    private Type type;
    private Expression expression;
    private Idf identifier;

    public DeclarationConstant(Type type, Idf identifier, Expression expression, String fl, int line, int col) {
        super(fl, line, col);
        this.setType(type);
        this.setIdentifier(identifier);
        this.setExpression(expression);
    }

    public Idf getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Idf identifier) {
        this.identifier = identifier;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
