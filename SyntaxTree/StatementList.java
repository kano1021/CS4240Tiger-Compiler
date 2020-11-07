package SyntaxTree;

import java.util.List;

import Utilities.BackendManager;
import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class StatementList implements CompilableStatementI {
	private List<Statement> mvStatements = null;
	
	public StatementList(
			List<Statement> avStatements) {
		mvStatements = avStatements;
	}
	
	@Override
	public void AnalyzeSemantics(
			SymbolTable aSymbolTable,
			SemanticAnalyzer aSemanticAnalyzer) {
		for (int fiIterator = 0; fiIterator < mvStatements.size(); ++fiIterator)
			mvStatements.get(fiIterator).AnalyzeSemantics(aSymbolTable, aSemanticAnalyzer);
	}

	@Override
	public void Compile(
			SymbolTable aSymbolTable,
			BackendManager aWriter) {
		for (int fiIterator = 0; fiIterator < mvStatements.size(); ++fiIterator) 
			mvStatements.get(fiIterator).Compile(aSymbolTable, aWriter);		
	}
	
	public void PrintSymbolTable(
		int aIndentLevel)
	{
		for (int fiIterator = 0; fiIterator < mvStatements.size(); ++fiIterator) {
			Statement fiStatement = mvStatements.get(fiIterator);
			if (!(fiStatement instanceof DeclarationStatement))
				continue;
			DeclarationStatement fiLet = (DeclarationStatement)fiStatement;
			fiLet.PrintSymbolTable(aIndentLevel);
		}
	}
}
