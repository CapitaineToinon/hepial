package hepial;

import hepial.ast.AnalyseSemantique;
import hepial.ast.ByteCodeGenerator;
import hepial.ast.SourceCodeGenerator;
import hepial.ast.instruction.DeclarationProgramme;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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
			System.out.println("[test.java] Le programme est sémantiquement correct !");
		} catch (Exception e) {
			System.out.println("[test.java] AnalyseSemantique failed");
			throw e;
		}

		try {
			// jasmin code generation
			ByteCodeGenerator generator = new ByteCodeGenerator();
			String code = (String) program.accept(generator);

			java.util.Scanner scanner = new java.util.Scanner(code);
			int nb = 1;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println(String.format("%d	%s", nb++, line));
			}
			scanner.close();

			File dest = new File(String.format("%s.j", program.getIdentifier().GetNom()));
			FileWriter myWriter = new FileWriter(dest);

			myWriter.write(code);
			myWriter.close();
			dest.createNewFile();
		} catch (Exception e) {
			System.out.println("[test.java] AnalyseSemantique failed");
			throw e;
		}

		System.out.println("#############################################################");
	}
}
