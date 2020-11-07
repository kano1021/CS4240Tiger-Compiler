package SyntaxTree;

public abstract class ExpressionTailBase implements CompilableExpressionI {
	protected ExpressionTailBase(
			OperatorE aOperator) {
		mOperator = aOperator;
	}
	
	protected OperatorE mOperator;
	
	public OperatorE GetOperator() {
		return mOperator;
	}
}