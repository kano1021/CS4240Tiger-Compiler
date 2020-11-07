package ControlFlowGraph;

import java.util.HashSet;
import java.util.Set;

import SyntaxTree.VariableSymbol;

public abstract class LivenessBase {
	public Set<VariableSymbol> Out = new HashSet<VariableSymbol>();
	
	public Set<VariableSymbol> Def = new HashSet<VariableSymbol>();
	
	public Set<VariableSymbol> Use = new HashSet<VariableSymbol>();
	
	public Set<VariableSymbol> In = new HashSet<VariableSymbol>();
	
	public abstract boolean ComputeLiveness();
	
	public boolean ComputeIn() {
		Set<VariableSymbol> fPreviousIn = this.In;
		this.In = new HashSet<VariableSymbol>();
		
		for (VariableSymbol fiOut : this.Out)
		{
			if (!this.Def.contains(fiOut))
				this.In.add(fiOut);
		}
		
		for (VariableSymbol fiUse : this.Use)
			this.In.add(fiUse);
		
		for (VariableSymbol fiIn : this.In)
		{
			if (!fPreviousIn.contains(fiIn))
				return true;
			
			fPreviousIn.remove(fiIn);
		}
		
		return fPreviousIn.size() > 0;	// A size greater than 0 means that there were differences.
	}
}
