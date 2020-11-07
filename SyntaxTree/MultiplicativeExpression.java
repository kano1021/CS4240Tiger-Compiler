package SyntaxTree;

import java.util.List;

import Utilities.BackendManager;
import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class MultiplicativeExpression extends Expression {
	private UnaryExpression mUnaryExpression = null;
	private MultiplicativeExpressionTail mTail = null;
	
	public MultiplicativeExpression(
			UnaryExpression aUnaryExpression,
			MultiplicativeExpressionTail aTail) {
		mUnaryExpression = aUnaryExpression;
		mTail = aTail;
	}

	@Override
	public IntrinsicTypeE EvaluateExpressionType() {
		IntrinsicTypeE fLHS = mUnaryExpression.EvaluateExpressionType(),
				fRHS = IntrinsicTypeE.Void;
		
		if (null != mTail)
			fRHS = mTail.EvaluateExpressionType();
		
		return EvaluateExpressionTypeFrom(fLHS, fRHS);
	}

	@Override
	public VariableSymbol EvaluateToTemporaryVariable(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		if (null == mTail)
			return mUnaryExpression.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		
		VariableSymbol fLHS = mUnaryExpression.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		VariableSymbol fRHS = mTail.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		VariableSymbol fReturn = aSymbolTable.CreateTemporaryVariable(this.EvaluateExpressionType());
		aWriter.EmitOperation(mTail.GetOperator(), fLHS, fRHS, fReturn);
		return fReturn;
	}

	@Override
	public void AnalyzeSemantics(SymbolTable aSymbolTable, SemanticAnalyzer aSemanticAnalyzer) {
		// Nothing to do.
	}
}
