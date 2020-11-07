package SyntaxTree;

public class IntrinsicType extends Type {
	private IntrinsicTypeE mIntrinsic = IntrinsicTypeE.Float;
	
	public IntrinsicType(
			IntrinsicTypeE aIntrinsic) {
		mIntrinsic = aIntrinsic;
	}

	@Override public boolean IsCustomType() {
		return false;
	}

	@Override
	public IntrinsicTypeE GetIntrinsicType() {
		return mIntrinsic;
	}
}
