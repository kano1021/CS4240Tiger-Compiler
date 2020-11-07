package SyntaxTree;

import IRCode.*;
import Utilities.*;

public class ForStatement extends LoopStatement {
	private VariableDeclaration mIterator = null;
	private Expression mFromExpression = null;
	private Expression mToExpression = null;
	private StatementList mBody = null;
	
	public ForStatement(
			VariableDeclaration aIterator,
			Expression aFromExpression,
			Expression aToExpression,
			StatementList aBody) {
		mIterator = aIterator;
		mFromExpression = aFromExpression;
		mToExpression = aToExpression;
		mBody = aBody;
	}

	@Override
	public void Compile(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		
		Label fStartOfFor = aWriter.CreateLabel(),
				fEndOfFor = aWriter.CreateLabel();
		
		VariableSymbol fFrom = mFromExpression.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		aWriter.EmitSet(mIterator, "0", fFrom);
		
		aWriter.PushLoop(fStartOfFor, fEndOfFor);
		VariableSymbol fTo = mToExpression.EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		VariableSymbol fResult = aSymbolTable.CreateTemporaryVariable(IntrinsicTypeE.Boolean);
		aWriter.EmitOperation(OperatorE.LessThanOrEqualTo, mIterator, fTo, fResult);
		aWriter.EmitBranchEqual(fResult, 0, fEndOfFor);
		mBody.Compile(aSymbolTable, aWriter);
		aWriter.EmitOperation(OperatorE.Add, mIterator, "1", mIterator);
		aWriter.PopLoop();
	}

	@Override
	public void AnalyzeSemantics(
			SymbolTable aSymbolTable,
			SemanticAnalyzer aSemanticAnalyzer) {
		if ((IntrinsicTypeE.Int != mFromExpression.EvaluateExpressionType())
			|| (IntrinsicTypeE.Int != mToExpression.EvaluateExpressionType()))
			aSemanticAnalyzer.AddTypeMismatch();
		
		aSemanticAnalyzer.PushLoop();
		mBody.AnalyzeSemantics(aSymbolTable, aSemanticAnalyzer);
		aSemanticAnalyzer.PopLoop();
	}
}
