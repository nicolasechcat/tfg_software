package testExamples.parser.general.correct.secondStep;


/*
 *	@pattern State <State1Header>
 *	@patternElement Context
 */
public class State1Context {
	
	private State1Header state;
	
	public State1Header getState () {
		return state;
	}
	
	public void setState (State1Header state) {
		this.state = state;
	}
	
}