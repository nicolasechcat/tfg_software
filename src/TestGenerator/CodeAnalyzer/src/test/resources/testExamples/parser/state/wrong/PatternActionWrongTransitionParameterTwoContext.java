package testExamples.parser.state.wrong;

import testExamples.parser.state.correct.State1Concrete1;
import testExamples.parser.state.correct.State1Context;
import testExamples.parser.state.correct.State1Header;

/*
 *	@pattern State
 *	@patternElement ConcreteState final
 */
public class PatternActionWrongTransitionParameterTwoContext extends
		State1Header {
	
	@Override
	public void action1 (State1Context context) {
		
	}
	
	/*
	 * @patternAction Transition State1Concrete1 <"test", context, context>
	 */
	@Override
	public void action2 (String string, State1Context context, int number) {
		
		context.setState(new State1Concrete1());
		
	}
	
	@Override
	public void action3 (State1Context context) {
		
	}
	
	
}
