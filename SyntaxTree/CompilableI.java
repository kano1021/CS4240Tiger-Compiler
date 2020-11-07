package SyntaxTree;

import Utilities.*;

public interface CompilableI {
	void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer);
}
