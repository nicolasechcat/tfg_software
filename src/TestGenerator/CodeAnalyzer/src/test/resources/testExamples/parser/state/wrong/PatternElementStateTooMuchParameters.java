package testExamples.parser.state.wrong;

/*
 *	@pattern State <Auxiliar>
 *	@patternElement State orderdep another
 */
public abstract class PatternElementStateTooMuchParameters extends
		AuxiliarGeneralState {
	
	public abstract void action1 (AuxiliarContext context);
}
