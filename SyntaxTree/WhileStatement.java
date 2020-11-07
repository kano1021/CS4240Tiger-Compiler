package SyntaxTree;

import IRCode.*;
import Utilities.*;

public class WhileStatement extends LoopStatement {
	private Expression mConditional = null;
	private StatementList mBody = null;
	
	public WhileStatement(
			Expression aConditional,
			StatementList aBody) {
		mConditional = aConditional;
		mBody = aBody;
	}
	
	@Override
	public void AnalyzeSemantics(
			SymbolTable aSymbolTable,
			SemanticAnalyzer aSemanticAnalyzer) {
		if (IntrinsicTypeE.Boolean != mConditional.EvaluateExpressionType())
			aSemanticAnalyzer.AddExpressionNotBoolean();
		
		aSemanticAnalyzer.PushLoop();
		mBody.AnalyzeSemantics(aSymbolTable, aSemanticAnalyzer);
		aSemanticAnalyzer.PopLoop();
	}

	@Override
	public void Compile(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		Label fStartOfWhile = aWriter.CreateLabel(),
				fEndOfWhile = aWriter.CreateLabel();
			
		aWriter.PushLoop(fStartOfWhile, fEndOfWhile);
		VariableSymbol fConditional = mConditional.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		aWriter.EmitBranchEqual(fConditional, 0, fEndOfWhile);
		mBody.Compile(aSymbolTable, aWriter);
		aWriter.PopLoop();	
	}
}
