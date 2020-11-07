package ControlFlowGraph;

import IRCode.Label;

public class CFGLabel extends CFGBase {
	public Label Label = null;
	
	public CFGLabel(
		Label aLabel) {
		this.Label = aLabel;
	}

	@Override
	public boolean ComputeLiveness() {
		// Nothing to do because labels don't have variables.
		return false;
	}

	@Override
	public String[] GetUsedVariables() {
		return null;
	}
}
