package hepial.ast;

import java.util.HashMap;
import java.util.Map;

import hepial.TableDesSymboles;
import hepial.ast.expression.*;
import hepial.ast.expression.binaire.arithmetique.*;
import hepial.ast.expression.binaire.relation.*;
import hepial.ast.expression.unaire.*;
import hepial.ast.instruction.*;

public class ByteCodeGenerator implements ASTVisitor {
    private int stackLimit = 20000;
    private int localsLimit = 1;

    private int labelId = 0;
    private boolean indent = false;
    private boolean pretty = false;

    private final Map<String, Integer> variableTable = new HashMap<>();

    public ByteCodeGenerator() {
        // built a table of variables with their relative jasmin var index
        int index = 0;
        for (String entry : TableDesSymboles.variables.keySet()) {
            variableTable.put(entry, index++);
        }
    }

    public void setPretty(boolean pretty) {
        this.pretty = pretty;
    }

    /**
     * Format to jasmin
     * 
     * @param jasmin
     * @param args
     * @return
     */
    private String f(String jasmin, java.lang.Object... args) {
        String line = "";

        if (indent && pretty)
            line += "	";

        if (args.length > 0) {
            line += String.format(jasmin, args);
        } else {
            line += jasmin;
        }

        return line;
    }

    /**
     * Add a new line to a string
     * 
     * @param jasmin
     * @param args
     * @return
     */
    private String ln(String line) {
        return String.format("%s%s", line, System.getProperty("line.separator"));
    }

    /**
     * Add a comment to a string
     * 
     * @param jasmin
     * @param args
     * @return
     */
    private String c(String line, String comment) {
        if (!pretty)
            return line;
        return String.format("%s ; %s", line, comment);
    }

    private String nextLabel() {
        return String.format("label_%d", labelId++);
    }

    private String buildCmp(Relation node, String instruction) throws Exception {
        String code = "";
        code += (String) node.GetGauche().accept(this);
        code += (String) node.GetDroite().accept(this);
        String thenLabel = nextLabel();
        String finallyLabel = nextLabel();
        code += ln(f("if_icmp%s %s", instruction, thenLabel));
        code += ln(f("iconst_0"));
        code += ln(f("goto %s", finallyLabel));
        code += ln(f("%s:", thenLabel));
        code += ln(f("iconst_1"));
        code += ln(f("%s:", finallyLabel));
        return code;
    }

    @Override
    public Object visit(Addition node) throws Exception {
        String code = "";
        code += (String) node.GetGauche().accept(this);
        code += (String) node.GetDroite().accept(this);
        code += ln(f("iadd"));
        return code;
    }

    @Override
    public Object visit(Affectation node) throws Exception {
        Idf idf = node.GetDestination();
        int index = variableTable.get(idf.GetNom());

        String code = "";
        code += (String) node.GetSource().accept(this);

        String line = f("istore %d", index);
        code += ln(c(line, idf.GetNom()));
        return code;
    }

    @Override
    public Object visit(Bloc node) throws Exception {
        String code = "";

        for (Instruction i : node.getInstructions()) {
            code += (String) i.accept(this);
        }

        return code;
    }

    @Override
    public Object visit(Chaine node) throws Exception {
        return ln(f("ldc %s", node.getValeur()));
    }

    @Override
    public Object visit(Condition node) throws Exception {
        if (node.GetElseInstructions().isPresent()) {
            String elseLabel = nextLabel();
            String finallyLabel = nextLabel();
            String code = (String) node.GetCondition().accept(this);
            code += ln(f("if_icmpne %s", elseLabel));
            code += node.GetThenInstructions().accept(this);
            code += ln(f("goto %s", finallyLabel));
            code += ln(f("%s:", elseLabel));
            code += node.GetElseInstructions().get().accept(this);
            code += ln(f("%s:", finallyLabel));
            return code;
        } else {
            String code = (String) node.GetCondition().accept(this);
            String finallyLabel = nextLabel();
            code += ln(f("if_icmpne %s", finallyLabel));
            code += node.GetThenInstructions().accept(this);
            code += ln(f("%s:", finallyLabel));
            return code;
        }
    }

    @Override
    public Object visit(DeclarationConstant node) throws Exception {
        // Don't actually write code yet, we will just return the
        // constant value later whenever the constant is accessed
        return "";
    }

    @Override
    public Object visit(DeclarationProgramme node) throws Exception {
        String code = "";
        code += ln(f(".class public %s", node.getIdentifier().GetNom()));
        code += ln(f(".super java/lang/Object"));
        code += ln(f(".method public static main([Ljava/lang/String;)V"));
        indent = true;

        String variables = (String) node.getDeclaration().accept(this);
        String main = (String) node.getInstructions().accept(this);

        code += ln(f(".limit stack %s", stackLimit));
        code += ln(f(".limit locals %s", localsLimit));
        code += variables;
        code += main;
        code += ln(f("exit_label:")); // in case we need early return anywher)e
        code += ln(f("return"));

        indent = false;
        code += ln(f(".end method"));
        return code;
    }

