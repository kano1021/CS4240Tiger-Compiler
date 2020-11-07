package Visitors;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import ANTLR.*;
import ANTLR.TigerParser.Additive_expression_tailContext;
import ANTLR.TigerParser.Type_idContext;
import SyntaxTree.*;
import Utilities.*;

public class AdditiveExpressionTailVisitor extends TigerParserBaseVisitor<AdditiveExpressionTail> {
	private SymbolTable mSymbolTable = null;
	
	public AdditiveExpressionTailVisitor(
			SymbolTable aSymbolTable) {
		mSymbolTable = aSymbolTable;
	}
	
	@Override public AdditiveExpressionTail visitAdditive_expression_tail(TigerParser.Additive_expression_tailContext ctx)
	{
		OperatorE fOperand = (null == ctx.PLUS()) ? OperatorE.Subtract : OperatorE.Add;
		ExpressionVisitor fExpressionVisitor = new ExpressionVisitor(mSymbolTable);
		MultiplicativeExpression fMultiplicativeExpression = (MultiplicativeExpression)ctx.multiplicative_expression()
				.accept(fExpressionVisitor);
		Additive_expression_tailContext fContext = ctx.additive_expression_tail();
		AdditiveExpressionTail fTail = null;
		if (null != fContext)
			fTail = fContext.accept(this);
		
		return new AdditiveExpressionTail(fOperand, fMultiplicativeExpression, fTail);
	}
}
