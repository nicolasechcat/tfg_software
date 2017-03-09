package testExamples.parser.state.correct;

/*
 *	@pattern State
 *	@patternElement State
 */
public abstract class State1Header {
	
	public abstract void action1 (State1Context context);
	
	public abstract void action2 (String string, State1Context context,
			int number);
	
	public abstract void action3 (State1Context context);
}
