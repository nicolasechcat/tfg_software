package tfg.sw.implementationExample.pattern.state.ExampleNotOrderDependentWrong;

/*
 *	@pattern State <ExampleNotOrderDependentWrong>
 *	@patternElement ConcreteState initial final
 */
public class State1 implements State {

	/*
	 * @patternAction Transition State2
	 */
	public void action1(Context context) {
		context.setState(new State2());
	}

	/*
	 * @patternAction Transition State2 <context, "test", -5>
	 */
	@Override
	public int action2(Context context, String string, int number) {
		if (string.compareTo("test") == 0 && number == -5)
			context.setState(new State2());
		return number;
	}

	/*
	 * @patternAction Transition State2 <context>
	 */
	@Override
	public void action3(Context context) {
		context.setState(new State2());
	}
}
