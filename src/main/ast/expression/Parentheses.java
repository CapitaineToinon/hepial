package main.ast.expression;

import main.Type;
import main.ast.ASTVisitor;

public class Parentheses extends Expression {

  private Expression expression;

  public Parentheses(Expression expression, String fl, int line, int col) {
    super(fl, line, col);
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public Object accept(ASTVisitor visitor) throws Exception {
    return visitor.visit(this);
  }

  public Type GetType() {
    return expression.GetType();
  }
}