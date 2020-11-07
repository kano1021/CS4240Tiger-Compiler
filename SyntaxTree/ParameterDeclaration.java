package SyntaxTree;

import Utilities.BackendManager;
import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class ParameterDeclaration extends VariableSymbol {	
	public ParameterDeclaration(
			String axName) {
		super(axName);
	}

	@Override
	public void Compile(SymbolTable aSymbolTable, BackendManager aWriter) {
		// Parameters don't have anything to compile.
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		// Here for future purposes.
	}
}
