package SyntaxTree;

import Utilities.*;

public class BreakStatement extends Statement {

	@Override
	public void Compile(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		aWriter.EmitBreak();
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		aSemanticAnalyzer.ValidateInLoop();
	}
}
