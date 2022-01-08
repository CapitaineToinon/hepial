package hepial.ast;

import hepial.Type;
import hepial.TableDesSymboles;
import hepial.ast.exceptions.*;
import hepial.ast.expression.*;
import hepial.ast.expression.binaire.*;
import hepial.ast.expression.binaire.arithmetique.*;
import hepial.ast.expression.binaire.relation.*;
import hepial.ast.expression.unaire.*;
import hepial.ast.instruction.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.AbstractMap.SimpleEntry;

public class AnalyseSemantique implements ASTVisitor {

  /**
   * Vérifie si les deux opérateurs d'une expression binaire ont des Type autorisé
   * 
   * @param node
   * @param types
   * @throws IncompatibleTypeException
   */
  private static void ForceBinaireType(Binaire node, Type... types) throws IncompatibleTypeException {
    String message = "l'opération binaire %s ne supporte pas le type %s";

    if (!Arrays.asList(types).contains(node.GetGauche().GetType())) {
      throw new IncompatibleTypeException(node.GetGauche(),
          String.format(message, node.getClass().getSimpleName(), node.GetGauche().GetType().GetLabel()));
    }

    if (!Arrays.asList(types).contains(node.GetDroite().GetType())) {
      throw new IncompatibleTypeException(node.GetDroite(),
          String.format(message, node.getClass().getSimpleName(), node.GetDroite().GetType().GetLabel()));
    }
  }

  /**
   * Vérifie que les deux opérateurs d'une expression binaire soit du même type
   * 
   * @param node
   * @throws IncompatibleTypeException
   */
  public static void ForceBinaireEqualType(Binaire node) throws IncompatibleTypeException {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    if (gauche.GetType() != droite.GetType()) {
      throw new IncompatibleTypeException(node,
          String.format("impossible de comparer %s avec %s", gauche.GetType().GetLabel(), droite.GetType().GetLabel()));
    }
  }

  /**
   * Vérifie que l'opérateur d'une expression unaire ai un type autorisé
   * 
   * @param node
   * @param types
   * @throws IncompatibleTypeException
   */
  private static void ForceUnaireType(Unaire node, Type... types) throws IncompatibleTypeException {
    String message = "l'opération unaire %s ne supporte pas le type %s";

    if (!Arrays.asList(types).contains(node.GetOperande().GetType())) {
      throw new IncompatibleTypeException(node.GetOperande(),
          String.format(message, node.getClass().getSimpleName(), node.GetOperande().GetType().GetLabel()));
    }
  }

  /**
   * Verifie si un nom pour un identifient est disponible
   * 
   * @param idf
   * @throws Exception
   */
  private static void CheckAvailability(Idf idf) throws Exception {
    if (TableDesSymboles.Exists(idf.GetNom())) {
      throw new AlreadyDefinedException(idf, String.format("identifient avec le nom %s existe déjà", idf.GetNom()));
    }
  }

  public Object visit(Affectation node) throws Exception {
    Idf destination = node.GetDestination();
    Expression source = node.GetSource();

    try {
      destination.accept(this);
    } catch (UnassignedException e) {
      // that's actually ok, prevent from throwing
    }

    source.accept(this);

    if (TableDesSymboles.constants.containsKey(destination.GetNom())) {
      throw new AssignConstantException(node.GetDestination(),
          String.format("impossible à la constante %s", destination.GetNom()));
    }

    if (destination.GetType() != source.GetType()) {
      throw new IncompatibleTypeException(node.GetDestination(),
          String.format("impossible d'assigner %s à la variable de type %s", source.GetType().GetLabel(),
              destination.GetType().GetLabel()));
    }

    // mark the variable as assigned
    TableDesSymboles.variables.get(destination.GetNom()).setValue(true);
    return null;
  }

  public Object visit(Bloc node) throws Exception {
    for (Instruction i : node.getInstructions()) {
      i.accept(this);
    }

    return null;
  }

  public Object visit(DeclarationProgramme node) throws Exception {
    Idf idf = node.getIdentifier();
    CheckAvailability(idf);

    TableDesSymboles.SetProgramme(idf.GetNom());
    node.getIdentifier().accept(this);
    node.getDeclaration().accept(this);
    node.getInstructions().accept(this);
    return null;
  }

  public Object visit(DeclarationVariable node) throws Exception {
    Type t = node.getType();

    for (Idf idf : node.getIdentifiants()) {
      // not calling idf.accept because the errors it will throw are expected
      // in this situation
      CheckAvailability(idf);
      // mark the variable as unassigned
      TableDesSymboles.variables.put(idf.GetNom(), new SimpleEntry<>(t, false));
    }

    return null;
  }

