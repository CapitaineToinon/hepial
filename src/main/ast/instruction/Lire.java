package main.ast.instruction;

import main.ast.ASTVisitor;
import main.ast.expression.Idf;

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
