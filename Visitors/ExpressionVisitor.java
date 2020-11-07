package Visitors;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import ANTLR.*;
import ANTLR.TigerParser.Additive_expression_tailContext;
import ANTLR.TigerParser.Const_literalContext;
import ANTLR.TigerParser.ExprContext;
import ANTLR.TigerParser.LvalueContext;
import ANTLR.TigerParser.Multiplicative_expression_tailContext;
import ANTLR.TigerParser.Type_idContext;
import SyntaxTree.*;
import Utilities.SymbolTable;

public class ExpressionVisitor extends TigerParserBaseVisitor<Expression> {
	private SymbolTable mSymbolTable = null;
	
	public ExpressionVisitor(
			SymbolTable aSymbolTable) {
		mSymbolTable = aSymbolTable;
	}
	
	@Override public Expression visitExpr(TigerParser.ExprContext ctx)
	{		
		List<AndExpression> fvExpressions = ctx.and_expression()
				.stream()
				.map(aaExpression -> (AndExpression)aaExpression.accept(this)).collect(Collectors.toList());

		return new InclusiveOrExpression(fvExpressions);
	}
	
	@Override public Expression visitAnd_expression(TigerParser.And_expressionContext ctx)
	{
		List<EqualityExpression> fvExpressions = ctx.equality_expression()
				.stream()
				.map(aaExpression -> (EqualityExpression)aaExpression.accept(this)).collect(Collectors.toList());
		
		return new AndExpression(fvExpressions);
	}
	
	@Override public Expression visitEquality_expression(TigerParser.Equality_expressionContext ctx)
	{
		List<RelationalExpression> fvExpressions = ctx.relational_expression()
				.stream()
				.map(aaExpression -> (RelationalExpression)aaExpression.accept(this)).collect(Collectors.toList());
		
		OperatorE fOperand = (null == ctx.EQEQ()) ? OperatorE.NotEqual : OperatorE.Equal;
				
		return new EqualityExpression(fvExpressions, fOperand);
	}
	
	@Override public Expression visitRelational_expression(TigerParser.Relational_expressionContext ctx)
	{
		List<AdditiveExpression> fvExpressions = ctx.additive_expression()
				.stream()
				.map(aaExpression -> (AdditiveExpression)aaExpression.accept(this)).collect(Collectors.toList());

		OperatorE fOperator = null;
		if (null != ctx.LESSER())
			fOperator = OperatorE.LessThan;
		else if (null != ctx.GREATER())
			fOperator = OperatorE.GreaterThan;
		else if (null != ctx.GREATEREQ())
			fOperator = OperatorE.GreaterThanOrEqualTo;
		else if (null != ctx.LESSEREQ())
			fOperator = OperatorE.LessThanOrEqualTo;
				
		return new RelationalExpression(fvExpressions, fOperator);
	}
	
	@Override public Expression visitAdditive_expression(TigerParser.Additive_expressionContext ctx)
	{
		MultiplicativeExpression fMultiplicativeExpression = (MultiplicativeExpression)ctx.multiplicative_expression()
				.accept(this);
		
		AdditiveExpressionTail fTail = null;
		Additive_expression_tailContext fContext = ctx.additive_expression_tail();
		if (null != fContext) {
			AdditiveExpressionTailVisitor fVisitor = new AdditiveExpressionTailVisitor(mSymbolTable);
			fTail = fContext.accept(fVisitor);
		}
		
		return new AdditiveExpression(fMultiplicativeExpression, fTail);
	}

	@Override public Expression visitMultiplicative_expression(TigerParser.Multiplicative_expressionContext ctx)
	{
		UnaryExpression fUnaryExpression = (UnaryExpression)ctx.unary_expression()
				.accept(this);
		
		MultiplicativeExpressionTail fTail = null;
		Multiplicative_expression_tailContext fContext = ctx.multiplicative_expression_tail();
		if (null != fContext) {
			MultiplicativeExpressionTailVisitor fVisitor = new MultiplicativeExpressionTailVisitor(mSymbolTable);
			fTail = fContext.accept(fVisitor);
		}
		
		return new MultiplicativeExpression(fUnaryExpression, fTail);
	}
	
	@Override public Expression visitUnary_expression(TigerParser.Unary_expressionContext ctx)
	{
		Const_literalContext fConstantLiteralContext = ctx.const_literal();
		if (null != fConstantLiteralContext)
			return fConstantLiteralContext.accept(this);
		
		LvalueContext fLValueContext = ctx.lvalue();
		if (null != fLValueContext)
			return fLValueContext.accept(this);
		
		return new ParentheticalExpression(ctx.expr().accept(this));
	}
	
	@Override public Expression visitOptional_init(TigerParser.Optional_initContext ctx) {
		return ctx.const_literal().accept(this);
	}

	@Override public Expression visitConst_literal(TigerParser.Const_literalContext ctx)
	{
		TerminalNode fIntLiteral = ctx.INTLIT();
		if (null == fIntLiteral)
			return new ConstantLiteral(Float.parseFloat(ctx.FLOATLIT().getText()));
		
		return new ConstantLiteral(Integer.parseInt(fIntLiteral.getText()));
	}
	
	@Override public Expression visitLvalue(TigerParser.LvalueContext ctx) {
		String fxID = ctx.ID().getText();
		VariableSymbol fVariable = mSymbolTable.GetVariable(fxID);
		
		ExprContext fContext = ctx.expr();
		Expression fExpression = null;
		if (null != fContext)
			fExpression = ctx.expr().accept(this);
		
		if (null != fExpression)
		{
			if (!fVariable.GetType().IsArray())
				mSymbolTable.AddVariableIsNotAnArray(fxID);
		}
		
		return new LValue(fVariable, fExpression);
	}
}
