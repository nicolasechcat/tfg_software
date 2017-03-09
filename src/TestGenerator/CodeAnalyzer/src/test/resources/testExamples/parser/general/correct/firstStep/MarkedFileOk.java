package testExamples.parser.general.correct.firstStep;

import testExamples.parser.general.correct.secondStep.State1Context;


/*
 * another comment bloc
 */

// line comment
/*
 *	@pattern State
 *	@patternElement State
 */
/*
 * another comment block
 */
public abstract class MarkedFileOk {
	
	public abstract void action1 (State1Context context, int number);
	
	public abstract void action2 (State1Context context);
	
	public abstract void action3 (State1Context context);
}
