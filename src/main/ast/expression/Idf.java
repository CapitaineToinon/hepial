package main.ast.expression;

import java.util.ArrayList;
import java.util.List;

import main.Type;
import main.TableDesSymboles;
import main.ast.ASTVisitor;

/*
 * Represent an identifier node inside the AST.
*/

public class Idf extends Expression {

    public static List<Idf> all = new ArrayList<Idf>();

    /**
     * Name of the
     */
    private String nom;

    /**
     * Constructor
     */
    public Idf(String nom, String fl, int line, int col) {
        super(fl, line, col);
        this.nom = nom;
        all.add(this);
    }

    /**
     * Get the identifier value
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Accepts a AST visitor
     */
    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }

    public Type GetType() {
        return TableDesSymboles.GetType(nom);
    }
}
