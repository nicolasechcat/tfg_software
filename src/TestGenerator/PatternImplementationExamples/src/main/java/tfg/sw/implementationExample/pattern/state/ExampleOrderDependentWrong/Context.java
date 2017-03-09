package tfg.sw.implementationExample.pattern.state.ExampleOrderDependentWrong;




/*
 *	@pattern State <ExampleOrederDependentWrong>
 *	@patternElement Context
 *	@patternElement Builder <new Context (new State1())>
 */
public class Context {
	
	private State state;
	
	public Context (State initialState) {
		this.state = initialState;
	}
	
	public State getState () {
		return state;
	}
	
	public void setState (State state) {
		this.state = state;
	}
	
}