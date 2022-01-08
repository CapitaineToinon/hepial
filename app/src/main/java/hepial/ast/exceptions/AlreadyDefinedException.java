package hepial.ast.exceptions;

import hepial.ast.ASTNode;

public class AlreadyDefinedException extends SemantiqueException {
    public AlreadyDefinedException(ASTNode node, String message) {
        super(node, message);
    }
}
