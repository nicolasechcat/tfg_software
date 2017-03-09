package testExamples.parser.state.correct;

/*
 *	@pattern State
 *	@patternElement ConcreteState final
 */
public class State1Concrete2 extends State1Header implements Cloneable,
		Comparable<Object> {
	
	@Override
	public void action1 (State1Context context) {
		
	}
	
	/*
	 * @patternAction Transition State1Concrete1 <"test", context, 5>
	 */
	@Override
	public void action2 (String string, State1Context context, int number) {
		
		context.setState(new State1Concrete1());
		
	}
	
	@Override
	public void action3 (State1Context context) {
		
	}
	
	@Override
	public int compareTo (Object arg0) {
		return 0;
	}
	
}
