package SyntaxTree;

import java.util.List;

import Utilities.*;

public abstract class ExpressionBase<tChild extends Expression> extends Expression {
	protected List<tChild> mvBranches = null;
	
	protected ExpressionBase(
			List<tChild> avBranches) {
		mvBranches = avBranches;
	}
	
	@Override
	public IntrinsicTypeE EvaluateExpressionType() {
		return (1 == mvBranches.size()) ? mvBranches.get(0).EvaluateExpressionType()
				: IntrinsicTypeE.Boolean;
	}

	@Override
	public VariableSymbol EvaluateToTemporaryVariable(
			SymbolTable aSymbolTable,
			BackendManager aWriter) {
		if (1 == mvBranches.size())
			return mvBranches.get(0).EvaluateToTemporaryVariable(aSymbolTable, aWriter);

		VariableSymbol fLHS = mvBranches.get(0).EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		VariableSymbol fRHS = mvBranches.get(1).EvaluateToTemporaryVariable(aSymbolTable, aWriter);
		VariableSymbol fReturn = aSymbolTable.CreateTemporaryVariable(IntrinsicTypeE.Boolean);
		aWriter.EmitOperation(GetOperator(), fLHS, fRHS, fReturn);
		return fReturn;
	}
	
	protected abstract OperatorE GetOperator();
}