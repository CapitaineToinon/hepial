package main.ast.exceptions;

import main.ast.ASTNode;

public class AssignConstantException extends SemantiqueException {
    public AssignConstantException(ASTNode node, String message) {
        super(node, message);
    }
}
