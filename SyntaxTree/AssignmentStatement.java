package SyntaxTree;

import Utilities.*;

public class AssignmentStatement extends Statement {
	private LValue mLValue = null;
	private Expression mExpression = null;
	
	public AssignmentStatement(
			LValue aLValue,
			Expression aExpression) {
		mLValue = aLValue;
		mExpression = aExpression;
	}

	@Override
	public void Compile(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		// TODO:	We can modify EvaluateToTemporaryVariable to accept a nullable variable.
		// If null, create a temporary variable.
		VariableSymbol fExpression = mExpression.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		Expression fOffsetExpression = mLValue.GetOffset();
		String fxOffset = "0";
		if (null != fOffsetExpression)
			fxOffset = fOffsetExpression.EvaluateToTemporaryVariable(aSymbolTable, aWriter).GetName();
			
		aWriter.EmitSet(mLValue.GetVariable(), fxOffset, fExpression);
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		IntrinsicTypeE fLHS = mLValue.EvaluateExpressionType(),
			fRHS = mExpression.EvaluateExpressionType();
		aSemanticAnalyzer.ValidateAssignable(fLHS, fRHS);
	}
}
