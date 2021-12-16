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
			DeclarationProgramme program = (DeclarationProgramme) myP.parse().value;
			if (program == null)
				// Aie, pas d'AST received ....
				return;

			System.out.println("ok, c'est retourné au prg java !!!!");
			// Lecture de l'AST selon design pattern Visitor
			// Et affichage du code d'origine
			SourceCodeGenerator sourceGenerator = new SourceCodeGenerator();
			System.out.println("___________ code source selon la lecture de l'arbre abstrait : _______________");
			program.accept(sourceGenerator);
			System.out.println(sourceGenerator.getCode());
			System.out.println("_________________________");

			// analyse sémantique
			// todo

			// production du code
			// todo

		} catch (Exception e) {
			System.out.println("[test.java] myP.parse() failed");
			throw e;
		}

		System.out.println("#############################################################");
	}
}
