package tfg.sw.analyzer.analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import tfg.sw.analyzer.exception.SistemUtilException;
import tfg.sw.analyzer.util.SystemUtil;

public class Crowler {

	public static List<File> recursiveCrowling(String rootPath)
			throws SistemUtilException {

		File inputFile = new File(rootPath.replace("/", "\\"));
		if (!inputFile.exists() || !inputFile.canRead()
				|| !inputFile.isDirectory()) {
			System.err.println("input path error");

			String errorMessage = SystemUtil
					.getMessageById("crowler_directory_not_found");
			System.err.println(errorMessage + ": '"
					+ inputFile.getAbsolutePath());
			System.exit(1);
		}

		List<File> result = new ArrayList<File>();

		// Creamos la cola de ficheros como una lista
		List<String> files = new ArrayList<String>();

		// Inicializamos la cola con los ficheros del directorio incial
		for (String s : inputFile.list())
			files.add(inputFile.getAbsolutePath() + "\\" + s);

		// Mientras haya elementos en la cola
		while (!files.isEmpty()) {

			// Cogemos la cabeza de la cola
			File file = new File(files.get(0));

			// Sacamos de la lista el fichero que se esta procesando
			files.remove(0);

			// System.out.println("File: " + file.getAbsolutePath()
			// + "\t\t With filename: " + file.getName());

			// Si es un fichero accesible
			if (file.exists() && file.canRead()) {

				// Si es un directorio, añadimos sus ficheros a la cola
				if (file.isDirectory()) {

					for (String s : file.list()) {
						files.add(file.getAbsolutePath() + "\\" + s);
					}

					// Si es un fichero
				} else {

					// If it's a Java file, it's added to the result
					try {
						if (file.getPath().endsWith(".java") && isMarked(file)) {
							result.add(file);
						}
					} catch (FileNotFoundException e) {

					}
				}
			}
		}

		return result;
	}

	private static boolean isMarked(File file) throws FileNotFoundException {
		Scanner reader = null;
		String line = "";

		boolean insideBlockComment = false;

		/*
		 * Search in the file for the pattern identifier if it's explicit.
		 * Otherwise take the class identifier to general class marker or search
		 * in extends/implements one that marches any of groupedFiles in other
		 * case
		 */

		if (file.exists() && file.canRead()) {

			try {

				reader = new Scanner(file, "UTF-8");
				line = reader.nextLine();

				/*
				 * The marker must be before the class header. If not, it's not
				 * a marked class
				 */
				/*
				 * Process the file until class header outside a comment
				 */
				while (line != null
						&& !(!insideBlockComment && !line.contains("//") && (line
								.contains(" class ") || line
								.contains(" interface ")))) {

					line = line.trim();

					/*
					 * The markers must be inside a block comment but not at the
					 * beginning or ending, and must be the only markers of the
					 * file until the class header, all related in the same
					 * block comment
					 */
					if (line.startsWith("/*"))
						insideBlockComment = true;

					if (line.contains("*/"))
						insideBlockComment = false;

					if (insideBlockComment
							&& line.contains(Parser.MARKER_CHARACTER)) {

						return true;
					}

					// Next line
					line = reader.nextLine();

				}
			} catch (NoSuchElementException eol) {
			} finally {
				reader.close();
			}
		}
		return false;
	}

}
