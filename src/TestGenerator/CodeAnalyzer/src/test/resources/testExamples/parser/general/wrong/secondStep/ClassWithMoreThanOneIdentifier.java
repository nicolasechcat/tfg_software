package testExamples.parser.general.wrong.secondStep;

import testExamples.parser.general.correct.secondStep.State1Context;
import testExamples.parser.general.correct.secondStep.State1Header;
import testExamples.parser.general.correct.secondStep.State2Context;
import testExamples.parser.general.correct.secondStep.State2Header;

/*
 *	@pattern State
 *	@patternElement SpecificState
 */
public class ClassWithMoreThanOneIdentifier extends State1Header implements
		State2Header {
	
	
	@Override
	public void action1 (State1Context context) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void action1 (State2Context context) {
		// TODO Auto-generated method stub
		
	}
}