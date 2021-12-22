/*
 * AST visiteur interface
 */

public interface ASTVisitor {
    Object visit(Addition node) throws Exception;

    Object visit(Affectation node) throws Exception;

    // Object visit(Appel node) throws Exception;
    Object visit(Bloc node) throws Exception;

    Object visit(Chaine node) throws Exception;

    Object visit(Condition node) throws Exception;

    Object visit(DeclarationConstant node) throws Exception;

    // Object visit(DeclarationFonction node) throws Exception;
    Object visit(DeclarationProgramme node) throws Exception;

    Object visit(DeclarationVariable node) throws Exception;

    Object visit(Diff node) throws Exception;

    Object visit(Division node) throws Exception;

    Object visit(Ecrire node) throws Exception;

    Object visit(Egal node) throws Exception;

    Object visit(Et node) throws Exception;

    Object visit(Faux node) throws Exception;

    Object visit(Idf node) throws Exception;

    // Object visit(Indice node) throws Exception;
    Object visit(InfEgal node) throws Exception;

    Object visit(Inferieur node) throws Exception;

    Object visit(Lire node) throws Exception;

    Object visit(Moins node) throws Exception;

    Object visit(Nombre node) throws Exception;

    Object visit(Non node) throws Exception;

    Object visit(Ou node) throws Exception;

    Object visit(Parentheses node) throws Exception;

    Object visit(Pour node) throws Exception;

    Object visit(Produit node) throws Exception;

    Object visit(Soustraction node) throws Exception;

    Object visit(SupEgal node) throws Exception;

    Object visit(Superieur node) throws Exception;

    Object visit(Tantque node) throws Exception;

    Object visit(Tilda node) throws Exception;

    Object visit(Vrai node) throws Exception;
}
