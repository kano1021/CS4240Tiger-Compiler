package SyntaxTree;

import Utilities.*;

public class ConstantSymbol extends VariableSymbol {
	public ConstantSymbol(
			String axValue) {
		super(axValue);
	}
	
	@Override
	public void Compile(SymbolTable aSymbolTable, BackendManager aWriter) {
		// Constants don't have anything to compile.
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		// Here for future purposes.
	}
}
