package Utilities;

import IRCode.Label;
import SyntaxTree.ConstantLiteral;
import SyntaxTree.FunctionDeclaration;
import SyntaxTree.OperatorE;
import SyntaxTree.VariableSymbol;

public abstract class OpCodeWriter {	
	public abstract void EmitCall(
			FunctionDeclaration aFunction,
			VariableSymbol aStoreReturn,
			VariableSymbol[] avArguments);

	public abstract void EmitBranchEqual(
		VariableSymbol aToTest,
		int aEqualTo,
		Label aBranchTo);
		
	public abstract void EmitComment(
		String axComment);
	
	public abstract void EmitJump(
		Label aLabel);
	
	public abstract void EmitLabel(
		Label aLabel);
	
	public abstract void EmitMove(
		VariableSymbol aSource,
		VariableSymbol aOffset,
		VariableSymbol aDestination);
	
	public abstract void EmitOperation(
		OperatorE aOperator,
		VariableSymbol aLHS,
		VariableSymbol aRHS,
		VariableSymbol aStore);
		
	public abstract void EmitOperation(
		OperatorE aOperator,
		VariableSymbol aLHS,
		String axRHS,
		VariableSymbol aStore);
	
	public abstract void EmitReturn(
		VariableSymbol aReturnValue);
	
	public abstract void EmitSet(
		VariableSymbol aDestination,
		String axOffset,
		VariableSymbol aSource);
	
	public abstract void EmitSet(
		VariableSymbol aDestination,
		String axOffset,
		ConstantLiteral aSource);
}
