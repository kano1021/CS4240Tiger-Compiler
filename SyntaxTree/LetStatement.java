package SyntaxTree;

import java.util.List;
import Utilities.*;

public class LetStatement implements CompilableI {
	private List<TypeDeclaration> mvTypeDeclarations = null;
	private List<VariableDeclaration> mvVariableDeclarations = null;
	private List<FunctionDeclaration> mvFunctionDeclarations = null;
	private SymbolTable mSymbolTable = null;
	
	private StatementList mvStatements = null;
	
	public LetStatement(
		List<TypeDeclaration> avTypeDeclarations,
		List<VariableDeclaration> avVariableDeclarations,
		List<FunctionDeclaration> avFunctionDeclarations,
		SymbolTable aSymbolTable)
	{
		mvTypeDeclarations = avTypeDeclarations;
		mvVariableDeclarations = avVariableDeclarations;
		mvFunctionDeclarations = avFunctionDeclarations;
		mSymbolTable = aSymbolTable;
	}
	
	@Override
	public void AnalyzeSemantics(SymbolTable aSymbolTable, SemanticAnalyzer aSemanticAnalyzer) {
		for (int fiIterator = 0; fiIterator < mvTypeDeclarations.size(); ++fiIterator)
			mvTypeDeclarations.get(fiIterator).AnalyzeSemantics(aSymbolTable, aSemanticAnalyzer);
		
		for (int fiIterator = 0; fiIterator < mvVariableDeclarations.size(); ++fiIterator)
			mvVariableDeclarations.get(fiIterator).AnalyzeSemantics(aSymbolTable, aSemanticAnalyzer);
		
		for (int fiIterator = 0; fiIterator < mvFunctionDeclarations.size(); ++fiIterator)
			mvFunctionDeclarations.get(fiIterator).AnalyzeSemantics(aSymbolTable, aSemanticAnalyzer);
		
		mvStatements.AnalyzeSemantics(aSymbolTable, aSemanticAnalyzer);
	}
	
	public void Compile(
		BackendManager aWriter) {
		for (int fiIterator = 0; fiIterator < mvVariableDeclarations.size(); ++fiIterator)
			mvVariableDeclarations.get(fiIterator).Compile(aWriter);
		
		for (int fiIterator = 0; fiIterator < mvFunctionDeclarations.size(); ++fiIterator)
			mvFunctionDeclarations.get(fiIterator).Compile(null, aWriter);

		mvStatements.Compile(mSymbolTable, aWriter);
	}
	
	public int Count() {
		return mvTypeDeclarations.size();
	}
	
	public void AddBody(
			StatementList avStatements) {
		mvStatements = avStatements;
	}
	
	public SymbolTable GetSymbolTable() {
		return mSymbolTable;
	}
	
	public void PrintSymbolTable(
		int aIndentLevel) {
		mSymbolTable.Print(aIndentLevel);
		
		mvStatements.PrintSymbolTable(aIndentLevel + 1);
	}
}
