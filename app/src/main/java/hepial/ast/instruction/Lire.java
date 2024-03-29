package hepial.ast.instruction;

import hepial.ast.ASTVisitor;
import hepial.ast.expression.Idf;

public class Lire extends Instruction {

    protected Idf destination;

    public Lire(Idf destination, String fl, int line, int col) {
        super(fl, line, col);
        this.destination = destination;
    }

    public Idf GetDestination() {
        return this.destination;
    }

    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
