package tfg.sw.implementationExample.pattern.state.ExampleNotOrderDependentCorrect;

/*
 *	@pattern State <ExampleNotOrderDependentCorrect>
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

	/*
	 * @patternAction Transition State1 <context, "test", 5>
	 */
	@Override
	public int action2(Context context, String string, int number) {
		if (string.compareTo("test") == 0 && number == 5)
			context.setState(new State1());
		return number;
	}

	/*
	 * @patternAction Transition State1
	 */
	@Override
	public void action3(Context context) {
		context.setState(new State1());
	}
}