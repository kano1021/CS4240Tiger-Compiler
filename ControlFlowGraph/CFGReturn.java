package ControlFlowGraph;

import SyntaxTree.VariableSymbol;

public class CFGReturn extends CFGBase {
	public VariableSymbol ReturnValue = null;
	
	public CFGReturn(
			VariableSymbol aReturnValue) {
		this.ReturnValue = aReturnValue;
	}

	@Override
	public boolean ComputeLiveness() {
		if (null == this.ReturnValue)
			return false;
		
		return this.Def.add(this.ReturnValue);
	}

	@Override
	public String[] GetUsedVariables() {
		if (null == this.ReturnValue)
			return null;
		
		return new String[] { this.ReturnValue.GetName() };
	}
}
