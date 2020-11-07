package SyntaxTree;

import Utilities.*;

public abstract class Expression implements CompilableExpressionI {
	public static IntrinsicTypeE EvaluateExpressionTypeFrom(
		IntrinsicTypeE aLHS,
		IntrinsicTypeE aRHS) {
		if ((IntrinsicTypeE.Float == aLHS)
				|| (IntrinsicTypeE.Float == aRHS))
			return IntrinsicTypeE.Float;
		
		if ((IntrinsicTypeE.Int == aLHS)
				|| (IntrinsicTypeE.Int == aRHS))
			return IntrinsicTypeE.Int;
		
		if ((IntrinsicTypeE.Boolean == aLHS)
				|| (IntrinsicTypeE.Boolean == aRHS))
			return IntrinsicTypeE.Boolean;
		
		return IntrinsicTypeE.Void;
	}
}
