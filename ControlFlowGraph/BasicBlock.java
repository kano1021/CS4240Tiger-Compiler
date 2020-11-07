package ControlFlowGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import IRCode.Label;
import SyntaxTree.VariableSymbol;

public class BasicBlock extends LivenessBase {
	public Set<Label> EntryLabels = new HashSet<Label>();
	public Set<Label> ExitLabels = new HashSet<Label>();
	
	public List<CFGBase> Instructions = new ArrayList<CFGBase>();

	public Set<BasicBlock> NextBlocks = new HashSet<BasicBlock>();
	
	public BasicBlockingStateE State = BasicBlockingStateE.PreHead;

	public BasicBlock() {
	}

	@Override
	public boolean ComputeLiveness() {
		CFGBase fiFollower = this.Instructions.get(this.Instructions.size() - 1);
		fiFollower.Out.clear();
		fiFollower.Out.addAll(this.Out);
		fiFollower.ComputeIn();
		
		for (int fiIterator = this.Instructions.size() - 2; fiIterator >= 0; --fiIterator) {
			CFGBase fiPreceder = this.Instructions.get(fiIterator);
			fiPreceder.Out.clear();
			fiPreceder.Out.addAll(fiFollower.In);
			fiPreceder.ComputeIn();
			fiFollower = fiPreceder;
		}
		
		return this.ComputeIn();
	}
}
