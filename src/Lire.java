public class Lire extends Instruction {

    protected Idf destination;

    public Lire(Idf destination, String fl, int line, int col) {
        super(fl, line, col);
        this.destination = destination;
    }

    public Idf getDestination() {
        return this.destination;
    }

    Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }

}
