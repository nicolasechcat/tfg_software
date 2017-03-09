package testExamples.parser.general.correct.secondStep;


/*
 *	@pattern State <State2Header>
 *	@patternElement Context
 */
public class State2Context {
	
	private State2Header state;
	
	public State2Header getState () {
		return state;
	}
	
	public void setState (State2Header state) {
		this.state = state;
	}
	
}