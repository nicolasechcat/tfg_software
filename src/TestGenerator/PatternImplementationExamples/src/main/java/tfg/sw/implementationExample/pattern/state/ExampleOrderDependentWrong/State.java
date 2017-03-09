package tfg.sw.implementationExample.pattern.state.ExampleOrderDependentWrong;

/*
 *	@pattern State <ExampleOrederDependentWrong>
 *	@patternElement State orderdep
 */
public interface State {

	public void action1(Context context);

	public int action2(Context context, String string, int number);

	public void action3(Context context);
}