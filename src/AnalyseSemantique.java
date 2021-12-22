import java.util.Map;
import java.util.HashMap;

public class AnalyseSemantique implements ASTVisitor {

  Map<String, Idf> variables = new HashMap<String, Idf>();
  Map<String, Idf> constants = new HashMap<String, Idf>();

  @Override
  public Object visit(Addition node) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visit(Affectation node) {
    // TODO Auto-generated method stub
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

    constants.put(idf.getNom(), idf);

    return null;
  }

  public Object visit(DeclarationProgramme node) throws Exception {
    node.getDeclaration().accept(this);
    return null;
  }

  public Object visit(DeclarationVariable node) throws Exception {
    for (Idf idf : node.getIdentifiants()) {
      if (variables.containsKey(idf.getNom())) {
        throw new SemantiqueException(idf, String.format("variable avec le nom %s existe déjà", idf.getNom()));
      }

      variables.put(idf.getNom(), idf);
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

  @Override
  public Object visit(Idf node) {
    // TODO Auto-generated method stub
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
