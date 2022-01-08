package hepial.ast.instruction;

import hepial.Type;
import hepial.ast.ASTVisitor;
import hepial.ast.expression.Idf;
import java.util.ArrayList;

public class DeclarationVariable extends Instruction {

    private Type type;
    private ArrayList<Idf> identifiants = null;

    public DeclarationVariable(Type type, ArrayList<Idf> identifiants, String fl, int line, int col) {
        super(fl, line, col);
        this.setType(type);
        this.setIdentifiants(identifiants);
    }

    public ArrayList<Idf> getIdentifiants() {
        return identifiants;
    }

    public void setIdentifiants(ArrayList<Idf> identifiants) {
        this.identifiants = identifiants;
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
