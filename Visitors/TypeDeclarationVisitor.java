package Visitors;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import ANTLR.*;
import ANTLR.TigerParser.Type_idContext;
import SyntaxTree.*;
import Utilities.*;

public class TypeDeclarationVisitor extends TigerParserBaseVisitor<TypeDeclaration> {
	private SymbolTable mSymbolTable = null;
	
	public TypeDeclarationVisitor(
			SymbolTable aSymbolTable) {
		mSymbolTable = aSymbolTable;
	}
	
	@Override public TypeDeclaration visitType_declaration(TigerParser.Type_declarationContext ctx)
	{
		TypeVisitor fVisitor = new TypeVisitor(mSymbolTable);
		Type fType = ctx.type().accept(fVisitor);
		String fxID = ctx.ID().getText();
		TypeDeclaration fReturn = new TypeDeclaration(fxID, fType);
		mSymbolTable.PutSymbol(fxID, fReturn);
		return fReturn;
	}
}
