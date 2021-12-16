public class Parentheses extends Expression {

  private Expression expression;

  public Parentheses(Expression expression, String fl, int line, int col) {
    super(fl, line, col);
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  Object accept(ASTVisitor visitor) {
    return visitor.visit(this);
  }

}
