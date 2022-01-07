package main.ast.expression;

import main.Type;
import main.TableDesSymboles;
import main.ast.ASTVisitor;

/**
 * Represent an identifier node inside the AST.
 */
public class Idf extends Expression {
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
    }

    /**
     * Get the identifier value
     */
    public String GetNom() {
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
