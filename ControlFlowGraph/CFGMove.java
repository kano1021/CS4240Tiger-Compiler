package ControlFlowGraph;

import SyntaxTree.VariableSymbol;

public class CFGMove extends CFGBase {
	public VariableSymbol Source = null;
	public VariableSymbol Offset = null;
	public VariableSymbol Destination = null;
	
	public CFGMove(
		VariableSymbol aSource,
		VariableSymbol aOffset,
		VariableSymbol aDestination) {
		this.Source = aSource;
		this.Offset = aOffset;
		this.Destination = aDestination;
	}

	@Override
	public boolean ComputeLiveness() {
		boolean fqReturn = false;
		fqReturn |= this.Def.add(this.Destination);
		fqReturn |= this.Use.add(this.Offset);
		fqReturn |= this.Use.add(this.Source);
		return fqReturn;
	}

	@Override
	public String[] GetUsedVariables() {
		return new String[] { this.Source.GetName(), this.Offset.GetName() };
	}
}
