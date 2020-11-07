package SyntaxTree;

import Utilities.*;

public class ConstantLiteral extends UnaryExpression {
	private boolean mqInteger = false;
	
	private int mInteger = 0;
	private float mFloat = 0f;
	
	public ConstantLiteral(
			int aInteger) {
		mqInteger = true;
		mInteger = aInteger;
	}
	
	public ConstantLiteral(
			float aFloat) {
		mqInteger = false;
		mFloat = aFloat;
	}
	
	public String GetStringValue() {
		return mqInteger ? Integer.toString(mInteger) : Float.toString(mFloat);
	}
	
	@Override public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		// Nothing to do.
	}
	
	@Override public VariableSymbol EvaluateToTemporaryVariable(
		SymbolTable aSymbolTable,
		BackendManager aOpCodeWriter) {
		return new ConstantSymbol(this.GetStringValue());
	}

	@Override
	public IntrinsicTypeE EvaluateExpressionType() {
		return mqInteger ? IntrinsicTypeE.Int : IntrinsicTypeE.Float;
	}
}