  public Object visit(DeclarationConstant node) throws Exception {
    Idf idf = node.getIdentifier();
    // not calling idf.accept because the errors it will throw are expected
    // in this situation
    CheckAvailability(idf);

    Type type = node.getType();
    Expression exp = node.getExpression();

    exp.accept(this);

    if (type != exp.GetType()) {
      throw new SemantiqueException(idf, String
          .format("impossible d'assigner la valeur type %s à la constante de type %s",
              exp.GetType().GetLabel(),
              type.GetLabel()));
    }

    TableDesSymboles.constants.put(idf.GetNom(), type);

    idf.accept(this);
    node.getExpression().accept(this);

    return null;
  }

  @Override
  public Object visit(Addition node) throws Exception {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    gauche.accept(this);
    droite.accept(this);

    ForceBinaireType(node, Type.Entier);
    return null;
  }

  @Override
  public Object visit(Produit node) throws Exception {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    gauche.accept(this);
    droite.accept(this);

    ForceBinaireType(node, Type.Entier);
    return null;
  }

  @Override
  public Object visit(Soustraction node) throws Exception {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    gauche.accept(this);
    droite.accept(this);

    ForceBinaireType(node, Type.Entier);
    return null;
  }

  @Override
  public Object visit(Division node) throws Exception {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    gauche.accept(this);
    droite.accept(this);

    ForceBinaireType(node, Type.Entier);

    // Also checks for literal division by 0
    // other divisions by 0 will be thrown at runtime
    if (node.GetDroite().getClass() == Nombre.class && ((Nombre) node.GetDroite()).getValeur() == 0) {
      throw new DivideByZeroException(node.GetDroite(), String.format("division par la valeur litérale 0"));
    }

    return null;
  }

  @Override
  public Object visit(Et node) throws Exception {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    gauche.accept(this);
    droite.accept(this);

    ForceBinaireType(node, Type.Booleen);
    return null;
  }

  @Override
  public Object visit(Ou node) throws Exception {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    gauche.accept(this);
    droite.accept(this);

    ForceBinaireType(node, Type.Booleen);
    return null;
  }

  @Override
  public Object visit(Diff node) throws Exception {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    gauche.accept(this);
    droite.accept(this);

    ForceBinaireType(node, Type.Entier, Type.Booleen);
    ForceBinaireEqualType(node);
    return null;
  }

  @Override
  public Object visit(InfEgal node) throws Exception {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    gauche.accept(this);
    droite.accept(this);

    ForceBinaireType(node, Type.Entier, Type.Booleen);
    ForceBinaireEqualType(node);
    return null;
  }

  @Override
  public Object visit(Inferieur node) throws Exception {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    gauche.accept(this);
    droite.accept(this);

    ForceBinaireType(node, Type.Entier, Type.Booleen);
    ForceBinaireEqualType(node);
    return null;
  }

  @Override
  public Object visit(Superieur node) throws Exception {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    gauche.accept(this);
    droite.accept(this);

    ForceBinaireType(node, Type.Entier, Type.Booleen);
    ForceBinaireEqualType(node);
    return null;
  }

  @Override
  public Object visit(SupEgal node) throws Exception {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    gauche.accept(this);
    droite.accept(this);

    ForceBinaireType(node, Type.Entier, Type.Booleen);
    ForceBinaireEqualType(node);
    return null;
  }

  @Override
  public Object visit(Egal node) throws Exception {
    Expression gauche = node.GetGauche();
    Expression droite = node.GetDroite();

    gauche.accept(this);
    droite.accept(this);

    ForceBinaireType(node, Type.Entier, Type.Booleen);
    ForceBinaireEqualType(node);
    return null;
  }

  public Object visit(Idf node) throws Exception {
    if (!TableDesSymboles.Exists(node.GetNom())) {
      throw new UndefinedException(node, String.format("l'indetifient %s n'existe pas", node.GetNom()));
    }

    SimpleEntry<Type, Boolean> variable = TableDesSymboles.variables.get(node.GetNom());
    if (variable != null && variable.getValue() == false) {
      throw new UnassignedException(node, String.format("la variable %s n'est pas initialisée", node.GetNom()));
    }

    return null;
  }

