package testExamples.parser.state.wrong;

/*
 *	@pattern State <Auxiliar>
 *	@patternElement Context
 */
public class AuxiliarContext {
	
	private AuxiliarGeneralState state;
	
	public AuxiliarGeneralState getState () {
		return state;
	}
	
	public void setState (AuxiliarGeneralState state) {
		this.state = state;
	}
	
	
}
