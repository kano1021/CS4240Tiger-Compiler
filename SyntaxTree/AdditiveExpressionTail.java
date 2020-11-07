package SyntaxTree;

import Utilities.BackendManager;
import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class AdditiveExpressionTail extends ExpressionTailBase {
	private MultiplicativeExpression mMultiplication = null;
	private AdditiveExpressionTail mAddition = null;
	
	public AdditiveExpressionTail(
			OperatorE aOperator,
			MultiplicativeExpression aMultiplication,
			AdditiveExpressionTail aAddition) {
		super(aOperator);
		mMultiplication = aMultiplication;
		mAddition = aAddition;
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		// Nothing to do.
	}

	@Override
	public VariableSymbol EvaluateToTemporaryVariable(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		if (null == mAddition)
			return mMultiplication.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		
		VariableSymbol fLHS = mMultiplication.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		VariableSymbol fRHS = mAddition.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		VariableSymbol fReturn = aSymbolTable.CreateTemporaryVariable(this.EvaluateExpressionType());
		aWriter.EmitOperation(mAddition.GetOperator(), fLHS, fRHS, fReturn);
		return fReturn;
	}

	@Override
	public IntrinsicTypeE EvaluateExpressionType() {
		IntrinsicTypeE fLHS = mMultiplication.EvaluateExpressionType(),
				fRHS = IntrinsicTypeE.Void;
		
		if (null == mAddition)
			return fLHS;
		
		fRHS = mAddition.EvaluateExpressionType();
		
		return Expression.EvaluateExpressionTypeFrom(fLHS, fRHS);
	}
}
