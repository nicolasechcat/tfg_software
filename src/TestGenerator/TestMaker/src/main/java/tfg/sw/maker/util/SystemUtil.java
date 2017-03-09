package tfg.sw.maker.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tfg.sw.maker.exception.SistemUtilException;

public class SystemUtil {
	
	private final String route = "./src/main/resources/Messages_UK.txt";
	
	private static SystemUtil instance = null;
	
	private List<String> ids;
	private List<String> messages;
	
	private SystemUtil () throws SistemUtilException {
		ids = new ArrayList<String>();
		messages = new ArrayList<String>();
		
		parseFile();
	}
	
	public static String getMessageById (String identifier)
			throws SistemUtilException {
		
		if (instance == null)
			instance = new SystemUtil();
		
		return instance.internalGetMessageById(identifier);
	}
	
	public static boolean hasSpecialCharacters (String text) {
		
		// expresión regular que revisa si tiene alguno de los siguientes
		// caracteres
		
		String REG_EXP = "\\¿+|\\?+|\\°+|\\¬+|\\|+|\\!+|\\#+|\\$+|"
				+ "\\%+|\\&+|\\+|\\=+|\\’+|\\¡+|\\++|\\*+|\\~+|\\[+|\\]"
				+ "+|\\{+|\\}+|\\^+|\\<+|\\>+|\\\"+ ";
		Pattern pattern = Pattern.compile(REG_EXP);
		Matcher matcher = pattern.matcher(text);
		
		return matcher.find(); // imprime true si tiene alguno de
								// los caracteres anteriores o false
								// si no tiene ninguno
		
	}
	
	private String internalGetMessageById (String identifier)
			throws SistemUtilException {
		
		String result = "";
		
		if (identifier.isEmpty())
			throw new SistemUtilException("The identifier can't be empty");
		
		int id = ids.indexOf(identifier);
		
		if (id == -1)
			throw new SistemUtilException("Identifier " + identifier
					+ " not found.");
		
		result = messages.get(id);
		
		return result;
	}
	
	public void parseFile () throws SistemUtilException {
		File file = new File(route);
		
		Scanner reader = null;
		String line = "";
		long lineNumber = 0;
		
		
		if (file.exists() && file.canRead()) {
			
			try {
				
				reader = new Scanner(file, "UTF-8");
				line = reader.nextLine();
				lineNumber++;
				
				while (line != null) {
					
					line = line.replace("\t", " ").trim();
					
					if (line.isEmpty()) {
						line = reader.nextLine();
						lineNumber++;
						continue;
					}
					
					if (line.startsWith("%")) {
						line = reader.nextLine();
						lineNumber++;
						continue;
					}
					
					if (!line.contains("="))
						throw new SistemUtilException(
								"The line must contain \"identifier = message\". Line "
										+ lineNumber);
					
					if (line.indexOf("=") == 0)
						throw new SistemUtilException(
								"The line must contain \"identifier = message\". No identifier found. Line "
										+ lineNumber);
					
					if (line.indexOf("=") == line.length() - 1)
						throw new SistemUtilException(
								"The line must contain \"identifier = message\". No message found. Line "
										+ lineNumber);
					
					ids.add(line.substring(0, line.indexOf("=")).trim());
					messages.add(line.substring(line.indexOf("=") + 1).trim());
					
					line = reader.nextLine();
					lineNumber++;
				}
				
			} catch (NoSuchElementException ex) {
			} catch (FileNotFoundException e) {
				throw new SistemUtilException("Messages file not found.");
			} finally {
				reader.close();
			}
		}
	}
	
}
