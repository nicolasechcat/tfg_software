package tfg.sw.analyzer.analyzer;

import java.util.ArrayList;
import java.util.List;

public class Marker {
	
	public static final String PATERN_TAG = "pattern";
	public static final String PATERN_ELEMENT_TAG = "patternElement";
	public static final String PATERN_ACTION_TAG = "patternAction";
	
	private boolean isMarker = false;
	private String markerId = "";
	private List<String> params;
	
	private String filePath;
	private long lineNumber;
	
	
	public Marker (String filePath, long lineNumber, String line) {
		super();
		this.filePath = filePath;
		this.lineNumber = lineNumber;
		
		parse(line);
	}
	
	private void parse (String inputLine) {
		
		params = new ArrayList<String>();
		String line = inputLine.replace("\t", " ").trim();
		
		if (line.contains("@")
				&& line.indexOf("@", line.indexOf("@") + 1) == -1) {
			String[] splitted = line.trim().split(" ");
			
			int i = 0;
			int commAsterisk = 0;
			
			while (commAsterisk == 0
					&& (splitted[i].compareTo("*") == 0 || splitted[i]
							.isEmpty())) {
				i++;
				commAsterisk++;
			}
			
			if (splitted[i].trim().startsWith("@")) {
				markerId = splitted[i].replace("@", "").trim();
			} else {
				return;
			}
			
			switch (markerId) {
				case "pattern":
				case "patternElement":
				case "patternAction":
					isMarker = true;
					break;
				default:
					isMarker = false;
			}
			
			
			String aux = "";
			String tmp;
			boolean flag = false;
			
			for (int j = i + 1; j < splitted.length; j++) {
				tmp = splitted[j].trim();
				if (!tmp.isEmpty()) {
					// To merge towhether the <---->
					if (tmp.startsWith("<"))
						flag = true;
					
					aux += " " + tmp;
					
					// TODO Must be completed if is going to be accepted
					// multiline tags
					if (tmp.contains(">") || j == splitted.length - 1)
						flag = false;
					
					if (!flag) {
						params.add(aux.trim());
						aux = "";
					}
				}
			}
		}
	}
	
	public boolean isMarker () {
		return isMarker;
	}
	
	public void setMarker (boolean isMarker) {
		this.isMarker = isMarker;
	}
	
	public String getMarkerId () {
		return markerId;
	}
	
	public void setMarkerId (String markerId) {
		this.markerId = markerId;
	}
	
	public List<String> getParams () {
		return params;
	}
	
	public void setParams (List<String> params) {
		this.params = params;
	}
	
	public String getFilePath () {
		return filePath;
	}
	
	public void setFilePath (String filePath) {
		this.filePath = filePath;
	}
	
	public long getLineNumber () {
		return lineNumber;
	}
	
	public void setLineNumber (long lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	
	
	@Override
	public String toString () {
		return "Marker [isMarker=" + isMarker + ", markerId=" + markerId
				+ ", params=" + params + ", filePath=" + filePath
				+ ", lineNumber=" + lineNumber + "]";
	}
	
	@Override
	public boolean equals (Object obj) {
		
		if (obj == null)
			return false;
		
		if (obj.getClass() != this.getClass())
			return false;
		
		Marker other = (Marker) obj;
		
		if (other.isMarker != this.isMarker)
			return false;
		
		if (other.markerId.compareTo(this.markerId) != 0)
			return false;
		
		if (other.filePath.compareTo(this.filePath) != 0)
			return false;
		
		if (other.lineNumber != this.lineNumber)
			return false;
		
		if (other.params.size() != this.params.size())
			return false;
		
		for (String s : other.params)
			if (!this.params.contains(s))
				return false;
		
		return true;
		
	}
	
	
}
