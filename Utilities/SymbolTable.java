package Utilities;

import java.util.*;

import SyntaxTree.*;

public class SymbolTable {
	private SymbolTable(
			SymbolTable aUnderlyingFrame) {
		mUnderlyingScope = aUnderlyingFrame;
	}

	public static SymbolTable GetGlobal() {
		return new SymbolTable(null);
	}
	
	private Dictionary<String, Symbol> mDictionary = new Hashtable<String, Symbol>();
	
	private SymbolTable mUnderlyingScope = null;
	
	public Symbol GetSymbol(
			String axID) {
		Symbol fReturn = mDictionary.get(axID);
		if ((null == fReturn) && (null != mUnderlyingScope))
			return mUnderlyingScope.GetSymbol(axID);
		
		return fReturn;
	}
	
	public void PutSymbol(
			String axID,
			Symbol aSymbol) {
		if (null != mDictionary.get(axID))
			AddDuplicateSymbol(axID);
		else 
			mDictionary.put(axID,  aSymbol);
	}
	
	private int mTemporaryVariableID = 0;
	
	public VariableDeclaration CreateTemporaryVariable(
		IntrinsicTypeE aType) {
		String fxID = "%temp" + Integer.toString(mTemporaryVariableID++);
		VariableDeclaration fReturn = new VariableDeclaration(fxID);
		this.PutSymbol(fxID, fReturn);
		return fReturn;
	}
	
	public VariableSymbol GetVariable(
			String axID) {
		Symbol fSymbol = GetSymbol(axID);
		if ((null != fSymbol) && (fSymbol instanceof VariableSymbol))
			return (VariableSymbol)fSymbol;
		
		return null;
	}
	
	public FunctionDeclaration GetFunction(
			String axID) {
		Symbol fSymbol = GetSymbol(axID);
		if ((null != fSymbol) && (fSymbol instanceof FunctionDeclaration))
			return (FunctionDeclaration)fSymbol;
		
		return null;
	}
	
	public TypeDeclaration GetType(
			String axID) {
		Symbol fSymbol = GetSymbol(axID);
		if ((null != fSymbol) && (fSymbol instanceof TypeDeclaration))
			return (TypeDeclaration)fSymbol;
		
		return null;
	}
	
	public SymbolTable PushScope() {
		return new SymbolTable(this);
	}
	
	private int mErrorCount = 0;
	
	public int GetErrorCount() {
		return (null == mUnderlyingScope) ? mErrorCount : mUnderlyingScope.GetErrorCount();
	}
	
	public void AddDuplicateSymbol(
			String axID) {
		if (null == mUnderlyingScope) {
			++mErrorCount;
			System.out.printf("Duplicate Symbol '%s'\n", axID);
		}
		else
			mUnderlyingScope.AddDuplicateSymbol(axID);
	}
	
	public void AddVariableIsNotAnArray(
			String axID) {
		if (null == mUnderlyingScope) {
			++mErrorCount;
			System.out.printf("Variable '%s' is not an array\n", axID);
		}
		else
			mUnderlyingScope.AddVariableIsNotAnArray(axID);
	}
	
	public void AddUnresolvedFunction(
			String axID) {
		if (null == mUnderlyingScope) {
			++mErrorCount;
			System.out.printf("Unresolved Function '%s'\n", axID);
		}
		else
			mUnderlyingScope.AddUnresolvedFunction(axID);
	}
	
	public void AddUnresolvedType(
			String axID) {
		if (null == mUnderlyingScope) {
			++mErrorCount;
			System.out.printf("Unresolved Type '%s'\n", axID);
		}
		else
			mUnderlyingScope.AddUnresolvedType(axID);
	}
	
	public void AddUnresolvedVariable(
			String axID) {
		if (null == mUnderlyingScope) {
			++mErrorCount;
			System.out.printf("Unresolved Variable '%s'\n", axID);
		}
		else
			mUnderlyingScope.AddUnresolvedVariable(axID);
	}
	
	public void Print(
		int aIndentLevel) {
		DumpLine(aIndentLevel, "Scope:");
		Enumeration<String> fvSymbols = mDictionary.keys();
//		while (fvSymbols.hasMoreElements()) {
//			String fxKey = fvSymbols.nextElement();
//			Symbol fiSymbol = mDictionary.get(fxKey);
//			String fxInfo = "";
//			if (fiSymbol instanceof FunctionDeclaration) { 
//				FunctionDeclaration fFuncDecl = (FunctionDeclaration)fiSymbol;
//				fxInfo = String.format("%s, func, %s", fxKey, fFuncDecl.GetReturnType().GetIntrinsicType());
//			} else if (fiSymbol instanceof VariableSymbol) {
//				VariableSymbol fiVarSym = (VariableSymbol)fiSymbol;
//				fxInfo = String.format("%s, var, %s", fxKey, fiVarSym.GetType().GetIntrinsicType());
//			} else if (fiSymbol instanceof TypeDeclaration) {
//				TypeDeclaration fiTypeDecl = (TypeDeclaration)fiSymbol;
//				fxInfo = String.format("%s, type, %s",  fxKey, fiTypeDecl.GetUnderlyingType());
//			}
//			
//			DumpLine(aIndentLevel + 1, fxInfo);
//		}
	}
	
	private void DumpLine(
		int aIndentLevel,
		String axText) {
		for (int fiIterator = 0; fiIterator < aIndentLevel; ++fiIterator)
			System.out.print('\t');
		
		System.out.println(axText);
	}
}