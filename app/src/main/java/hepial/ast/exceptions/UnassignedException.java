package hepial.ast.exceptions;

import hepial.ast.ASTNode;

public class UnassignedException extends SemantiqueException {
    public UnassignedException(ASTNode node, String message) {
        super(node, message);
    }
}
