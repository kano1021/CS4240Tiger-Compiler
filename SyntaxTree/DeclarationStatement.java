package SyntaxTree;

import Utilities.BackendManager;
import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class DeclarationStatement extends Statement {
	private LetStatement mDeclarationSegment = null;
	private StatementList mStatements = null;
	
	public DeclarationStatement(
			LetStatement aDeclarationSegment,
			StatementList avStatements) {
		mDeclarationSegment = aDeclarationSegment;
		mStatements = avStatements;
	}

	@Override
	public void Compile(
			SymbolTable aSymbolTable,
			BackendManager aWriter) {
		mDeclarationSegment.Compile(aWriter);
		mStatements.Compile(aSymbolTable, aWriter);
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		mDeclarationSegment.AnalyzeSemantics(null, aSemanticAnalyzer);
		mStatements.AnalyzeSemantics(aSymbolTable, aSemanticAnalyzer);
	}
	
	public void PrintSymbolTable(
		int aIndentLevel) {
		mDeclarationSegment.PrintSymbolTable(aIndentLevel);
	}
}
