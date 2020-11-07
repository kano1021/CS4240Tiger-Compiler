package ControlFlowGraph;

import SyntaxTree.OperatorE;
import SyntaxTree.VariableSymbol;

public class CFGOperation extends CFGBase {
	public OperatorE Operator = null;
	
	public VariableSymbol LHS = null;
	public VariableSymbol RHSSymbol = null;
	public String RHSConstant = null;
	public VariableSymbol Store = null;
	
	public CFGOperation(
		OperatorE aOperator,
		VariableSymbol aLHS,
		VariableSymbol aRHS,
		VariableSymbol aStore) {
		this.Operator = aOperator;
		this.LHS = aLHS;
		this.RHSSymbol = aRHS;
		this.Store = aStore;
	}
	
	public CFGOperation(
		OperatorE aOperator,
		VariableSymbol aLHS,
		String axRHS,
		VariableSymbol aStore) {
		this.Operator = aOperator;
		this.LHS = aLHS;
		this.RHSConstant = axRHS;
		this.Store = aStore;
	}

	@Override
	public boolean ComputeLiveness() {
		boolean fqReturn = false;
		fqReturn |= this.Def.add(this.Store);
		fqReturn |= this.Use.add(this.LHS);
		if (null != this.RHSSymbol)
			fqReturn |= this.Use.add(this.RHSSymbol);
		return fqReturn;
	}

	@Override
	public String[] GetUsedVariables() {
		return (null == this.RHSSymbol) ? new String[] { this.LHS.GetName() } : new String[] {this.LHS.GetName(), this.RHSSymbol.GetName() };
	}
}
