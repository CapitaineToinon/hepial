package hepial.ast.instruction;

import hepial.Type;
import hepial.ast.ASTVisitor;
import hepial.ast.expression.Expression;
import hepial.ast.expression.Idf;

public class DeclarationConstant extends Instruction {

    private Type type;
    private Idf identifier;
    private Expression expression;

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

    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }

}
