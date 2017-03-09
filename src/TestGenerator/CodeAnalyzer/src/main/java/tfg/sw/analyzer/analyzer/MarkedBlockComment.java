package tfg.sw.analyzer.analyzer;

import java.util.List;

public class MarkedBlockComment {

	private List<Marker> markers;
	private String blockComment = "";
	private String codeAfterBlock = "";
	private long lineNumberCodeAfterBlock = 0;
	private int scope = 0;

	public MarkedBlockComment(List<Marker> markers, String blockComment,
			int scope) {
		super();
		this.markers = markers;
		this.blockComment = blockComment;
		this.scope = scope;
	}

	public MarkedBlockComment(List<Marker> markers, String blockComment,
			String codeAfterBlock, int lineNumberCodeAfterBlock, int scope) {
		super();
		this.markers = markers;
		this.blockComment = blockComment;
		this.codeAfterBlock = codeAfterBlock;
		this.lineNumberCodeAfterBlock = lineNumberCodeAfterBlock;
		this.scope = scope;
	}

	public long getLineNumberCodeAfterBlock() {
		return lineNumberCodeAfterBlock;
	}

	public void setLineNumberCodeAfterBlock(long lineNumberCodeAfterBlock) {
		this.lineNumberCodeAfterBlock = lineNumberCodeAfterBlock;
	}

	public List<Marker> getMarkers() {
		return markers;
	}

	public void setMarkers(List<Marker> markers) {
		this.markers = markers;
	}

	public String getBlockComment() {
		return blockComment;
	}

	public void setBlockComment(String blockComment) {
		this.blockComment = blockComment;
	}

	public String getCodeAfterBlock() {
		return codeAfterBlock;
	}

	public void setCodeAfterBlock(String codeAfterBlock) {
		this.codeAfterBlock = codeAfterBlock;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

}
