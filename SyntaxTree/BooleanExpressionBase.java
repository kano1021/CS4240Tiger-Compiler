package SyntaxTree;

import java.util.*;

import Utilities.*;

public abstract class BooleanExpressionBase<tChild extends Expression> 
	extends ExpressionBase<tChild> {
	protected BooleanExpressionBase(
		List<tChild> avBranches) {
		super(avBranches);
	}
		
	@Override
	public void AnalyzeSemantics(
		SymbolTable aSymbolTable,
		SemanticAnalyzer aSemanticAnalyzer) {
		if (2 == mvBranches.size()) {
			if ((IntrinsicTypeE.Boolean != mvBranches.get(0).EvaluateExpressionType())
				|| (IntrinsicTypeE.Boolean != mvBranches.get(1).EvaluateExpressionType()))
				aSemanticAnalyzer.AddExpressionNotBoolean();
		}
	}
}
