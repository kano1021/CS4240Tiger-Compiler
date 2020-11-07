package Utilities;

import java.io.PrintWriter;
import java.util.Stack;

import IRCode.FunctionInfo;
import IRCode.Label;
import SyntaxTree.ConstantLiteral;
import SyntaxTree.FunctionDeclaration;
import SyntaxTree.OperatorE;
import SyntaxTree.VariableSymbol;

public class IRWriter extends OpCodeWriter {
	private PrintWriter mOutput = null;
	
	public IRWriter(
		PrintWriter aOut) {
		mOutput = aOut;
	}
	
	private void PrintLine(
			String axLine) {
		mOutput.println(axLine);
	}
		
	public void EmitCall(
			FunctionDeclaration aFunction,
			VariableSymbol aStoreReturn,
			VariableSymbol[] avArguments) {
		String fxOperation = "call";
		if (null != aStoreReturn)
			fxOperation += String.format("r, %s", aStoreReturn.GetName());
		
		fxOperation += String.format(", %s", aFunction.GetName());
		
		for (int fiIterator = 0; fiIterator < avArguments.length; ++fiIterator)
			fxOperation += String.format(", %s", avArguments[fiIterator].GetName());
		
		PrintLine(fxOperation);
	}
		
	public void EmitBranchEqual(
			VariableSymbol aToTest,
			int aEqualTo,
			Label aBranchTo) {
		String fxOperation = String.format("breq %s, %d, %s", aToTest.GetName(), aEqualTo, aBranchTo.GetName());
		PrintLine(fxOperation);
	}
	
	public void EmitComment(
			String axComment) {
		PrintLine("#" + axComment);
	}
		
	public void EmitJump(
			Label aLabel) {
		PrintLine("jmp " + aLabel.GetName());
	}
	
	public void EmitLabel(
			Label aLabel) {
		PrintLine(aLabel.GetName() + ":");
	}
	
	public void EmitMove(
			VariableSymbol aSource,
			VariableSymbol aOffset,
			VariableSymbol aDestination) {
		PrintLine(String.format("mv %s, %s, %s", aSource.GetName(), aOffset.GetName(), aDestination.GetName()));
	}
	
	public void EmitOperation(
			OperatorE aOperator,
			VariableSymbol aLHS,
			VariableSymbol aRHS,
			VariableSymbol aStore) {
		EmitOperation(aOperator, aLHS, aRHS.GetName(), aStore);
	}
		
	public void EmitOperation(
			OperatorE aOperator,
			VariableSymbol aLHS,
			String axRHS,
			VariableSymbol aStore) {
		String fxOperation = OperandToString(aOperator);
		if (null == fxOperation)
			throw new UnsupportedOperationException("Operator not found.");
		
		fxOperation += String.format(" %s, %s, %s", aLHS.GetName(), axRHS, aStore.GetName());
		PrintLine(fxOperation);
	}
	
	public void EmitReturn(
			VariableSymbol aReturnValue) {
		String fxOperation = "ret";
		
		if (null != aReturnValue)
			fxOperation += String.format(" %s", aReturnValue.GetName());
		
		PrintLine(fxOperation);
	}
	
	public void EmitSet(
			VariableSymbol aDestination,
			String axOffset,
			VariableSymbol aSource) {
		EmitSet(aDestination, axOffset, aSource.GetName());
	}
	
	public void EmitSet(
			VariableSymbol aDestination,
			String axOffset,
			ConstantLiteral aSource) {
		EmitSet(aDestination, axOffset, aSource.GetStringValue());
	}
	
	private void EmitSet(
			VariableSymbol aDestination,
			String axOffset,
			String axSource) {
		PrintLine(String.format("set %s, %s, %s", aDestination.GetName(), axOffset, axSource));
	}
	
	private static String OperandToString(
			OperatorE aOperand) {
		switch (aOperand) {
			case Add: return "add";
			case And: return "and";
			case Subtract: return "sub";
			case Multiply: return "mul";
			case Divide: return "div";
			case Power: return "pow";	
			case Equal: return "eq";	
			case NotEqual: return "neq";			
			case LessThan: return "lt";	
			case LessThanOrEqualTo: return "lteq";			
			case GreaterThan: return "gt";
			case GreaterThanOrEqualTo: return "gteq";
			case Or: return "or";
		}
		
		return null;
	}
}
