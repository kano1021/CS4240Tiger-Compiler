package SyntaxTree;

import Utilities.*;

public interface CompilableStatementI extends CompilableI {
	void Compile(
			SymbolTable aSymbolTable,
			BackendManager aWriter);
}
