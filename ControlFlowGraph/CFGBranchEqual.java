package ControlFlowGraph;

import java.util.Dictionary;

import IRCode.Label;
import SyntaxTree.VariableSymbol;

public class CFGBranchEqual extends CFGBase {
	public VariableSymbol ToTest = null;
	public int EqualTo = 0;
	public Label BranchTo = null;
	
	public CFGBranchEqual(
		VariableSymbol aToTest,
		int aEqualTo,
		Label aBranchTo) {
		this.ToTest = aToTest;
		this.EqualTo = aEqualTo;
		this.BranchTo = aBranchTo;
	}

	@Override
	public boolean ComputeLiveness() {
		return this.Use.add(this.ToTest);
	}

	@Override
	public String[] GetUsedVariables() {
		return new String[] { this.ToTest.GetName() };
	}
}
