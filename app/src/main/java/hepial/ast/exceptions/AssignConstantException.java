package hepial.ast.exceptions;

import hepial.ast.ASTNode;

public class AssignConstantException extends SemantiqueException {
    public AssignConstantException(ASTNode node, String message) {
        super(node, message);
    }
}
