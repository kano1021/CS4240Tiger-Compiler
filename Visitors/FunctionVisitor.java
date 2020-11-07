package Visitors;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import ANTLR.*;
import ANTLR.TigerParser.Type_idContext;
import SyntaxTree.*;
import Utilities.SymbolTable;

public class FunctionVisitor extends TigerParserBaseVisitor<FunctionDeclaration> {
	private SymbolTable mParentSymbolTable = null;
	private SymbolTable mScopedSymbolTable = null;
	
	public FunctionVisitor(
		SymbolTable aSymbolTable)
	{
		mParentSymbolTable = aSymbolTable;
		mScopedSymbolTable = mParentSymbolTable.PushScope();
	}
	
	@Override public FunctionDeclaration visitFunction_declaration(TigerParser.Function_declarationContext ctx)
	{
		FunctionDeclaration fReturn = ctx.param_list().accept(this);
		
		String fID = ctx.ID().getText();
		fReturn.SetName(fID);
		mParentSymbolTable.PutSymbol(fID, fReturn);
		
		TypeVisitor fTypeVisitor = new TypeVisitor(mScopedSymbolTable);
		Type fReturnType = ctx.ret_type().accept(fTypeVisitor);		
		fReturn.SetReturnType(fReturnType);
		
		StatementListVisitor fStatementsVisitor = new StatementListVisitor(mScopedSymbolTable);
		StatementList fStatements = ctx.stat_seq().accept(fStatementsVisitor);
		fReturn.SetStatements(fStatements);
		
		return fReturn;
	}

	@Override public FunctionDeclaration visitParam_list(TigerParser.Param_listContext ctx)
	{
		ParameterVisitor fParameterVisitor = new ParameterVisitor(mScopedSymbolTable);
		List<ParameterDeclaration> fvParameterDeclarations = ctx.param()
				.stream()
				.map(aaType -> {
					ParameterDeclaration ffParam = aaType.accept(fParameterVisitor);
					Type ffType = ffParam.GetType();
					ffParam.SetType(ffType);
					
					mScopedSymbolTable.PutSymbol(ffParam.GetName(), ffParam);
					return ffParam;
				}).collect(Collectors.toList());
		
		return new FunctionDeclaration(fvParameterDeclarations, mScopedSymbolTable);
	}
}
