package main.ast;

/*
 * Source code generator that walks the AST and generates code source from the AST
 *
 * @author Stephane Malandain 
 */
import main.ast.expression.*;
import main.ast.expression.binaire.arithmetique.*;
import main.ast.expression.binaire.relation.*;
import main.ast.expression.unaire.*;
import main.ast.instruction.*;
import java.util.*;

public class SourceCodeGenerator implements ASTVisitor {

    /**
     * Generated code
     */
    private String code = "";
    /**
     * Depth level (tabulations)
     */
    private int level = 0;
    /**
     * If we are currently declaring function parameters
     */
    // private boolean isParameterDeclaration = false;

    /**
     * Adds tabulation with current level
     */
    private void addTabulation() {
        addTabulation(level);
    }

    /**
     * Adds tabulation with spacified level
     */
    private void addTabulation(int level) {
        for (int i = 0; i < level; i++)
            code += "    ";
    }

    public Object visit(Addition node) throws Exception {
        node.GetGauche().accept(this);
        code += " + ";
        node.GetDroite().accept(this);
        return null;
    }

    public Object visit(Affectation node) throws Exception {
        node.GetDestination().accept(this);
        code += " = ";
        node.GetSource().accept(this);
        code += ";";
        return null;
    }

    public Object visit(Bloc node) throws Exception {
        for (Instruction inst : node.getInstructions()) {
            code += "\n";
            addTabulation();
            inst.accept(this);
        }
        return null;
    }

    public Object visit(Chaine node) throws Exception {
        code += node.getValeur();
        return null;
    }

    public Object visit(Condition node) throws Exception {
        code += "si ";
        node.getCondition().accept(this);
        code += " alors";
        level += 1;
        node.getThenInstructions().accept(this);
        if (node.hasElse()) {
            code += "\n";
            addTabulation(level - 1);
            code += "sinon";
            node.getElseInstructions().get().accept(this);
        }
        level -= 1;
        code += "\n";
        addTabulation();
        code += "finsi";
        return null;
    }

    public Object visit(DeclarationConstant node) throws Exception {
        // Symbole sym = TDS.getInstance().identifier(new
        // Entree(node.getIdentifier().getNom()));
        // code += sym + " ";
        code += "constante ";
        code += node.getType().GetLabel() + " ";
        node.getIdentifier().accept(this);
        code += " = ";
        node.getExpression().accept(this);
        code += ";";
        return null;
    }

    public Object visit(DeclarationProgramme node) throws Exception {
        /*
         * TDS.getInstance().resetBlocNumber();
         * 
         * TDS.getInstance().entreeBloc();
         * level += 1;
         */
        code += "programme ";
        // node.getIdentifier().accept(this);
        node.getDeclaration().accept(this);
        code += "\ndebutprg";
        level += 1;
        node.getInstructions().accept(this);
        level -= 1;
        code += "\nfinprg";

        /*
         * TDS.getInstance().sortieBloc();
         * level -= 1;
         */
        return null;
    }

    public Object visit(DeclarationVariable node) throws Exception {
        /*
         * Symbole sym = TDS.getInstance().identifier(new
         * Entree(node.getIdentifier().getNom()));
         */
        code += node.getType().GetLabel() + " ";

        ArrayList<Idf> idfs = node.getIdentifiants();
        for (int i = 0; i < idfs.size(); i++) {
            idfs.get(i).accept(this);
            if (i < idfs.size() - 1)
                code += ",";
        }
        code += ";";
        return null;
    }

    public Object visit(Diff node) throws Exception {
        node.GetGauche().accept(this);
        code += " <> ";
        node.GetDroite().accept(this);
        return null;
    }

    public Object visit(Division node) throws Exception {
        node.GetGauche().accept(this);
        code += " / ";
        node.GetDroite().accept(this);
        return null;
    }

    public Object visit(Ecrire node) throws Exception {
        code += "ecrire ";
        node.GetSource().accept(this);
        code += ";";
        return null;
    }

    public Object visit(Egal node) throws Exception {
        node.GetGauche().accept(this);
        code += " == ";
        node.GetDroite().accept(this);
        return null;
    }

    public Object visit(Et node) throws Exception {
        node.GetGauche().accept(this);
        code += " et ";
        node.GetDroite().accept(this);
        return null;
    }

    public Object visit(Faux node) throws Exception {
        code += "faux";
        return null;
    }

    public Object visit(Idf node) throws Exception {
        code += node.getNom();
        return null;
    }

    public Object visit(InfEgal node) throws Exception {
        node.GetGauche().accept(this);
        code += " <= ";
        node.GetDroite().accept(this);
        return null;
    }

    public Object visit(Inferieur node) throws Exception {
        node.GetGauche().accept(this);
        code += " < ";
        node.GetDroite().accept(this);
        return null;
    }

    public Object visit(Lire node) throws Exception {
        code += "lire ";
        node.GetDestination().accept(this);
        code += ";";
        return null;
    }

    public Object visit(Moins node) throws Exception {
        code += "-";
        node.GetOperande().accept(this);
        return null;
    }

    public Object visit(Nombre node) throws Exception {
        code += Integer.toString(node.getValeur());
        return null;
    }

    public Object visit(Non node) throws Exception {
        code += "non ";
        node.GetOperande().accept(this);
        return null;
    }

    public Object visit(Ou node) throws Exception {
        node.GetGauche().accept(this);
        code += " ou ";
        node.GetDroite().accept(this);
        return null;
    }

    public Object visit(Parentheses node) throws Exception {
        code += "(";
        node.getExpression().accept(this);
        code += ")";
        return null;
    }

    public Object visit(Pour node) throws Exception {
        code += "pour ";
        node.getIteratorName().accept(this);
        code += " allantde ";
        node.getFrom().accept(this);
        code += " a ";
        node.getTo().accept(this);
        code += " faire";
        level += 1;
        node.getInstructions().accept(this);
        level -= 1;
        code += "\n";
        addTabulation();
        code += "finpour";
        return null;
    }

    public Object visit(Produit node) throws Exception {
        node.GetGauche().accept(this);
        code += " * ";
        node.GetDroite().accept(this);
        return null;
    }

    /*
     * public Object visit(Retour node) throws Exception{
     * code += "retourne ";
     * node.GetSource().accept(this);
     * code += ";";
     * return null;
     * }
     */
    public Object visit(Soustraction node) throws Exception {
        node.GetGauche().accept(this);
        code += " - ";
        node.GetDroite().accept(this);
        return null;
    }

    public Object visit(SupEgal node) throws Exception {
        node.GetGauche().accept(this);
        code += " >= ";
        node.GetDroite().accept(this);
        return null;
    }

    public Object visit(Superieur node) throws Exception {
        node.GetGauche().accept(this);
        code += " > ";
        node.GetDroite().accept(this);
        return null;
    }

    public Object visit(Tantque node) throws Exception {
        code += "tantque ";
        node.getCondition().accept(this);
        code += " faire";
        level += 1;
        node.getInstructions().accept(this);
        level -= 1;
        code += "\n";
        addTabulation();
        code += "fintantque";
        return null;
    }

    public Object visit(Tilda node) throws Exception {
        code += "~";
        node.GetOperande().accept(this);
        return null;
    }

    /*
     * public Object visit(Vrai node) throws Exception{
     * code += "vrai";
     * return null;
     * }
     */
    public Object visit(Vrai node) throws Exception {
        code += "vrai";
        return null;
    }

    public String getCode() {
        return code;
    }
}
