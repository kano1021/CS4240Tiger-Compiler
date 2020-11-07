package SyntaxTree;

import java.util.List;

import Utilities.BackendManager;
import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class InvocationStatement extends Statement {
	private VariableSymbol mAssignmentVariable = null;
	private FunctionDeclaration mFunction = null;
	private List<Expression> mvArgumentExpressions = null;
	
	public InvocationStatement(
			VariableSymbol axAssignmentVariable,
			FunctionDeclaration axFunctionName,
			List<Expression> avArgumentExpressions) {
		mAssignmentVariable = axAssignmentVariable;
		mFunction = axFunctionName;
		mvArgumentExpressions = avArgumentExpressions;
	}

	@Override
	public void Compile(
			SymbolTable aSymbolTable,
			BackendManager aWriter) {
		VariableSymbol[] fvArguments = new VariableSymbol[mvArgumentExpressions.size()];
		
		for (int fiIterator = 0; fiIterator < mvArgumentExpressions.size(); ++fiIterator)
			fvArguments[fiIterator] = mvArgumentExpressions.get(fiIterator).EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		
		aWriter.EmitCall(mFunction, mAssignmentVariable, fvArguments);
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		aSemanticAnalyzer.ValidateAssignable(mAssignmentVariable.GetType(), mFunction.GetReturnType());
		if (mvArgumentExpressions.size() == mFunction.GetArgumentCount()) {
			for (int fiIterator = 0; fiIterator < mFunction.GetArgumentCount(); ++fiIterator)
				aSemanticAnalyzer.ValidateAssignable(mFunction.GetArgument(fiIterator).GetType(),
					mvArgumentExpressions.get(fiIterator).EvaluateExpressionType());
		}
		else
			aSemanticAnalyzer.AddArgumentCountMismatch();
	}
}
