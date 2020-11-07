package SyntaxTree;

import java.util.List;

import Utilities.*;

public class VariableDeclaration extends VariableSymbol {	
	public VariableDeclaration(
			String axID) {
		super(axID);
	}
	
	public void Compile(
		BackendManager aWriter) {
		// Nothing to do since these are only allocations.
	}

	private ConstantLiteral mInitialValue = null;

	public ConstantLiteral SetInitialValue(
			ConstantLiteral aInitialValue) {
		mInitialValue = aInitialValue;
		return aInitialValue;
	}
	
	@Override
	public void Compile(
		SymbolTable aSymbolTable,
		BackendManager aWriter) {
		if (null != mInitialValue)
			aWriter.EmitSet(this, "0", mInitialValue);
	}

	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		// Here for future purposes.
	}
}
