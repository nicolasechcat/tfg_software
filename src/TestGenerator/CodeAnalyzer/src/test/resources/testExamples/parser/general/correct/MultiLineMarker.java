package testExamples.parser.general.correct;

import testExamples.parser.general.correct.secondStep.State1Context;
import testExamples.parser.general.correct.secondStep.State1Header;

/*
 *	@pattern State <State1Header>
 *	@patternElement SpecificState initial
 */
public class MultiLineMarker extends State1Header {
	/*
	 * @patternAction Transition State1Concrete2 <context,
	 * 000000000412411215235>
	 */
	@Override
	public void action1 (State1Context context) {
		// TODO Auto-generated method stub
		
	}
}
