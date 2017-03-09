package tfg.sw.implementationExample.pattern.state.ExampleWrongTransitions;

/*
 *	@pattern State <ExampleWrongTransitions>
 *	@patternElement ConcreteState final
 */
public class State3 implements State {

	/*
	 * @patternAction Transition State1
	 */
	@Override
	public void action1(Context context) {
		context.setState(new State1());
	}

	@Override
	public int action2(Context context, String string, int number) {
		return number;
	}

	/*
	 * @patternAction Transition State2
	 */
	@Override
	public void action3(Context context) {
		context.setState(new State2());
	}
}