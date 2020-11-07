package Visitors;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import ANTLR.*;
import ANTLR.TigerParser.Type_idContext;
import SyntaxTree.*;
import Utilities.*;

public class TypeVisitor extends TigerParserBaseVisitor<Type> {
	private SymbolTable mSymbolTable = null;

	public TypeVisitor(
			SymbolTable aSymbolTable) {
		mSymbolTable = aSymbolTable;
	}

	@Override public Type visitType(TigerParser.TypeContext ctx) {
		Type_idContext fTypeIDContext = ctx.type_id();
		if (null == fTypeIDContext) {
			String fxID = ctx.ID().getText();
			TypeDeclaration fTypeDeclaration = mSymbolTable.GetType(fxID);
			return new AliasType(fTypeDeclaration);
		}

		TypeIDVisitor fTypeIDVisitor = new TypeIDVisitor();
		IntrinsicTypeE fTypeID = fTypeIDContext.accept(fTypeIDVisitor);

		TerminalNode fSizeNode = ctx.INTLIT();
		if (null == fSizeNode)
			return new IntrinsicType(fTypeID);

		int fSize = Integer.parseInt(fSizeNode.getText());
		// TODO: We should check the result of the integer parsed here.
		return new ArrayType(fTypeID, fSize);
	}

	@Override public Type visitRet_type(TigerParser.Ret_typeContext ctx) {
		return ctx.type().accept(this);
	}
}
