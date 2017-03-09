package tfg.sw.implementationExample.pattern.state.ExampleOrderDependentWrong;

/*
 *	@pattern State <ExampleOrederDependentWrong>
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
	 * @patternAction Transition State1 <context, "est", 5>
	 */
	@Override
	public int action2(Context context, String string, int number) {
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