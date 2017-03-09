package tfg.sw.analyzer.analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ParsedFile {
	
	
	private File file;
	private List<MarkedBlockComment> markedComments;
	private List<String> imports;
	
	
	public ParsedFile (File file) throws FileNotFoundException {
		super();
		this.file = file;
		
		this.markedComments = Parser.getAllBlockCommentMarkers(file);
		this.imports = Parser.getImports(file);
	}
	
	public ParsedFile (String filePath) throws FileNotFoundException {
		super();
		this.file = new File(filePath);
		
		this.markedComments = Parser.getAllBlockCommentMarkers(file);
		this.imports = Parser.getImports(file);
	}
	
	public File getFile () {
		return file;
	}
	
	
	public void setFile (File file) {
		this.file = file;
	}
	
	
	public List<MarkedBlockComment> getMarkedComments () {
		return markedComments;
	}
	
	
	public void setMarkedComments (List<MarkedBlockComment> markedComments) {
		this.markedComments = markedComments;
	}
	
	public boolean isMarked () {
		return this.markedComments == null || this.markedComments.size() == 0;
	}
	
	
	
	public List<String> getImports () {
		return imports;
	}
	
	public void setImports (List<String> imports) {
		this.imports = imports;
	}
	
	@Override
	public boolean equals (Object obj) {
		if (obj == null)
			return false;
		
		if (obj.getClass() != this.getClass())
			return false;
		
		ParsedFile other = (ParsedFile) obj;
		
		// if (aux.getPatternName() != this.patternName)
		// return false;
		
		if (!this.getFile().equals(other.getFile()))
			return false;
		
		return true;
	}
	
	@Override
	public String toString () {
		return "ParsedFile [file=" + file + ", markedComments="
				+ markedComments + ", imports=" + imports + "]";
	}
	
	
	
	
	
}
