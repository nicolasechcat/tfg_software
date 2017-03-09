package testExamples.parser.state.correct;

/*
 *	@pattern State <State2>
 *	@patternElement State orderdep
 */
public interface State2Header {
	
	public abstract void action1 (State2Context context);
}
