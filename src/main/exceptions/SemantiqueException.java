package main.exceptions;

import main.ast.ASTNode;

public class SemantiqueException extends Exception {
  public SemantiqueException(ASTNode node, String message) {
    super(String.format("%s - %s:%d", message, node.getFilename(), node.getLine() + 1));
  }
}