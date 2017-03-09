package tfg.sw.analyzer.analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import tfg.sw.analyzer.pattern.PatternName;

public class PatternParsedFiles {
	
	private PatternName patternName;
	private String patternId;
	private ParsedFile headerPatternFile;
	private List<ParsedFile> patterFileList;
	
	
	public PatternParsedFiles () {
		this.patterFileList = new ArrayList<ParsedFile>();
	}
	
	public PatternParsedFiles (PatternName patternName, String PatternId,
			ParsedFile headerPatternFile) throws FileNotFoundException {
		super();
		this.patternName = patternName;
		this.patternId = PatternId;
		this.patterFileList = new ArrayList<ParsedFile>();
		this.headerPatternFile = headerPatternFile;
	}
	
	
	
	public PatternParsedFiles (PatternName patternName, String patternId) {
		super();
		this.patternName = patternName;
		this.patternId = patternId;
		this.patterFileList = new ArrayList<ParsedFile>();
		this.headerPatternFile = null;
	}
	
	public ParsedFile getHeaderPatternFile () {
		return headerPatternFile;
	}
	
	public void setHeaderPatternFile (ParsedFile headerFile) {
		this.headerPatternFile = headerFile;
	}
	
	public void setHeaderFile (File headerFile) throws FileNotFoundException {
		this.headerPatternFile = new ParsedFile(headerFile);
	}
	
	public String getPatternId () {
		return patternId;
	}
	
	public void setPatternId (String PatternId) {
		this.patternId = PatternId;
	}
	
	public void addPatternFile (ParsedFile patternfile)
			throws FileNotFoundException {
		if (!this.patterFileList.contains(patternfile))
			this.patterFileList.add(patternfile);
	}
	
	public PatternName getPatternName () {
		return patternName;
	}
	
	public void setPatternName (PatternName name) {
		this.patternName = name;
	}
	
	public List<ParsedFile> getPatterFileList () {
		return patterFileList;
	}
	
	public void setPatterFileList (List<ParsedFile> patterFileList) {
		this.patterFileList = patterFileList;
	}
	
	
	@Override
	public boolean equals (Object obj) {
		if (obj == null)
			return false;
		
		if (obj.getClass() != this.getClass())
			return false;
		
		PatternParsedFiles aux = (PatternParsedFiles) obj;
		
		// if (aux.getPatternName() != this.patternName)
		// return false;
		
		if (aux.getPatternId().compareTo(this.patternId) != 0)
			return false;
		
		return true;
	}
	
	
	
	
	
}
