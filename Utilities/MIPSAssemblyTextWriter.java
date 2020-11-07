package Utilities;

import java.io.PrintWriter;
import IRCode.Label;
import SyntaxTree.OperatorE;

public class MIPSAssemblyTextWriter {
	private PrintWriter mOutput = null;
	
	private String mxPowerFunction = null;
	
	public MIPSAssemblyTextWriter(
		PrintWriter aOut) {
		mOutput = aOut;
		mxPowerFunction = "pow";

		Label fPowerStartLabel = new Label(mxPowerFunction);
		Label fPowerEndLabel = new Label("pow_end");
		EmitLabel(fPowerStartLabel);
		// $2 = Return value (accumulator) = Holds x * x * ... * x.
		// $3 = Multiplicand = The register containing the original value, pre-multiplication.
		// $4 = Power = The register containing the power. Note value is clobbered.
		EmitBranchEqual(4, 0, fPowerEndLabel);
		EmitOperation(OperatorE.Multiply, 2, 4, 2);
		PrintLine("addi $4, $4, -1");
		EmitJump(fPowerStartLabel);
		EmitLabel(fPowerEndLabel);
	}
	
	private void PrintLine(
			String axLine) {
		mOutput.println(axLine);
	}
		
	public void EmitCall(
			String axFunction) {		
		PrintLine(String.format("call %s", axFunction));
	}
		
	public void EmitBranchEqual(
			int aToTest,
			int aEqualTo,
			Label aBranchTo) {
		String fxOperation = String.format("breq $%d, $%d, %s", aToTest, aEqualTo, aBranchTo.GetName());
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
	
	// Implements *($aFromRegister + aOffset)
	public void EmitLoad(
			int aFromRegister,
			int aOffset,
			int aToRegister) {
		PrintLine(String.format("lw $%d, %d($%d)", aToRegister, aOffset, aFromRegister));
	}
	
	public void EmitMove(
			int aSource,
			int aOffset,
			int aDestination) {
		PrintLine(String.format("mv %d, %d, %d", aSource, aOffset, aDestination));
	}
	
	// TODO:	Refactor this to EmitMove after EmitMove/EmitSet are cleaned up.
	public void EmitMoveRegister(
			int aSourceRegister,
			int aDestinationRegister) {
		PrintLine(String.format("add $%d, $%d, $0", aDestinationRegister, aSourceRegister));
	}
		
	public void EmitOperation(
			OperatorE aOperator,
			int aLHS,
			int aRHS,
			int aStore) {
		if (OperatorE.Power == aOperator) {
			EmitPush(3);
			EmitPush(4);
			EmitMoveRegister(aLHS, 3);	// Multiplicand
			EmitMoveRegister(aRHS, 4);	// Power
			EmitCall(mxPowerFunction);
			EmitMoveRegister(2, aStore);
			EmitPop(4);
			EmitPop(3);
		} else {
			String fxOperation = OperandToString(aOperator);
			if (null == fxOperation)
				throw new UnsupportedOperationException("Operator not found.");
			
			fxOperation += String.format(" $%d, $%d, $%d", aLHS, aRHS, aStore);
			PrintLine(fxOperation);
		}
	}
	
	public void EmitReturn(
			String aReturnValue) {
		String fxOperation = "ret";
		
		if (null != aReturnValue)
			fxOperation += String.format(" %s", aReturnValue);
		
		PrintLine(fxOperation);
	}
	
	public void EmitSet(
			int aDestination,
			String axOffset,
			int axSource) {
		PrintLine(String.format("set %d, %s, %d", aDestination, axOffset, axSource));
	}
		
	// Implements *($aToRegister + aOffset) = $aFromOffset
	public void EmitStore(
			int aFromRegister,
			int aToRegister,
			int aOffset)
	{
		PrintLine(String.format("sw $%d, %d($%d)",aFromRegister, aOffset, aToRegister));
	}
	
	public void EmitPop(
			int aToRegister) {
		PrintLine(String.format("lw $%d, ($sp)", aToRegister));
		PrintLine(String.format("addi $sp, $sp, 4"));
	}

	public void EmitPush(
			int aFromRegister) {
		PrintLine(String.format("addi $sp, $sp, -4"));
		PrintLine(String.format("lw $%d, ($sp)", aFromRegister));
	}
	
	public void EmitLoadImmediate(
			int aToRegister,
			int aImmediateValue) {
		PrintLine(String.format("addi $%d, $0, %d", aToRegister, aImmediateValue));
	}
	
	private static String OperandToString(
			OperatorE aOperand) {
		switch (aOperand) {
			case Add: return "add";
			case And: return "and";
			case Subtract: return "sub";
			case Multiply: return "mul";
			case Divide: return "div";
			case Equal: return "eq";	
			case NotEqual: return "ne";			
			case LessThan: return "lt";	
			case LessThanOrEqualTo: return "le";			
			case GreaterThan: return "gt";
			case GreaterThanOrEqualTo: return "ge";
			case Or: return "or";
		}
		
		return null;
	}
}
