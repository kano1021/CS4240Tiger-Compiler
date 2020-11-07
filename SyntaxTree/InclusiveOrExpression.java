package SyntaxTree;

import java.util.List;

import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class InclusiveOrExpression extends BooleanExpressionBase<AndExpression> {
	public InclusiveOrExpression(
		List<AndExpression> avBranches) {
		super(avBranches);
	}

	@Override
	protected OperatorE GetOperator() {
		// TODO Auto-generated method stub
		return OperatorE.Or;
	}
}
