package SyntaxTree;

import java.util.List;

import Utilities.*;

public class FunctionDeclaration extends Symbol {
	private String mxName = null;
	private Type mReturnType = null;
	
	private List<ParameterDeclaration> mvParameters = null;
	
	private SymbolTable mSymbolTable = null;
	
	private StatementList mvStatements = null;
	
	public FunctionDeclaration(
			List<ParameterDeclaration> avParameters,
			SymbolTable aSymbolTable) {
		mvParameters = avParameters;
		mSymbolTable = aSymbolTable;
	}
	
	public String GetName() {
		return mxName;
	}
	
	public String SetName(
			String axName) {
		mxName = axName;
		return mxName;
	}
	
	public Type GetReturnType() {
		return mReturnType;
	}
	
	public Type SetReturnType(
			Type aReturnType) {
		mReturnType = aReturnType;
		return aReturnType;
	}
	
	public int GetArgumentCount() {
		return mvParameters.size();
	}
	
	public ParameterDeclaration GetArgument(
		int aIndex) {
		return mvParameters.get(aIndex);
	}
	
	public StatementList SetStatements(
			StatementList avStatements) {
		mvStatements = avStatements;
		return avStatements;
	}

	@Override
	public void Compile(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		aWriter.PushFunction(mxName);
		mvStatements.Compile(mSymbolTable, aWriter);
		aWriter.PopFunction();		
	}
	
	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		aSemanticAnalyzer.PushFunction(mxName, mReturnType);
		mvStatements.AnalyzeSemantics(mSymbolTable, aSemanticAnalyzer);
		aSemanticAnalyzer.PopFunction();
	}
}
