package Visitors;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import ANTLR.*;
import ANTLR.TigerParser.Multiplicative_expression_tailContext;
import ANTLR.TigerParser.Type_idContext;
import SyntaxTree.*;
import Utilities.*;

public class MultiplicativeExpressionTailVisitor extends TigerParserBaseVisitor<MultiplicativeExpressionTail> {
	private SymbolTable mSymbolTable = null;
	
	public MultiplicativeExpressionTailVisitor(
			SymbolTable aSymbolTable) {
		mSymbolTable = aSymbolTable;
	}
	
	@Override public MultiplicativeExpressionTail visitMultiplicative_expression_tail(TigerParser.Multiplicative_expression_tailContext ctx)
	{
		OperatorE fOperand = OperatorE.Add;
		if (null != ctx.MULT())
			fOperand = OperatorE.Multiply;
		else if (null != ctx.DIV())
			fOperand = OperatorE.Divide;
		else if (null != ctx.POWER())
			fOperand = OperatorE.Power;
		
		if (OperatorE.Add == fOperand)
			throw new UnsupportedOperationException("Operand not found.");

		ExpressionVisitor fExpressionVisitor = new ExpressionVisitor(mSymbolTable);
		UnaryExpression fUnaryExpression = (UnaryExpression)ctx.unary_expression()
				.accept(fExpressionVisitor);
		
		Multiplicative_expression_tailContext fContext = ctx.multiplicative_expression_tail();
		MultiplicativeExpressionTail fTail = null;
		if (null != fContext)
			fTail = fContext.accept(this);
		
		return new MultiplicativeExpressionTail(fOperand, fUnaryExpression, fTail);
	}
}
