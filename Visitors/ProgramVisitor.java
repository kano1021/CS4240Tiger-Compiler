package Visitors;

import java.util.*;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import ANTLR.*;
import SyntaxTree.*;
import Utilities.*;

// See: https://jakubdziworski.github.io/java/2016/04/01/antlr_visitor_vs_listener.html
public class ProgramVisitor extends TigerParserBaseVisitor<LetStatement> {
	private SymbolTable mSymbolTable = null;
	
	public ProgramVisitor(
			SymbolTable aSymbolTable) {
		mSymbolTable = aSymbolTable;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public LetStatement visitTiger_program(TigerParser.Tiger_programContext ctx)
	{
		LetStatement fProgram = ctx.declaration_segment().accept(this);
		StatementListVisitor fStatementListVisitor = new StatementListVisitor(fProgram.GetSymbolTable());
		StatementList fStatementList = ctx.stat_seq().accept(fStatementListVisitor);
		fProgram.AddBody(fStatementList);
		return fProgram;
	}
	
	@Override public LetStatement visitDeclaration_segment(TigerParser.Declaration_segmentContext ctx)
	{
		TypeDeclarationVisitor fTypeVisitor = new TypeDeclarationVisitor(mSymbolTable);
		List<TypeDeclaration> fvTypeDeclarations = ctx.type_declaration()
				.stream()
				.map(aaType -> aaType.accept(fTypeVisitor)).collect(Collectors.toList());
				
		VariableVisitor fVariableListener = new VariableVisitor(mSymbolTable);
		List<VariableDeclaration> fvVariableDeclarations = new ArrayList<VariableDeclaration>();
		ctx.var_declaration()
			.stream()
			.forEach(aaVariable ->
			{
				List<VariableDeclaration> ffvVariables = aaVariable.accept(fVariableListener);
				for (int ffiVariableIterator = 0; ffiVariableIterator < ffvVariables.size(); ++ffiVariableIterator) {
					VariableDeclaration ffiVariable = ffvVariables.get(ffiVariableIterator);
					mSymbolTable.PutSymbol(ffiVariable.GetName(), ffiVariable);
					fvVariableDeclarations.add(ffiVariable);
				}
			});
				
		List<FunctionDeclaration> fvFunctionDeclarations = ctx.function_declaration()
				.stream()
				.map(aaFunction ->
				{
					FunctionVisitor fFunctionListener = new FunctionVisitor(mSymbolTable);					
					return aaFunction.accept(fFunctionListener);
				}).collect(Collectors.toList());
								
		return new LetStatement(fvTypeDeclarations, fvVariableDeclarations, fvFunctionDeclarations, mSymbolTable);
	}
}
