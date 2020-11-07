package SyntaxTree;

import Utilities.BackendManager;
import Utilities.SymbolTable;

public interface CompilableExpressionI extends CompilableI {
	IntrinsicTypeE EvaluateExpressionType();

	VariableSymbol EvaluateToTemporaryVariable(
			SymbolTable aSymbolTable,
			BackendManager aWriter);
}
