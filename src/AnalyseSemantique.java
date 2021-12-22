import java.util.Map;
import java.util.HashMap;

public class AnalyseSemantique implements ASTVisitor {

  Map<String, Type> variables = new HashMap<String, Type>();
  Map<String, Type> constants = new HashMap<String, Type>();

  @Override
  public Object visit(Addition node) {
    // TODO Auto-generated method stub
    return null;
  }

  public Object visit(Affectation node) throws Exception {
    if (node.getDestination().getClass() == Idf.class) {
      Idf dest = (Idf) node.getDestination();
      String nom = dest.getNom();

      if (constants.containsKey(nom)) {
        throw new SemantiqueException(node.getDestination(),
            String.format("Impossible d'assigner %s car c'est une constante", nom));
      }

      if (!variables.containsKey(nom)) {
        throw new SemantiqueException(node.getDestination(),
            String.format("Impossible d'assigner %s car cette variable n'a pas été déclarée", nom));
      }

      Type t = variables.get(nom);
      Class<?> c = node.getSource().getClass();

      // todo support more than literals
      if (!t.accepted.contains(c)) {
        throw new SemantiqueException(node.getDestination(),
            String.format("Impossible d'assigner %s à la variable de type %s", c.getName(), t.label));
      }

      node.getSource().accept(this);
    }

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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Condition node) {
    // TODO Auto-generated method stub
    return null;
  }

  public Object visit(DeclarationConstant node) throws Exception {
    Idf idf = node.getIdentifier();

    if (constants.containsKey(idf.getNom())) {
      throw new SemantiqueException(idf, String.format("constante avec le nom %s existe déjà", idf.getNom()));
    }

    Class<?> c = node.getExpression().getClass();
    Type t = node.getType();

    if (!t.accepted.contains(c)) {
      throw new SemantiqueException(idf, String
          .format("Il est impossible d'assigner la valeur type %s à la constante de type %s", t.label, c.getName()));
    }

    constants.put(idf.getNom(), t);

    idf.accept(this);
    node.getExpression().accept(this);

    return null;
  }

  public Object visit(DeclarationProgramme node) throws Exception {
    node.getIdentifier().accept(this);
    node.getDeclaration().accept(this);
    node.getInstructions().accept(this);
    return null;
  }

  public Object visit(DeclarationVariable node) throws Exception {
    Type t = node.getType();

    for (Idf idf : node.getIdentifiants()) {
      if (variables.containsKey(idf.getNom())) {
        throw new SemantiqueException(idf, String.format("variable avec le nom %s existe déjà", idf.getNom()));
      }

      variables.put(idf.getNom(), t);
    }

    return null;
  }

  @Override
  public Object visit(Diff node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Division node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Ecrire node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Egal node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Et node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Faux node) {
    // TODO Auto-generated method stub
    return null;
  }

  public Object visit(Idf node) {
    // nothing to check with Idf
    return null;
  }

  @Override
  public Object visit(InfEgal node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Inferieur node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Lire node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Moins node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Nombre node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Non node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Ou node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Parentheses node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Pour node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Produit node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Soustraction node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(SupEgal node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Superieur node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Tantque node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Tilda node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Vrai node) {
    // TODO Auto-generated method stub
    return null;
  }

}
