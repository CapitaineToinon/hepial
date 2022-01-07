package main;

import main.ast.AnalyseSemantique;
import main.ast.SourceCodeGenerator;
import main.ast.instruction.DeclarationProgramme;

import java.io.FileReader;

public class App {
	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			throw new Exception("args empty");
		}

		System.out.println("#############################################################");
		System.out.println("#                         TITLE                             #");
		System.out.println("#############################################################");
		System.out.println("# Auteur :                                                  #");
		System.out.println("# Classe :                                                  #");
		System.out.println("#                                                           #");
		System.out.printf("# Fichier testé : %-41s #\n", args[0]);
		System.out.println("#############################################################");
		System.out.println("#                        Résultat                           #");
		System.out.println("#############################################################");

		FileReader myFile;
		Lexer myTP;
		parser myP;
		DeclarationProgramme program;

		try {
			myFile = new FileReader(args[0]);
		} catch (Exception e) {
			System.out.println("[test.java] new FileReader failed");
			throw e;
		}

		try {
			myTP = new Lexer(myFile);
		} catch (Exception e) {
			System.out.println("[test.java] new Lexer failed");
			throw e;
		}

		try {
			myP = new parser(myTP, args[0]);
		} catch (Exception e) {
			System.out.println("[test.java] new parser failed");
			throw e;
		}

		try {
			// on lance le parsing, et on récupère l'AST
			program = (DeclarationProgramme) myP.parse().value;
			if (program == null)
				throw new Exception("no AST recieved");

		} catch (Exception e) {
			System.out.println("[test.java] myP.parse() failed");
			throw e;
		}

		try {
			SourceCodeGenerator sourceGenerator = new SourceCodeGenerator();
			System.out.println("___________ code source selon la lecture de l'arbre abstrait : _______________");
			program.accept(sourceGenerator);
			System.out.println(sourceGenerator.getCode());
			System.out.println("_________________________");
		} catch (Exception e) {
			System.out.println("[test.java] SourceCodeGenerator failed");
			throw e;
		}

		try {
			// analyse sémantique
			AnalyseSemantique analyseur = new AnalyseSemantique();
			program.accept(analyseur);
		} catch (Exception e) {
			System.out.println("[test.java] AnalyseSemantique failed");
			throw e;
		}

		// production du code
		// todo

		System.out.println("#############################################################");
	}
}
