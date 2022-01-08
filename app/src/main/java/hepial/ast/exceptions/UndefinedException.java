package hepial.ast.exceptions;

import hepial.ast.ASTNode;

public class UndefinedException extends SemantiqueException {
    public UndefinedException(ASTNode node, String message) {
        super(node, message);
    }
}
