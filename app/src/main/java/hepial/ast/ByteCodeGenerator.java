package hepial.ast;

import java.util.HashMap;
import java.util.Map;

import hepial.ast.expression.*;
import hepial.ast.expression.binaire.arithmetique.*;
import hepial.ast.expression.binaire.relation.*;
import hepial.ast.expression.unaire.*;
import hepial.ast.instruction.*;

public class ByteCodeGenerator implements ASTVisitor {
    private int stackLimit = 20000;
    private int localsLimit = 1;
    private int variableTableIndex = 0;
    private int labelIndex = 0;
    private boolean indent = false;
    private boolean pretty = false;

    // Index table used by jasmin in the .var instruction
    // called variables but also contains constantes
    private final Map<String, Integer> variableTable = new HashMap<>();

    /**
     * Set pretty print to add intend and comments to the byte code
     * 
     * @param pretty
     */
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

    /**
     * Generates a unique label name
     * 
     * @return
     */
    private String nextLabel() {
        return String.format("label_%d", labelIndex++);
    }

    private String buildCmp(Relation node, String instruction) throws Exception {
        String code = "";
        String thenLabel = nextLabel();
        String finallyLabel = nextLabel();
        code += (String) node.GetGauche().accept(this);
        code += (String) node.GetDroite().accept(this);
        code += ln(f("if_icmp%s %s", instruction, thenLabel));
        code += ln(f("iconst_0"));
        code += ln(f("goto %s", finallyLabel));
        code += ln(f("%s:", thenLabel));
        code += ln(f("iconst_1"));
        code += ln(f("%s:", finallyLabel));
        return code;
    }

    private String buildVar(Idf idf, String source) throws Exception {
        String code = "";
        String type;

        // save to the table for access in other nodes
        localsLimit += 1;
        variableTable.put(idf.GetNom(), variableTableIndex++);

        switch (idf.GetType()) {
            case Entier:
                type = "I";
                break;
            case Booleen:
                type = "Z";
                break;
            default:
                throw new UnsupportedOperationException();
        }

        String name = idf.GetNom();
        int index = variableTable.get(name);
        code += ln(f(".var %d is %s %s", index, name, type));
        code += source;
        code += ln(f("istore %d", index));
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
        String code = (String) node.GetCondition().accept(this);

        // compute the then and else code before getting the labels for the jumps
        String thenCode = (String) node.GetThenInstructions().accept(this);
        String elseCode = node.GetElseInstructions().isPresent()
                ? (String) node.GetElseInstructions().get().accept(this)
                : "";

        // actual if then logic
        String elseLabel = nextLabel();
        String finallyLabel = nextLabel();
        code += ln(f("ifeq %s", elseLabel));
        code += thenCode;
        code += ln(f("goto %s", finallyLabel));
        code += ln(f("%s:", elseLabel));
        code += elseCode;
        code += ln(f("%s:", finallyLabel));
        return code;
    }

    @Override
    public Object visit(DeclarationConstant node) throws Exception {
        // we treat constantes like variables, we already ensured constantes won't ever
        // be assigned in the semantic analysis
        return buildVar(node.getIdentifier(), (String) node.getExpression().accept(this));
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
        code += ln(f("exit_label:")); // in case we need early return anywhere
        code += ln(f("return"));

        indent = false;
        code += ln(f(".end method"));
        return code;
    }

    @Override
    public Object visit(DeclarationVariable node) throws Exception {
        String code = "";

        for (Idf idf : node.getIdentifiants()) {
            code += buildVar(idf, ln(f("ldc 0")));
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
        int index = variableTable.get(node.GetNom());
        return ln(c(f("iload %d", index), node.GetNom()));
    }

    @Override
    public Object visit(InfEgal node) throws Exception {
        return buildCmp(node, "le");
    }

    @Override
    public Object visit(Inferieur node) throws Exception {
        return buildCmp(node, "lt");
    }

    @Override
    public Object visit(Lire node) throws Exception {
        Idf idf = node.GetDestination();
        int index = variableTable.get(idf.GetNom());
        String code = "";
        code += ln(f("new java/util/Scanner"));
        code += ln(f("dup"));
        code += ln(f("getstatic java/lang/System/in Ljava/io/InputStream;"));
        code += ln(f("invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V"));
        code += ln(f("invokevirtual java/util/Scanner/nextInt()I"));
        code += ln(c(f("istore %s", index), idf.GetNom()));
        return code;
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
        Idf idf = node.GetIteratorName();
        int index = variableTable.get(idf.GetNom());

        String code = "";
        code += node.GetFrom().accept(this);
        code += ln(c(f("istore %d", index), idf.GetNom()));

        String loopLabel = nextLabel();
        String exitLabel = nextLabel();

        // start of the loop
        code += ln(c(f("%s:", loopLabel), "loop"));

        // condition to leave the loop
        code += node.GetTo().accept(this);
        code += node.GetIteratorName().accept(this);
        code += ln(f("if_icmplt %s", exitLabel));

        // loop instructions
        code += node.GetInstructions().accept(this);

        // load the loop variable again and increment
        code += node.GetIteratorName().accept(this);
        code += ln(f("ldc 1"));
        code += ln(f("iadd"));
        code += ln(c(f("istore %d", index), idf.GetNom()));

        // jump back to the loop start
        code += ln(f("goto %s", loopLabel));

        // end of the loop
        code += ln(c(f("%s:", exitLabel), "exit"));
        return code;
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

        code += ln(c(f("%s:", loopLabel), "loop"));
        code += node.GetCondition().accept(this);
        code += ln(f("ifeq %s", endLabel));
        code += node.GetInstructions().accept(this);
        code += ln(f("goto %s", loopLabel));
        code += ln(f("%s:", endLabel));
        return code;
    }

    @Override
    public Object visit(Tilda node) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Vrai node) throws Exception {
        return ln(f("ldc 1"));
    }

}
