package testExamples.parser.state.correct;

/*
 *	@pattern State <State1Header>
 *	@patternElement ConcreteState initial final
 *  @patternElement Builder <new State1Concrete1()>
 */
public class State1Concrete1 extends State1Header {
	
	/*
	 * @patternAction Transition State1Concrete2
	 */
	@Override
	public void action1 (State1Context context) {
		context.setState(new State1Concrete2());
	}
	
	@Override
	public void action2 (String string, State1Context context, int number) {
		
	}
	
	/*
	 * @patternAction Transition State1Concrete2 <context>
	 */
	@Override
	public void action3 (State1Context context) {
		context.setState(new State1Concrete2());
		
	}
	
}
