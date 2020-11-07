package Visitors;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.TerminalNode;

import ANTLR.*;
import ANTLR.TigerParser.Type_idContext;
import SyntaxTree.*;
import Utilities.*;

public class ParameterVisitor extends TigerParserBaseVisitor<ParameterDeclaration> {
	private SymbolTable mSymbolTable = null;
	
	public ParameterVisitor(
		SymbolTable aSymbolTable) {
		mSymbolTable = aSymbolTable;
	}
	
	@Override public ParameterDeclaration visitParam(TigerParser.ParamContext ctx)
	{
		TypeVisitor fTypeVisitor = new TypeVisitor(mSymbolTable);
		Type fType = ctx.type().accept(fTypeVisitor);
		
		String fxID = ctx.ID().getText();
		ParameterDeclaration fReturn = new ParameterDeclaration(fxID);
		fReturn.SetType(fType);
		return fReturn;
	}
}
