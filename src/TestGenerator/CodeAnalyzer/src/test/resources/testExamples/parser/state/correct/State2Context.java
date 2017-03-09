package testExamples.parser.state.correct;


/*
 *	@pattern State <State2>
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