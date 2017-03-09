package tfg.sw.maker.maker;

import tfg.sw.maker.pattern.Pattern;

public abstract class PatternTest {

	protected Pattern pattern;

	protected String testFileName = "";

	public PatternTest(Pattern pattern) {
		super();
		this.pattern = pattern;
	}

	public abstract String getTests(String destinyPackage);

	public String getTestsFileName() {
		String result = "";

		result += this.pattern.getPatternId() + this.testFileName;

		return result;
	}
}
