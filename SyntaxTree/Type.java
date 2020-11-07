package SyntaxTree;

public abstract class Type {
	public Type GetUnderlyingType() {
		return this;
	}
	
	public abstract IntrinsicTypeE GetIntrinsicType();
	
	public abstract boolean IsCustomType();
	
	public boolean IsArray() {
		return false;
	}
}
