package testExamples.parser.state.correct;


/*
 *	@pattern State <State1Header>
 *	@patternElement Context
 *	@patternElement Builder <new State1Context (new State1Concrete1())>
 */
public class State1Context {
	
	private State1Header state;
	
	public State1Context (State1Header initialState) {
		this.state = initialState;
	}
	
	public State1Header getState () {
		return state;
	}
	
	public void setState (State1Header state) {
		this.state = state;
	}
	
}