public class DeclarationConstant extends Instruction {

    private Type type;
    private Expression expression = null;

    public DeclarationConstant(Type type, Expression expression, String fl, int line, int col) {
        super(fl, line, col);
        this.type = type;
        this.expression = expression;
    }

    Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
