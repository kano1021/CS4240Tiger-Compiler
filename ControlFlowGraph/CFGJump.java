package ControlFlowGraph;

import IRCode.Label;

public class CFGJump extends CFGBase {
	public Label To = null;
	
	public CFGJump(
		Label aTo) {
		this.To = aTo;
	}

	@Override
	public boolean ComputeLiveness() {
		// Nothing to do because jmp's don't use variables.
		return false;
	}

	@Override
	public String[] GetUsedVariables() {
		return null;
	}
}
