package SyntaxTree;

import Utilities.*;

public class ReturnStatement extends Statement {
	private Expression mExpression = null;
	
	public ReturnStatement(
			Expression aExpression) {
		mExpression = aExpression;
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aAnalyzer) {
		aAnalyzer.ValidateReturnType(mExpression.EvaluateExpressionType());
	}
	
	@Override
	public void Compile(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		aWriter.EmitReturn(mExpression.EvaluateToTemporaryVariable(aSymbolTable, aWriter));
	}
}
