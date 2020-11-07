package SyntaxTree;

import java.util.List;

import Utilities.BackendManager;
import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class AdditiveExpression extends Expression {
	private MultiplicativeExpression mLHS = null;
	private AdditiveExpressionTail mRHS = null;
	
	public AdditiveExpression(
			MultiplicativeExpression aLHS,
			AdditiveExpressionTail aRHS) {
		mLHS = aLHS;
		mRHS = aRHS;
	}

	@Override
	public IntrinsicTypeE EvaluateExpressionType() {
		IntrinsicTypeE fLHS = mLHS.EvaluateExpressionType(),
			fRHS = IntrinsicTypeE.Void;
				
		if (null != mRHS)
			fRHS = mRHS.EvaluateExpressionType();
		
		return EvaluateExpressionTypeFrom(fLHS, fRHS);
	}

	@Override
	public VariableSymbol EvaluateToTemporaryVariable(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		if (null == mRHS)
			return mLHS.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		
		VariableSymbol fLHS = mLHS.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		VariableSymbol fRHS = mRHS.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		VariableSymbol fReturn = aSymbolTable.CreateTemporaryVariable(this.EvaluateExpressionType());
		aWriter.EmitOperation(mRHS.GetOperator(), fLHS, fRHS, fReturn);
		return fReturn;
	}

	@Override
	public void AnalyzeSemantics(SymbolTable aSymbolTable, SemanticAnalyzer aSemanticAnalyzer) {
		// Nothing to do.
	}
}
