/*
 * AST visiteur interface
 */

public interface ASTVisitor {
    Object visit(Addition node);

    Object visit(Affectation node);

    // Object visit(Appel node);
    Object visit(Bloc node);

    Object visit(Chaine node);

    Object visit(Condition node);

    Object visit(DeclarationConstant node);

    // Object visit(DeclarationFonction node);
    Object visit(DeclarationProgramme node);

    Object visit(DeclarationVariable node);

    Object visit(Diff node);

    Object visit(Division node);

    Object visit(Ecrire node);

    Object visit(Egal node);

    Object visit(Et node);

    Object visit(Faux node);

    Object visit(Idf node);

    // Object visit(Indice node);
    Object visit(InfEgal node);

    Object visit(Inferieur node);

    Object visit(Lire node);

    Object visit(Moins node);

    Object visit(Nombre node);

    Object visit(Non node);

    Object visit(Ou node);

    // Object visit(Parentheses node);
    Object visit(Pour node);

    Object visit(Produit node);

    Object visit(Soustraction node);

    Object visit(SupEgal node);

    Object visit(Superieur node);

    Object visit(Tantque node);

    Object visit(Tilda node);

    Object visit(Vrai node);
}
