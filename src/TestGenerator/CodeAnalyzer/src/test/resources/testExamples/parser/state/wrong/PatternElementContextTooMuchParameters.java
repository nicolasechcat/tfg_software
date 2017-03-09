package testExamples.parser.state.wrong;

/*
 *	@pattern State <Auxiliar>
 *	@patternElement Context another
 */
public class PatternElementContextTooMuchParameters {
	
	private AuxiliarGeneralState state;
	
	public AuxiliarGeneralState getState () {
		return state;
	}
	
	public void setState (AuxiliarGeneralState state) {
		this.state = state;
	}
}
