package Visitors;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import ANTLR.*;
import ANTLR.TigerParser.Type_idContext;
import SyntaxTree.*;
import Utilities.*;

public class StatementListVisitor extends TigerParserBaseVisitor<StatementList> {
	private SymbolTable mSymbolTable = null;
	
	public StatementListVisitor(
			SymbolTable aSymbolTable)
	{
		mSymbolTable = aSymbolTable;
	}
	
	@Override public StatementList visitStat_seq(TigerParser.Stat_seqContext ctx)
	{
		StatementVisitor fStatementVisitor = new StatementVisitor(mSymbolTable);
		List<Statement> fvStatements = ctx.stat()
				.stream()
				.map(aaStatement -> aaStatement.accept(fStatementVisitor)).collect(Collectors.toList());
		return new StatementList(fvStatements);
	}
}
