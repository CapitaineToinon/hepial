import java.util.ArrayList;

public class DeclarationVariable extends Instruction {

    private Type type;
    private ArrayList<Idf> identifiants = null;

    public DeclarationVariable(Type type, ArrayList<Idf>identifiants, String fl, int line, int col) {
        super(fl, line, col);
        this.type = type;
        this.identifiants = identifiants;
    }

    Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
