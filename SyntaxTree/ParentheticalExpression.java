package SyntaxTree;

import Utilities.*;

public class ParentheticalExpression extends UnaryExpression {
	private Expression mNestedExpression = null;
	
	public ParentheticalExpression(
			Expression aNestedExpression) {
		mNestedExpression = aNestedExpression;
	}
		
	public VariableSymbol EvaluateToTemporaryVariable(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		return mNestedExpression.EvaluateToTemporaryVariable(aSymbolTable, aWriter);		
	}

	@Override
	public IntrinsicTypeE EvaluateExpressionType() {
		return mNestedExpression.EvaluateExpressionType();
	}

	@Override
	public void AnalyzeSemantics(
			SymbolTable aSymbolTable,
			SemanticAnalyzer aSemanticAnalyzer) {
		mNestedExpression.AnalyzeSemantics(aSymbolTable, aSemanticAnalyzer);
	}
}
