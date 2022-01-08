package hepial.ast;

import hepial.ast.expression.Chaine;
import hepial.ast.expression.Faux;
import hepial.ast.expression.Idf;
import hepial.ast.expression.Nombre;
import hepial.ast.expression.Parentheses;
import hepial.ast.expression.Vrai;
import hepial.ast.expression.binaire.arithmetique.Addition;
import hepial.ast.expression.binaire.arithmetique.Division;
import hepial.ast.expression.binaire.arithmetique.Produit;
import hepial.ast.expression.binaire.arithmetique.Soustraction;
import hepial.ast.expression.binaire.relation.Diff;
import hepial.ast.expression.binaire.relation.Egal;
import hepial.ast.expression.binaire.relation.Et;
import hepial.ast.expression.binaire.relation.InfEgal;
import hepial.ast.expression.binaire.relation.Inferieur;
import hepial.ast.expression.binaire.relation.Ou;
import hepial.ast.expression.binaire.relation.SupEgal;
import hepial.ast.expression.binaire.relation.Superieur;
import hepial.ast.expression.unaire.Moins;
import hepial.ast.expression.unaire.Non;
import hepial.ast.expression.unaire.Tilda;
import hepial.ast.instruction.Affectation;
import hepial.ast.instruction.Bloc;
import hepial.ast.instruction.Condition;
import hepial.ast.instruction.DeclarationConstant;
import hepial.ast.instruction.DeclarationProgramme;
import hepial.ast.instruction.DeclarationVariable;
import hepial.ast.instruction.Ecrire;
import hepial.ast.instruction.Lire;
import hepial.ast.instruction.Pour;
import hepial.ast.instruction.Tantque;

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