  @Override
  public Object visit(Ecrire node) throws Exception {
    Expression exp = node.GetSource();
    exp.accept(this);

    String message = "l'instruction %s ne supporte pas le type %s";
    List<Type> types = List.of(Type.Entier, Type.Booleen, Type.Chaine);

    if (!types.contains(exp.GetType())) {
      throw new IncompatibleTypeException(node,
          String.format(message, node.getClass().getSimpleName(), exp.GetType().GetLabel()));
    }

    return null;
  }

  @Override
  public Object visit(Lire node) throws Exception {
    Idf exp = node.GetDestination();

    try {
      exp.accept(this);
    } catch (UnassignedException e) {
      // that's actually ok, prevent from throwing
    }

    if (TableDesSymboles.constants.containsKey(exp.GetNom())) {
      throw new AssignConstantException(node.GetDestination(),
          String.format("impossible d'assigner la constante %s", exp.GetNom()));
    }

    if (exp.GetType() != Type.Entier) {
      throw new IncompatibleTypeException(node,
          String.format("l'instruction %s ne supporte pas le type %s", node.getClass().getSimpleName(),
              exp.GetType().GetLabel()));
    }

    return null;
  }

  @Override
  public Object visit(Non node) throws Exception {
    Expression op = node.GetOperande();
    op.accept(this);

    ForceUnaireType(node, Type.Booleen);
    return null;
  }

  @Override
  public Object visit(Moins node) throws Exception {
    Expression op = node.GetOperande();
    op.accept(this);

    ForceUnaireType(node, Type.Entier);
    return null;
  }

  @Override
  public Object visit(Tilda node) throws Exception {
    // this is the same as Moins
    Expression op = node.GetOperande();
    op.accept(this);

    ForceUnaireType(node, Type.Entier);
    return null;
  }

  @Override
  public Object visit(Parentheses node) throws Exception {
    Expression exp = node.getExpression();
    exp.accept(this);
    return null;
  }

  @Override
  public Object visit(Pour node) throws Exception {
    Idf iterator = node.GetIteratorName();
    Expression from = node.GetFrom();
    Expression to = node.GetTo();

    try {
      iterator.accept(this);
    } catch (UnassignedException e) {
      // that's actually ok, prevent from throwing
    }

    from.accept(this);
    to.accept(this);

    if (iterator.GetType() != Type.Entier) {
      throw new IncompatibleTypeException(iterator,
          String.format("la variable d'itération d'une instruction %s doit être de type %s",
              node.getClass().getSimpleName(), Type.Entier.GetLabel()));
    }

    if (from.GetType() != Type.Entier) {
      throw new IncompatibleTypeException(from,
          String.format("la borne inférieure d'une instruction %s doit être de type %s",
              node.getClass().getSimpleName(), Type.Entier.GetLabel()));
    }

    if (to.GetType() != Type.Entier) {
      throw new IncompatibleTypeException(to,
          String.format("la borne supérieure d'une instruction %s doit être de type %s",
              node.getClass().getSimpleName(), Type.Entier.GetLabel()));
    }

    return null;
  }

  @Override
  public Object visit(Condition node) throws Exception {
    Expression condition = node.GetCondition();
    Bloc thenBloc = node.GetThenInstructions();
    Optional<Bloc> elseBloc = node.GetElseInstructions();

    condition.accept(this);
    thenBloc.accept(this);

    if (elseBloc.isPresent()) {
      elseBloc.get().accept(this);
    }

    if (condition.GetType() != Type.Booleen) {
      throw new IncompatibleTypeException(condition,
          String.format("la condition d'une instruction %s doit être de type %s",
              node.getClass().getSimpleName(), Type.Booleen.GetLabel()));
    }

    return null;
  }

  @Override
  public Object visit(Tantque node) throws Exception {
    Expression exp = node.GetCondition();
    Bloc bloc = node.GetInstructions();

    exp.accept(this);
    bloc.accept(this);

    String message = "la condition d'une instruction %s doit être de type %s";
    if (exp.GetType() != Type.Booleen) {
      throw new IncompatibleTypeException(exp,
          String.format(message, node.getClass().getSimpleName(), Type.Booleen.GetLabel()));
    }

    return null;
  }

  @Override
  public Object visit(Vrai node) {
    // nothing to semantically check here
    return null;
  }

  @Override
  public Object visit(Faux node) {
    // nothing to semantically check here
    return null;
  }

  @Override
  public Object visit(Nombre node) {
    // nothing to semantically check here
    return null;
  }

  @Override
  public Object visit(Chaine node) {
    // nothing to semantically check here
    return null;
  }

}
