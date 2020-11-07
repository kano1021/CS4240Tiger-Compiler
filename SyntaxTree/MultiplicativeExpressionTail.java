package SyntaxTree;

import Utilities.BackendManager;
import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class MultiplicativeExpressionTail extends ExpressionTailBase {
	private UnaryExpression mUnaryExpression = null;
	private MultiplicativeExpressionTail mMultiplication = null;
	
	public MultiplicativeExpressionTail(
			OperatorE aOperator,
			UnaryExpression aUnaryExpression,
			MultiplicativeExpressionTail aMultiplication) {
		super(aOperator);
		mUnaryExpression = aUnaryExpression;
		mMultiplication = aMultiplication;
	}

	@Override
	public IntrinsicTypeE EvaluateExpressionType() {
		IntrinsicTypeE fLHS = mUnaryExpression.EvaluateExpressionType(),
				fRHS = IntrinsicTypeE.Void;
		
		if (null != mMultiplication)
			fRHS = mMultiplication.EvaluateExpressionType();
		
		return Expression.EvaluateExpressionTypeFrom(fLHS, fRHS);
	}
	
	@Override
	public VariableSymbol EvaluateToTemporaryVariable(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		if (null == mMultiplication)
			return mUnaryExpression.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		
		VariableSymbol fLHS = mUnaryExpression.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		VariableSymbol fRHS = mMultiplication.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		VariableSymbol fReturn = aSymbolTable.CreateTemporaryVariable(this.EvaluateExpressionType());
		aWriter.EmitOperation(mMultiplication.GetOperator(), fLHS, fRHS, fReturn);
		return fReturn;
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		// Nothing to do.
	}
}
