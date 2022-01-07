package main.ast;

import main.ast.expression.Chaine;
import main.ast.expression.Faux;
import main.ast.expression.Idf;
import main.ast.expression.Nombre;
import main.ast.expression.Parentheses;
import main.ast.expression.Vrai;
import main.ast.expression.binaire.arithmetique.Addition;
import main.ast.expression.binaire.arithmetique.Division;
import main.ast.expression.binaire.arithmetique.Produit;
import main.ast.expression.binaire.arithmetique.Soustraction;
import main.ast.expression.binaire.relation.Diff;
import main.ast.expression.binaire.relation.Egal;
import main.ast.expression.binaire.relation.Et;
import main.ast.expression.binaire.relation.InfEgal;
import main.ast.expression.binaire.relation.Inferieur;
import main.ast.expression.binaire.relation.Ou;
import main.ast.expression.binaire.relation.SupEgal;
import main.ast.expression.binaire.relation.Superieur;
import main.ast.expression.unaire.Moins;
import main.ast.expression.unaire.Non;
import main.ast.expression.unaire.Tilda;
import main.ast.instruction.Affectation;
import main.ast.instruction.Bloc;
import main.ast.instruction.Condition;
import main.ast.instruction.DeclarationConstant;
import main.ast.instruction.DeclarationProgramme;
import main.ast.instruction.DeclarationVariable;
import main.ast.instruction.Ecrire;
import main.ast.instruction.Lire;
import main.ast.instruction.Pour;
import main.ast.instruction.Tantque;

/*
 * AST visiteur interface
 */
public interface ASTVisitor {
    Object visit(Addition node) throws Exception;

    Object visit(Affectation node) throws Exception;

    Object visit(Bloc node) throws Exception;

    Object visit(Chaine node) throws Exception;

    Object visit(Condition node) throws Exception;

    Object visit(DeclarationConstant node) throws Exception;

    Object visit(DeclarationProgramme node) throws Exception;

    Object visit(DeclarationVariable node) throws Exception;

    Object visit(Diff node) throws Exception;

    Object visit(Division node) throws Exception;

    Object visit(Ecrire node) throws Exception;

    Object visit(Egal node) throws Exception;

    Object visit(Et node) throws Exception;

    Object visit(Faux node) throws Exception;

    Object visit(Idf node) throws Exception;

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
