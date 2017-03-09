package testExamples.parser.state.wrong;

import testExamples.parser.state.correct.State1Context;
import testExamples.parser.state.correct.State1Header;


/*
 *	@pattern State
 *	@patternElement States
 */
public class PatternElementTypeNotKnown extends State1Header {
	
	@Override
	public void action1 (State1Context context) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void action2 (String string, State1Context context, int number) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void action3 (State1Context context) {
		// TODO Auto-generated method stub
		
	}
	
	
}
