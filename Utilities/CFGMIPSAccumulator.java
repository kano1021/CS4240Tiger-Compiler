package Utilities;

import java.util.ArrayList;
import java.util.List;

import ControlFlowGraph.*;
import IRCode.Label;
import SyntaxTree.ConstantLiteral;
import SyntaxTree.FunctionDeclaration;
import SyntaxTree.OperatorE;
import SyntaxTree.VariableSymbol;

public class CFGMIPSAccumulator extends OpCodeWriter {
	private List<CFGBase> mvInstructions = new ArrayList<CFGBase>();

	@Override
	public void EmitCall(FunctionDeclaration aFunction, VariableSymbol aStoreReturn, VariableSymbol[] avArguments) {
		mvInstructions.add(new CFGCall(aFunction.GetName(), aStoreReturn, avArguments));
	}

	@Override
	public void EmitBranchEqual(VariableSymbol aToTest, int aEqualTo, Label aBranchTo) {
		mvInstructions.add(new CFGBranchEqual(aToTest, aEqualTo, aBranchTo));
	}

	@Override
	public void EmitComment(String axComment) {
		// Nothing to do.
	}

	@Override
	public void EmitJump(Label aLabel) {
		mvInstructions.add(new CFGJump(aLabel));
	}

	@Override
	public void EmitLabel(Label aLabel) {
		mvInstructions.add(new CFGLabel(aLabel));
	}

	@Override
	public void EmitMove(VariableSymbol aSource, VariableSymbol aOffset, VariableSymbol aDestination) {
		mvInstructions.add(new CFGMove(aSource, aOffset, aDestination));
	}

	@Override
	public void EmitOperation(OperatorE aOperator, VariableSymbol aLHS, VariableSymbol aRHS, VariableSymbol aStore) {
		mvInstructions.add(new CFGOperation(aOperator, aLHS, aRHS, aStore));
	}

	@Override
	public void EmitOperation(OperatorE aOperator, VariableSymbol aLHS, String axRHS, VariableSymbol aStore) {
		mvInstructions.add(new CFGOperation(aOperator, aLHS, axRHS, aStore));
	}

	@Override
	public void EmitReturn(VariableSymbol aReturnValue) {
		mvInstructions.add(new CFGReturn(aReturnValue));
	}

	@Override
	public void EmitSet(VariableSymbol aDestination, String axOffset, VariableSymbol aSource) {
		mvInstructions.add(new CFGSet(aDestination, axOffset, aSource));
	}

	@Override
	public void EmitSet(VariableSymbol aDestination, String axOffset, ConstantLiteral aSource) {
		mvInstructions.add(new CFGSet(aDestination, axOffset, aSource));
	}

	public List<BasicBlock> ConvertToBasicBlocks() {
		BasicBlockBuilder fBuilder = new BasicBlockBuilder(mvInstructions);
		return fBuilder.Build();
	}
}
