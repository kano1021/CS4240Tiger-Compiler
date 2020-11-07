package ControlFlowGraph;

import java.util.List;

import SyntaxTree.ConstantLiteral;
import SyntaxTree.VariableSymbol;

public class CFGSet extends CFGBase {
	public VariableSymbol Destination = null;
	public String Offset = null;
	public VariableSymbol SourceSymbol = null;
	public ConstantLiteral SourceConstant = null;
	
	public CFGSet(
		VariableSymbol aDestination,
		String axOffset,
		VariableSymbol aSource) {
		this.Destination = aDestination;
		this.Offset = axOffset;
		this.SourceSymbol = aSource;
	}
	
	public CFGSet(
		VariableSymbol aDestination,
		String axOffset,
		ConstantLiteral aSource) {
		this.Destination = aDestination;
		this.Offset = axOffset;
		this.SourceConstant = aSource;
	}

	@Override
	public boolean ComputeLiveness() {
		boolean fqReturn = false;
		fqReturn |= this.Def.add(this.Destination);
		if (null != this.SourceSymbol)
			fqReturn |= this.Use.add(this.SourceSymbol);
		return fqReturn;
	}

	@Override
	public String[] GetUsedVariables() {
		if (null == this.SourceSymbol) 
			return null;
		
		return new String[] { this.SourceSymbol.GetName() };
	}
}
