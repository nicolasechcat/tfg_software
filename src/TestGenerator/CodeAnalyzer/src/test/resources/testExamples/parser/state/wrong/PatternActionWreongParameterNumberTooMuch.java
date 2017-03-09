package testExamples.parser.state.wrong;

import testExamples.parser.state.correct.State1Concrete2;
import testExamples.parser.state.correct.State1Context;
import testExamples.parser.state.correct.State1Header;

/*
 *	@pattern State <State1Header>
 *	@patternElement ConcreteState final
 */
public class PatternActionWreongParameterNumberTooMuch extends State1Header {
	
	/*
	 * @patternAction Transition State1Concrete2 <context> another
	 */
	@Override
	public void action1 (State1Context context) {
		context.setState(new State1Concrete2());
	}
	
	@Override
	public void action2 (String string, State1Context context, int number) {
		
	}
	
	/*
	 * @patternAction Transition State1Concrete2 <context>
	 */
	@Override
	public void action3 (State1Context context) {
		context.setState(new State1Concrete2());
		
	}
	
}