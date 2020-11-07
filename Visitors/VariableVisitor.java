package Visitors;

import java.util.List;
import java.util.stream.Collectors;

import ANTLR.*;
import ANTLR.TigerParser.Optional_initContext;
import SyntaxTree.*;
import Utilities.*;

public class VariableVisitor extends TigerParserBaseVisitor<List<VariableDeclaration>> {
	private SymbolTable mSymbolTable = null;
	
	public VariableVisitor(
			SymbolTable aSymbolTable) {
		mSymbolTable = aSymbolTable;
	}
	
	@Override public List<VariableDeclaration> visitVar_declaration(TigerParser.Var_declarationContext ctx)
	{
		List<VariableDeclaration> fvReturn = ctx.id_list().accept(this);
		TypeVisitor fTypeVisitor = new TypeVisitor(mSymbolTable);
		Type fType = ctx.type().accept(fTypeVisitor);
		
		Optional_initContext fOptionalInitContext = ctx.optional_init();
		ConstantLiteral fInitialValue = null;
		if (null != fOptionalInitContext) {
			ExpressionVisitor fExpressionVisitor = new ExpressionVisitor(mSymbolTable);
			fInitialValue = (ConstantLiteral)fOptionalInitContext.accept(fExpressionVisitor);
		}

		for (int fiIterator = 0; fiIterator < fvReturn.size(); ++fiIterator) {
			VariableDeclaration fiVariable = fvReturn.get(fiIterator);
			fiVariable.SetType(fType);
			fiVariable.SetInitialValue(fInitialValue);
		}
		return fvReturn;
	}

	@Override public List<VariableDeclaration> visitId_list(TigerParser.Id_listContext ctx)
	{
		// TODO:	We should be pushing these onto the symbol table here.
		List<VariableDeclaration> fvVariables = ctx.ID()
				.stream()
				.map(aaID -> new VariableDeclaration(aaID.getText()))
					.collect(Collectors.toList());
		
		return fvVariables;
	}
}
