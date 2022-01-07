package main.ast.exceptions;

import main.ast.ASTNode;

public class UndefinedException extends SemantiqueException {
    public UndefinedException(ASTNode node, String message) {
        super(node, message);
    }
}
