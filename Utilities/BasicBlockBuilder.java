package Utilities;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import ControlFlowGraph.BasicBlock;
import ControlFlowGraph.BasicBlockingStateE;
import ControlFlowGraph.CFGBase;
import ControlFlowGraph.CFGBranchEqual;
import ControlFlowGraph.CFGCall;
import ControlFlowGraph.CFGJump;
import ControlFlowGraph.CFGLabel;
import ControlFlowGraph.CFGMove;
import ControlFlowGraph.CFGOperation;
import ControlFlowGraph.CFGReturn;
import ControlFlowGraph.CFGSet;
import ControlFlowGraph.VariableLifetime;
import IRCode.Label;

public class BasicBlockBuilder {
	public BasicBlockBuilder(
		List<CFGBase> avInstructions) {
		mvInstructions = avInstructions;
	}
	
	private List<CFGBase> mvInstructions = null;

	private List<BasicBlock> mvBasicBlocks = new ArrayList<BasicBlock>();

	private BasicBlock mPrevious = null;
	private BasicBlock mCurrent = null;
	
	private Dictionary<Label, BasicBlock> mvLabelToBasicBlockMapping = new Hashtable<Label, BasicBlock>();
	
	private BasicBlock GetCurrent() {
		if (null == mCurrent) {
			mCurrent = new BasicBlock();
			mvBasicBlocks.add(mCurrent);
			if (null != mPrevious) 
			{
				mPrevious.NextBlocks.add(mCurrent);
				mPrevious = null;
			}
		}
		
		return mCurrent;
	}

	private void Push()
	{
		mPrevious = mCurrent;
		End();
	}
	
	private void End() {
		if (null != mCurrent) {
			for (Label fiLabel : mCurrent.EntryLabels)
				mvLabelToBasicBlockMapping.put(fiLabel, mCurrent);
			
			mCurrent = null;
		}
	}
	
	public List<BasicBlock> Build() {
		for (int fiIterator = 0; fiIterator < mvInstructions.size(); ++fiIterator) {				
			CFGBase fiBase = mvInstructions.get(fiIterator);
			BasicBlock fiCurrent = null;
			
			if (fiBase instanceof CFGBranchEqual) {
				// Add the branch-equal instruction, which finishes this instruction. However, we also have to add the exit label and push us to the previous.
				(fiCurrent = GetCurrent()).Instructions.add(fiBase);
				fiCurrent.ExitLabels.add(((CFGBranchEqual)fiBase).BranchTo);
				Push();
			} else if (fiBase instanceof CFGJump) {
				// Add the jump, which finishes this instruction. We also have to add the exit label. However, we don't push because there's nothing to follow us.
				(fiCurrent = GetCurrent()).Instructions.add(fiBase);
				fiCurrent.ExitLabels.add(((CFGJump)fiBase).To);
				End();
			} else if (fiBase instanceof CFGLabel) {
				// Labels depend on state.
				switch ((fiCurrent = GetCurrent()).State)
				{
				case PostHead:
				case PreHead:
					fiCurrent.EntryLabels.add(((CFGLabel)fiBase).Label);
					fiCurrent.State = BasicBlockingStateE.PostHead;
					break;
					
				case Tail:
					Push();
					(fiCurrent = GetCurrent()).EntryLabels.add(((CFGLabel)fiBase).Label);
					break;
					
				default:
					throw new UnsupportedOperationException();	
				}
			} else if ((fiBase instanceof CFGCall) 
					|| (fiBase instanceof CFGMove) 
					|| (fiBase instanceof CFGOperation) 
					|| (fiBase instanceof CFGSet)) {
				(fiCurrent = GetCurrent()).Instructions.add(fiBase);
				fiCurrent.State = BasicBlockingStateE.Tail;
			} else if (fiBase instanceof CFGReturn) {
				(fiCurrent = GetCurrent()).Instructions.add(fiBase);
				End();
			}
		}
		
		for (BasicBlock fiBasicBlock : mvBasicBlocks)
		{
			for (Label fiLabel : fiBasicBlock.ExitLabels)
				fiBasicBlock.NextBlocks.add(mvLabelToBasicBlockMapping.get(fiLabel));
		}
		
		boolean fqChanged = false;
		do {
			fqChanged = false;
			
			for (int fiBasicBlockIterator = mvBasicBlocks.size() - 1; fiBasicBlockIterator >= 0; --fiBasicBlockIterator) {
				BasicBlock fiBasicBlock = mvBasicBlocks.get(fiBasicBlockIterator);
				fiBasicBlock.Out.clear();
				for (BasicBlock fiFollower : fiBasicBlock.NextBlocks)
					fiBasicBlock.Out.addAll(fiFollower.In);
				fqChanged |= fiBasicBlock.ComputeLiveness();
			}
		} while (fqChanged);
		
		Dictionary<String, VariableLifetime> fvVariableLifetimes = new Hashtable<String, VariableLifetime>();
		
		for (BasicBlock fiBasicBlock : mvBasicBlocks) {
			for (CFGBase fiiBase : fiBasicBlock.Instructions) {
				String[] fviiVariables = fiiBase.GetUsedVariables();
				for (String fxiiiVariable : fviiVariables) {
					VariableLifetime fiiiLifetime = fvVariableLifetimes.get(fxiiiVariable);
					if (null == fiiiLifetime) 
					{
						fiiiLifetime = new VariableLifetime();
						fvVariableLifetimes.put(fxiiiVariable, fiiiLifetime);
					}
					
					// TODO: Do an insertion sort.
				}
			}
		}
		
		return mvBasicBlocks;
	}
}
