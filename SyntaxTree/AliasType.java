package SyntaxTree;

public class AliasType extends Type {
	private TypeDeclaration mAliasedType = null;
	
	public AliasType(
		TypeDeclaration aAliasedType)
	{
		mAliasedType = aAliasedType;
	}
	
	@Override public Type GetUnderlyingType() {
		return mAliasedType.GetUnderlyingType();
	}
	
	@Override public boolean IsCustomType() {
		return true;
	}

	@Override public IntrinsicTypeE GetIntrinsicType() {
		return mAliasedType.GetUnderlyingType().GetIntrinsicType();
	}
}
