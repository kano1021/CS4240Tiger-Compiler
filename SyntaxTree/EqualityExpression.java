package SyntaxTree;

import java.util.List;

import Utilities.*;

public class EqualityExpression extends ExpressionBase<RelationalExpression> {
	private OperatorE mOperator;
	
	public EqualityExpression(
			List<RelationalExpression> avBranches,
			OperatorE aOperator)
	{
		super(avBranches);
		mOperator = aOperator;
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		// Should we check for void expressions?
	}

	@Override
	protected OperatorE GetOperator() {
		return mOperator;
	}
}
