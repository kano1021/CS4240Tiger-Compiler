package ControlFlowGraph;

import SyntaxTree.VariableSymbol;

public class CFGCall extends CFGBase {
	public String FunctionName = null;
	public VariableSymbol StoreReturn = null;
	public VariableSymbol[] Arguments = null;
	
	public CFGCall(
		String axFunctionName,
		VariableSymbol aStoreReturn,
		VariableSymbol[] avArguments) {
		
		this.FunctionName = axFunctionName;
		this.StoreReturn = aStoreReturn;
		this.Arguments = avArguments;
	}

	@Override
	public boolean ComputeLiveness() {
		boolean fqReturn = false;
		fqReturn |= this.Def.add(this.StoreReturn);
		for (VariableSymbol fiArgument : this.Arguments)
			fqReturn |= this.Use.add(fiArgument);
		
		return fqReturn;
	}

	@Override
	public String[] GetUsedVariables() {
		String[] fvReturn = new String[this.Arguments.length];
		
		for (int fiIterator = 0; fiIterator < this.Arguments.length; ++fiIterator)
			fvReturn[fiIterator] = this.Arguments[fiIterator].GetName();
		
		return fvReturn;
	}
}
