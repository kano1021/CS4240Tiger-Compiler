package ControlFlowGraph;

public class LiveRange {
	public BasicBlock BasicBlock = null;
	
	public int StartInstruction = Integer.MIN_VALUE;
	public int EndInstruction = Integer.MIN_VALUE;
}
