package tfg.sw.implementationExample.pattern.state.ExampleCorrect;

/*
 *	@pattern State <ExampleCorrect>
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
	 * @patternAction Transition State1 <context, "test", 5>
	 */
	public int action2(Context context, String string, int number) {
		return number;
	}

	/*
	 * @patternAction Transition State2 <context>
	 */
	public void action3(Context context) {
		context.setState(new State2());
	}
}
