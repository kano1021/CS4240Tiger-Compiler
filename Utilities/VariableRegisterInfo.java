package Utilities;

import SyntaxTree.VariableSymbol;

public class VariableRegisterInfo {
	public static final int INVALID_REGISTER = Integer.MAX_VALUE;
	
	public int CurrentRegister = INVALID_REGISTER;
	
	public int LastUsed = 0;
	
	public VariableSymbol Variable = null;
	
	public VariableRegisterInfo(
			VariableSymbol aVariable) {
		this.Variable = aVariable;
	}
}
