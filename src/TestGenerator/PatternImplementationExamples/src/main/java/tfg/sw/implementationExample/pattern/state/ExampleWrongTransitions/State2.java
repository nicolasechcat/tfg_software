package tfg.sw.implementationExample.pattern.state.ExampleWrongTransitions;

/*
 *	@pattern State <ExampleWrongTransitions>
 *	@patternElement ConcreteState
 */
public class State2 implements State {

	/*
	 * @patternAction Transition State3
	 */
	@Override
	public void action1(Context context) {
		context.setState(new State3());
	}

	/*
	 * @patternAction Transition State3 <context, "test", 5>
	 */
	@Override
	public int action2(Context context, String string, int number) {
		return number;
	}

	@Override
	public void action3(Context context) {

	}
}