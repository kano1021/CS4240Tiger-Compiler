package SyntaxTree;

import java.util.List;

import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class AndExpression extends BooleanExpressionBase<EqualityExpression> {
	public AndExpression(
			List<EqualityExpression> avBranches) {
		super(avBranches);
	}

	@Override
	protected OperatorE GetOperator() {
		return OperatorE.And;
	}
}