    @Override
    public Object visit(DeclarationVariable node) throws Exception {
        String code = "";

        for (Idf idf : node.getIdentifiants()) {
            localsLimit += 1;

            String type;

            switch (idf.GetType()) {
                case Entier:
                    type = "I";
                    break;
                case Booleen:
                    type = "Z";
                    break;
                default:
                    throw new Exception(
                            String.format("le type %s ne peut pas être utilisé avec l'instruction DeclarationVariable",
                                    node.getType().GetLabel()));
            }

            String name = idf.GetNom();
            int index = variableTable.get(name);
            code += ln(f(".var %d is %s %s", index, name, type));
            code += ln(f("ldc 0"));
            code += ln(f("istore %d", index));
        }

        return code;
    }

    @Override
    public Object visit(Diff node) throws Exception {
        return buildCmp(node, "ne");
    }

    @Override
    public Object visit(Division node) throws Exception {
        String code = "";
        code += (String) node.GetGauche().accept(this);
        code += (String) node.GetDroite().accept(this);
        code += ln(f("idiv"));
        return code;
    }

    @Override
    public Object visit(Ecrire node) throws Exception {
        String code = "";
        code += "";
        code += ln(f("getstatic java/lang/System/out Ljava/io/PrintStream;"));

        String value;
        String type;

        switch (node.GetSource().GetType()) {
            case Entier:
                value = (String) node.GetSource().accept(this);
                type = "I";
                break;
            case Booleen:
                value = (String) node.GetSource().accept(this);
                String elseLabel = nextLabel();
                String finallyLabel = nextLabel();
                value += ln(f("ifeq %s", elseLabel));
                value += ln(f("ldc \"vrai\""));
                value += ln(f("goto %s", finallyLabel));
                value += ln(f("%s:", elseLabel));
                value += ln(f("ldc \"faux\""));
                value += ln(f("%s:", finallyLabel));
                type = "Ljava/lang/String;";
                break;
            case Chaine:
                value = (String) node.GetSource().accept(this);
                type = "Ljava/lang/String;";
                break;
            default:
                throw new Exception("le type %s ne peut pas être utilisé avec l'instruction Ecrire");
        }

        code += value;
        code += ln(f("invokevirtual java/io/PrintStream/println(%s)V", type));
        return code;
    }

    @Override
    public Object visit(Egal node) throws Exception {
        return buildCmp(node, "eq");
    }

    @Override
    public Object visit(Et node) throws Exception {
        String code = "";
        code += (String) node.GetGauche().accept(this);
        code += (String) node.GetDroite().accept(this);
        code += ln(f("iand"));
        return code;
    }

    @Override
    public Object visit(Faux node) throws Exception {
        return ln(f("ldc 0"));
    }

    @Override
    public Object visit(Idf node) throws Exception {
        // assuming trying to access
        int index = variableTable.get(node.GetNom());
        return ln(c(f("iload %d", index), node.GetNom()));
    }

    @Override
    public Object visit(InfEgal node) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Object visit(Inferieur node) throws Exception {
        return buildCmp(node, "lt");
    }

    @Override
    public Object visit(Lire node) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Object visit(Moins node) throws Exception {
        String code = (String) node.GetOperande().accept(this);
        code += ln(f("ineg"));
        return code;
    }

    @Override
    public Object visit(Nombre node) throws Exception {
        return ln(f("ldc %d", node.getValeur()));
    }

    @Override
    public Object visit(Non node) throws Exception {
        String code = "";
        code += (String) node.GetOperande().accept(this);
        String thenLabel = nextLabel();
        String finallyLabel = nextLabel();
        code += ln(f("ifeq %s", thenLabel));
        code += ln(f("iconst_0"));
        code += ln(f("goto %s", finallyLabel));
        code += ln(f("%s:", thenLabel));
        code += ln(f("iconst_1"));
        code += ln(f("%s:", finallyLabel));
        return code;
    }

    @Override
    public Object visit(Ou node) throws Exception {
        String code = "";
        code += node.GetGauche().accept(this);
        code += node.GetDroite().accept(this);
        code += ln(f("ior"));
        return code;
    }

    @Override
    public Object visit(Parentheses node) throws Exception {
        return node.getExpression().accept(this);
    }

    @Override
    public Object visit(Pour node) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Object visit(Produit node) throws Exception {
        String code = "";
        code += (String) node.GetGauche().accept(this);
        code += (String) node.GetDroite().accept(this);
        code += ln(f("imul"));
        return code;
    }

    @Override
    public Object visit(Soustraction node) throws Exception {
        String code = "";
        code += (String) node.GetGauche().accept(this);
        code += (String) node.GetDroite().accept(this);
        code += ln(f("isub"));
        return code;
    }

    @Override
    public Object visit(SupEgal node) throws Exception {
        return buildCmp(node, "ge");
    }

    @Override
    public Object visit(Superieur node) throws Exception {
        return buildCmp(node, "gt");
    }

    @Override
    public Object visit(Tantque node) throws Exception {
        String code = "";
        String loopLabel = nextLabel();
        String endLabel = nextLabel();

        code += ln(f("%s: ; loop", loopLabel));
        code += node.GetCondition().accept(this);
        code += ln(f("ldc 1"));
        code += ln(f("if_icmpne %s", endLabel));
        code += node.GetInstructions().accept(this);
        code += ln(f("goto %s", loopLabel));
        code += ln(f("%s:", endLabel));
        return code;
    }

    @Override
    public Object visit(Tilda node) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Object visit(Vrai node) throws Exception {
        return ln(f("ldc 1"));
    }

}
