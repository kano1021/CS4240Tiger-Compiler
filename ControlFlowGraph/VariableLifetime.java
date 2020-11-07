package ControlFlowGraph;

import java.util.ArrayList;
import java.util.List;

import SyntaxTree.VariableSymbol;

public class VariableLifetime {
	public VariableSymbol Variable = null;
	
	public List<LiveRange> Range = new ArrayList<LiveRange>();
}
