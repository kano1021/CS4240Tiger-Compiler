package SyntaxTree;

import Utilities.BackendManager;
import Utilities.SemanticAnalyzer;
import Utilities.SymbolTable;

public class TypeDeclaration extends Symbol {
	public TypeDeclaration(
			String axName,
			Type aUnderlyingType) {
		mxName = axName;
		mUnderlyingType = aUnderlyingType;
	}
	
	public void Compile(
		BackendManager aWriter) {
		// Nothing to do since these are only allocations.
	}
	
	private String mxName = null;
	
	public String GetName() {
		return mxName;
	}
	
	public String SetName(
			String axName) {
		mxName = axName;
		return mxName;
	}
	
	private Type mUnderlyingType = null;
	
	public Type GetUnderlyingType() {
		return mUnderlyingType;
	}

	@Override
	public void Compile(SymbolTable aSymbolTable, BackendManager aWriter) {
		// Types don't have anything to compile.
	}

	@Override
	public void AnalyzeSemantics(SymbolTable aSymbolTable, SemanticAnalyzer aSemanticAnalyzer) {
		// Here for future purposes.
	}
}
