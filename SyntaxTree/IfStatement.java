package SyntaxTree;

import IRCode.*;
import Utilities.*;

public class IfStatement extends Statement {
	private Expression mConditional = null;
	private StatementList mThen = null;
	private StatementList mElse = null;
	
	public IfStatement(
			Expression aConditional,
			StatementList aThen,
			StatementList aElse) {
		mConditional = aConditional;
		mThen = aThen;
		mElse = aElse;
	}

	@Override
	public void Compile(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		Label fEnd = aWriter.CreateLabel(),
				fFalse = null;

		fFalse = (null == mElse) ? fEnd : aWriter.CreateLabel();
		
		VariableSymbol fConditional = mConditional.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		aWriter.EmitBranchEqual(fConditional, 0, fFalse);
		mThen.Compile(aSymbolTable, aWriter);
		if (null != mElse)
		{
			aWriter.EmitJump(fEnd);
			aWriter.EmitLabel(fFalse);
			mElse.Compile(aSymbolTable, aWriter);
		}
		aWriter.EmitLabel(fEnd);
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		if (IntrinsicTypeE.Boolean != mConditional.EvaluateExpressionType())
			aSemanticAnalyzer.AddTypeMismatch();
	}
}
