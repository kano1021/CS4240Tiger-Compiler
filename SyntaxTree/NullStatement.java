package SyntaxTree;

import Utilities.*;

public class NullStatement extends Statement {
	@Override
	public void AnalyzeSemantics(
			SymbolTable aSymbolTable,
			SemanticAnalyzer aSemanticAnalyzer) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void Compile(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		throw new UnsupportedOperationException();		
	}
}
