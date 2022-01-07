package main.ast.exceptions;

import main.ast.ASTNode;

public class UnassignedException extends SemantiqueException {
    public UnassignedException(ASTNode node, String message) {
        super(node, message);
    }
}
