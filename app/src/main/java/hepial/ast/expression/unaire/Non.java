package hepial.ast.expression.unaire;

import hepial.Type;
import hepial.ast.ASTVisitor;

public class Non extends Unaire {

    public Non(String fl, int line, int col) {
        super(fl, line, col);
    }

    public String operateur() {
        return "non";
    }

    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }

    public int apply(int value) {
        // @TODO can this be applied to an int?
        return value;
    }

    public boolean apply(boolean value) {
        return !value;
    }

    public Type GetType() {
        return Type.Booleen;
    }
}
