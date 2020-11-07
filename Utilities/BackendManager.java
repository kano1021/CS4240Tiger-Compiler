package Utilities;

import java.util.*;

import IRCode.*;
import SyntaxTree.*;

public class BackendManager {
	public BackendManager() {
		// Push the main function.
		mvFunctionInfo.push(new FunctionInfo("main"));
	}
	
	private List<OpCodeWriter> mvOpCodeWriters = new ArrayList<OpCodeWriter>();
	
	public void AddOpCodeWriter(
			OpCodeWriter aOpCodeWriter) {
		if (null == aOpCodeWriter) 
			throw new IllegalArgumentException();
		
		mvOpCodeWriters.add(aOpCodeWriter);
	}
	
	private Stack<FunctionInfo> mvFunctionInfo = new Stack<FunctionInfo>();
	
	public Label PushFunction(
			String axFunctionName)
	{
		String fxPreviousFunctionName = "";
		fxPreviousFunctionName = mvFunctionInfo.peek().GetName();
		
		fxPreviousFunctionName += "@" + axFunctionName;
		
		mvFunctionInfo.push(new FunctionInfo(fxPreviousFunctionName));
		EmitComment(String.format("start_function<%s>", fxPreviousFunctionName));
		Label fLabel = new Label(fxPreviousFunctionName);
		EmitLabel(fLabel);
		return fLabel;
	}
	
	public void PopFunction() {
		String fxFunctionName = mvFunctionInfo.pop().GetName();
		EmitComment(String.format("end_function<%s>", fxFunctionName));
	}
	
	public void PushLoop(
		Label aStartLoop,
		Label aEndLoop) {
		FunctionInfo fFunctionInfo = mvFunctionInfo.peek();
		fFunctionInfo.PushLoop(aStartLoop, aEndLoop);
		EmitLabel(aStartLoop);
	}
	
	public void PopLoop()
	{
		FunctionInfo fFunctionInfo = mvFunctionInfo.peek();
		EmitLabel(fFunctionInfo.PopLoop());
	}
	
	public Label CreateLabel() {
		return new Label(mvFunctionInfo.peek().NextLabelID());
	}
	
	public void EmitLabel(
			Label aLabel) {
		for (OpCodeWriter fiWriter : mvOpCodeWriters) 
			fiWriter.EmitLabel(aLabel);
	}	

	public void EmitBreak() {
		EmitJump(mvFunctionInfo.peek().PeekLoopEndLabel());
	}
	
	public void EmitComment(
		String axComment) {
		for (OpCodeWriter fiWriter : mvOpCodeWriters)
			fiWriter.EmitComment(axComment);
	}
	
	public void EmitCall(
			FunctionDeclaration aFunction,
			VariableSymbol aStoreReturn,
			VariableSymbol[] avArguments) {
		for (OpCodeWriter fiWriter : mvOpCodeWriters)
			fiWriter.EmitCall(aFunction, aStoreReturn, avArguments);
	}

	public void EmitBranchEqual(
		VariableSymbol aToTest,
		int aEqualTo,
		Label aBranchTo) {
		for (OpCodeWriter fiWriter : mvOpCodeWriters)
			fiWriter.EmitBranchEqual(aToTest, aEqualTo, aBranchTo);
	}
		
	public void EmitJump(
		Label aLabel) {
		for (OpCodeWriter fiWriter : mvOpCodeWriters)
			fiWriter.EmitJump(aLabel);
	}
		
	public void EmitMove(
		VariableSymbol aSource,
		VariableSymbol aOffset,
		VariableSymbol aDestination) {
		for (OpCodeWriter fiWriter : mvOpCodeWriters)
			fiWriter.EmitMove(aSource, aOffset, aDestination);
	}
	
	public void EmitOperation(
		OperatorE aOperator,
		VariableSymbol aLHS,
		VariableSymbol aRHS,
		VariableSymbol aStore) {
		for (OpCodeWriter fiWriter : mvOpCodeWriters)
			fiWriter.EmitOperation(aOperator, aLHS, aRHS, aStore);
	}
		
	public void EmitOperation(
		OperatorE aOperator,
		VariableSymbol aLHS,
		String axRHS,
		VariableSymbol aStore) {
		for (OpCodeWriter fiWriter : mvOpCodeWriters)
			fiWriter.EmitOperation(aOperator, aLHS, axRHS, aStore);
	}
	
	public void EmitReturn(
		VariableSymbol aReturnValue) {
		for (OpCodeWriter fiWriter : mvOpCodeWriters)
			fiWriter.EmitReturn(aReturnValue);
	}
	
	public void EmitSet(
		VariableSymbol aDestination,
		String axOffset,
		VariableSymbol aSource) {
		for (OpCodeWriter fiWriter : mvOpCodeWriters)
			fiWriter.EmitSet(aDestination, axOffset, aSource);
	}
	
	public void EmitSet(
		VariableSymbol aDestination,
		String axOffset,
		ConstantLiteral aSource) {
		for (OpCodeWriter fiWriter : mvOpCodeWriters)
			fiWriter.EmitSet(aDestination, axOffset, aSource);
	}
}
