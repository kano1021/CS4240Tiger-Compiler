package SyntaxTree;

import Utilities.BackendManager;
import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class LValue extends UnaryExpression {
	private VariableSymbol mVariable = null;
	
	private Expression mOffset = null;
	
	public LValue(
			VariableSymbol aVariable,
			Expression aOffset) {
		mVariable = aVariable;
		mOffset = aOffset;
	}
	
	public VariableSymbol GetVariable() {
		return mVariable;
	}
	
	public Expression GetOffset() {
		return mOffset;
	}

	@Override
	public IntrinsicTypeE EvaluateExpressionType() {
		return mVariable.GetType().GetIntrinsicType();
	}

	@Override
	public VariableSymbol EvaluateToTemporaryVariable(
			SymbolTable aSymbolTable,
			BackendManager aWriter) {
		if (null == mOffset)
			return mVariable;

		VariableSymbol fOffset = mOffset.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		VariableSymbol fReturn = aSymbolTable.CreateTemporaryVariable(mVariable.GetType().GetIntrinsicType());
		aWriter.EmitMove(mVariable, fOffset, fReturn);
		return fReturn;
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		if ((null == mOffset) ^ mVariable.GetType().IsArray())
			aSemanticAnalyzer.AddArrayMismatch();
	}
}
