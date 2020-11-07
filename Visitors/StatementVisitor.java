package Visitors;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import ANTLR.*;
import ANTLR.TigerParser.ExprContext;
import ANTLR.TigerParser.Stat_seqContext;
import SyntaxTree.*;
import Utilities.SymbolTable;

public class StatementVisitor extends TigerParserBaseVisitor<Statement> {
	private SymbolTable mSymbolTable = null;
	
	public StatementVisitor(
			SymbolTable aSymbolTable) {
		mSymbolTable = aSymbolTable;
	}
	
	@Override public Statement visitStat(TigerParser.StatContext ctx)
	{
		if (null != ctx.BREAK()) {
			// Break statement.
			return new BreakStatement();
		}
		
		ExpressionVisitor fExpressionVisitor = new ExpressionVisitor(mSymbolTable);

		if (null != ctx.RETURN()) {
			// Return statement.
			Expression fExpression = ctx.expr(0).accept(fExpressionVisitor);
			return new ReturnStatement(fExpression);
		}

		StatementListVisitor fStatementListVisitor = new StatementListVisitor(mSymbolTable);

		if (null != ctx.WHILE()) { 
			// While statement.
			Expression fConditional = ctx.expr(0).accept(fExpressionVisitor);
			StatementList fBody = ctx.stat_seq(0).accept(fStatementListVisitor);
			return new WhileStatement(fConditional, fBody);
		}
		
		if (null != ctx.FOR()) {
			// For statement.
			String fxID = ctx.ID(0).getText();
			if (null == mSymbolTable.GetSymbol(fxID)) {
				SymbolTable fForSymbolTable = mSymbolTable.PushScope();
				VariableDeclaration fIteratorDeclaration = new VariableDeclaration(fxID);
				fIteratorDeclaration.SetType(new IntrinsicType(IntrinsicTypeE.Int));
				fForSymbolTable.PutSymbol(fxID, fIteratorDeclaration);
				ExpressionVisitor fForExpressionVisitor = new ExpressionVisitor(fForSymbolTable);
				Expression fFrom = ctx.expr(0).accept(fForExpressionVisitor),
						fTo = ctx.expr(1).accept(fForExpressionVisitor);
				StatementListVisitor fForStatementListVisitor = new StatementListVisitor(fForSymbolTable);
				StatementList fStatements = ctx.stat_seq(0).accept(fForStatementListVisitor);
				return new ForStatement(fIteratorDeclaration, fFrom, fTo, fStatements);
			}
			else
				mSymbolTable.AddDuplicateSymbol(fxID);
			return new NullStatement();
		}

		if (null != ctx.IF()) {
			// If statement.
			Expression fConditional = ctx.expr(0).accept(fExpressionVisitor);
			StatementList fThen = ctx.stat_seq(0).accept(fStatementListVisitor),
					fElse = null;
			Stat_seqContext fElseContext = ctx.stat_seq(1);
			if (null != fElseContext)
				fElse = fElseContext.accept(fStatementListVisitor);
			
			return new IfStatement(fConditional, fThen, fElse);
		}

		if (null != ctx.LET()) {
			// Let statement.
			ProgramVisitor fProgramVisitor = new ProgramVisitor(mSymbolTable.PushScope());
			LetStatement fDeclarationSegment = ctx.declaration_segment().accept(fProgramVisitor);
			StatementList fStatementList = ctx.stat_seq(0).accept(fStatementListVisitor);
			return new DeclarationStatement(fDeclarationSegment, fStatementList);
		}

		if (null != ctx.ASSIGN()) {
			// Assignment statement.
			LValue fLValue = (LValue)ctx.lvalue().accept(fExpressionVisitor);
			Expression fExpression = ctx.expr(0).accept(fExpressionVisitor);
			return new AssignmentStatement(fLValue, fExpression);
		}
		
		if (null != ctx.EQ()) {
			// Function invocation.
			String fxVariableName = ctx.ID(0).getText();
			VariableSymbol fVariable = mSymbolTable.GetVariable(fxVariableName);
			if (null == fVariable)
				mSymbolTable.AddUnresolvedVariable(fxVariableName);

			String fxFunctionName = ctx.ID(1).getText();
			FunctionDeclaration fFunction = mSymbolTable.GetFunction(fxFunctionName);
			if (null == fFunction)
				mSymbolTable.AddUnresolvedFunction(fxFunctionName);
			
			List<Expression> fvArgumentExpressions = ctx.expr()
					.stream()
					.map(aaExpressionArgument -> aaExpressionArgument.accept(fExpressionVisitor)).collect(Collectors.toList());

			return new InvocationStatement(fVariable, fFunction, fvArgumentExpressions);
		}
		
		throw new UnsupportedOperationException();
	}
}
