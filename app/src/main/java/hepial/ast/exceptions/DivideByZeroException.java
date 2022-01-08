package hepial.ast.exceptions;

import hepial.ast.ASTNode;

public class DivideByZeroException extends SemantiqueException {
    public DivideByZeroException(ASTNode node, String message) {
        super(node, message);
    }
}
