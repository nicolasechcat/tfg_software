package testExamples.parser.state.wrong;

import testExamples.parser.state.correct.State1Header;


/*
 *	@pattern State <State1Header>
 *	@patternElement Context
 *	@patternElement Builder <new State1Context (new State1Concrete1())>
 */
public class State1Context2 {
	
	private State1Header state;
	
	public State1Context2 (State1Header initialState) {
		this.state = initialState;
	}
	
	public State1Header getState () {
		return state;
	}
	
	public void setState (State1Header state) {
		this.state = state;
	}
	
}