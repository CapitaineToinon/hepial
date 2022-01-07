package main.ast.exceptions;

import main.ast.ASTNode;

public class IncompatibleTypeException extends SemantiqueException {
    public IncompatibleTypeException(ASTNode node, String message) {
        super(node, message);
    }
}
