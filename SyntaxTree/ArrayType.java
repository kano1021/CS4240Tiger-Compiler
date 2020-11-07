package SyntaxTree;

public class ArrayType extends Type {
	private IntrinsicTypeE mBaseType = IntrinsicTypeE.Float;
	private int mSize = 0;
	
	public ArrayType(
			IntrinsicTypeE aIntrinsicType,
			int aSize) {
		mBaseType = aIntrinsicType;
		mSize = aSize;
	}
	
	@Override public boolean IsCustomType() {
		return false;
	}
	
	@Override public boolean IsArray() {
		return true;
	}

	@Override
	public IntrinsicTypeE GetIntrinsicType() {
		return mBaseType;
	}
	
	public int GetSize() {
		return mSize;
	}
}
