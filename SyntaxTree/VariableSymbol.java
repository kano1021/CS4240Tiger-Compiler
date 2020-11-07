package SyntaxTree;

public abstract class VariableSymbol extends Symbol {
	protected VariableSymbol(
		String axID) {
		mxID = axID;
	}
	
	private int mAddress = 0x00;
	
	public int GetAddress() {
		return mAddress;
	}
	
	private Type mType = null;

	public Type GetType() {
		return mType;
	}
	
	public Type SetType(
			Type aType) {
		mType = aType;
		return aType;
	}
	
	private String mxID = null;

	public String GetName() {
		return mxID;
	}
}
