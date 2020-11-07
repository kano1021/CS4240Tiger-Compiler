package SyntaxTree;

import java.util.List;

import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class RelationalExpression extends ExpressionBase<AdditiveExpression> {
	private OperatorE mOperator;
	
	public RelationalExpression(
			List<AdditiveExpression> avBranches,
			OperatorE aOperator)
	{
		super(avBranches);
		mOperator = aOperator;
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		// TODO: Should we check for voids?		
	}

	@Override
	protected OperatorE GetOperator() {
		return mOperator;
	}
}
