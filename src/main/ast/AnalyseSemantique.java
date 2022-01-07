package main.ast;

import main.Type;
import main.TableDesSymboles;
import main.exceptions.*;
import main.ast.expression.*;
import main.ast.expression.binaire.*;
import main.ast.expression.binaire.arithmetique.*;
import main.ast.expression.binaire.relation.*;
import main.ast.expression.unaire.*;
import main.ast.instruction.*;
import java.util.Arrays;
import java.util.AbstractMap.SimpleEntry;

public class AnalyseSemantique implements ASTVisitor {
  private static void ForceBinaireType(Binaire node, Type... types) throws Exception {
    String message = "l'opération binaire %s ne supporte pas le type %s";

    if (!Arrays.asList(types).contains(node.GetGauche().GetType())) {
      throw new SemantiqueException(node.GetGauche(),
          String.format(message, node.getClass().getName(), node.GetGauche().GetType().GetLabel()));
    }

    if (!Arrays.asList(types).contains(node.GetDroite().GetType())) {
      throw new SemantiqueException(node.GetDroite(),
          String.format(message, node.getClass().getName(), node.GetDroite().GetType().GetLabel()));
    }
  }

  private static void ForceUnaireType(Unaire node, Type... types) throws Exception {
    String message = "l'opération unaire %s ne supporte pas le type %s";

    if (!Arrays.asList(types).contains(node.GetOperande().GetType())) {
      throw new SemantiqueException(node.GetOperande(),
          String.format(message, node.getClass().getName(), node.GetOperande().GetType().GetLabel()));
    }
  }

  private static void CheckAvailablility(Idf idf) throws Exception {
    if (TableDesSymboles.Exists(idf.getNom())) {
      throw new SemantiqueException(idf, String.format("identifient avec le nom %s existe déjà", idf.getNom()));
    }
  }

  @Override
  public Object visit(Addition node) throws Exception {
    ForceBinaireType(node, Type.Entier);
    return Idf.all;
  }

  public Object visit(Affectation node) throws Exception {
    Idf destination = node.GetDestination();
    Expression source = node.GetSource();

    destination.accept(this);
    source.accept(this);

    if (destination.GetType() != source.GetType()) {
      throw new SemantiqueException(node.GetDestination(),
          String.format("impossible d'assigner %s à la variable de type %s", source.GetType().GetLabel(),
              destination.GetType().GetLabel()));
    }

    // mark the variable as assigned
    TableDesSymboles.variables.get(destination.getNom()).setValue(true);
    return null;
  }

  public Object visit(Bloc node) throws Exception {
    for (Instruction i : node.getInstructions()) {
      i.accept(this);
    }

    return null;
  }

  @Override
  public Object visit(Chaine node) {
    // nothing to semantically check here
    return null;
  }

  @Override
  public Object visit(Condition node) {
    throw new UnsupportedOperationException();
  }

  public Object visit(DeclarationConstant node) throws Exception {
    Idf idf = node.getIdentifier();
    CheckAvailablility(idf);

    Type type = node.getType();
    Expression value = node.getExpression();

    if (type != value.GetType()) {
      throw new SemantiqueException(idf, String
          .format("impossible d'assigner la valeur type %s à la constante de type %s",
              value.GetType().GetLabel(),
              type.GetLabel()));
    }

    TableDesSymboles.constants.put(idf.getNom(), type);

    idf.accept(this);
    node.getExpression().accept(this);

    return null;
  }

  public Object visit(DeclarationProgramme node) throws Exception {
    Idf idf = node.getIdentifier();
    // CheckAvailablility(idf);

    TableDesSymboles.SetProgramme(idf.getNom());
    node.getIdentifier().accept(this);
    node.getDeclaration().accept(this);
    node.getInstructions().accept(this);
    return null;
  }

  public Object visit(DeclarationVariable node) throws Exception {
    Type t = node.getType();

    for (Idf idf : node.getIdentifiants()) {
      CheckAvailablility(idf);
      TableDesSymboles.variables.put(idf.getNom(), new SimpleEntry<>(t, false));
    }

    return null;
  }

  @Override
  public Object visit(Diff node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Division node) throws Exception {
    ForceBinaireType(node, Type.Entier);

    // Also checks for literal division by 0
    // other divisions by 0 will be thrown at runtime
    if (node.GetDroite().getClass() == Nombre.class && ((Nombre) node.GetDroite()).getValeur() == 0) {
      throw new SemantiqueException(node.GetDroite(), String.format("division par la valeur litérale 0"));
    }

    return null;
  }

  @Override
  public Object visit(Ecrire node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Egal node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Et node) throws Exception {
    ForceBinaireType(node, Type.Booleen);
    return null;
  }

  @Override
  public Object visit(Faux node) {
    throw new UnsupportedOperationException();
  }

  public Object visit(Idf node) throws Exception {
    if (!TableDesSymboles.Exists(node.getNom())) {
      throw new SemantiqueException(node, String.format("l'indetifient %s n'existe pas", node.getNom()));
    }

    SimpleEntry<Type, Boolean> variable = TableDesSymboles.variables.get(node.getNom());
    if (variable != null && variable.getValue() == false) {
      throw new SemantiqueException(node, String.format("la variable %s n'est pas initialisée", node.getNom()));
    }

    return null;
  }

  @Override
  public Object visit(InfEgal node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Inferieur node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Lire node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Moins node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Nombre node) {
    // nothing to semantically check here
    return null;
  }

  @Override
  public Object visit(Non node) throws Exception {
    ForceUnaireType(node, Type.Booleen);
    return null;
  }

  @Override
  public Object visit(Ou node) throws Exception {
    ForceBinaireType(node, Type.Booleen);
    return null;
  }

  @Override
  public Object visit(Parentheses node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Pour node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Produit node) throws Exception {
    ForceBinaireType(node, Type.Entier);
    return null;
  }

  @Override
  public Object visit(Soustraction node) throws Exception {
    ForceBinaireType(node, Type.Entier);
    return null;
  }

  @Override
  public Object visit(SupEgal node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Superieur node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Tantque node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Tilda node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Vrai node) {
    throw new UnsupportedOperationException();
  }

}
