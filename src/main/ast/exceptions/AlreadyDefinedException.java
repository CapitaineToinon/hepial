package main.ast.exceptions;

import main.ast.ASTNode;

public class AlreadyDefinedException extends SemantiqueException {
    public AlreadyDefinedException(ASTNode node, String message) {
        super(node, message);
    }
}
