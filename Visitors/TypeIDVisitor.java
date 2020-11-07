package Visitors;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import ANTLR.*;
import ANTLR.TigerParser.Type_idContext;
import SyntaxTree.*;

public class TypeIDVisitor extends TigerParserBaseVisitor<IntrinsicTypeE> {
	@Override public IntrinsicTypeE visitType_id(TigerParser.Type_idContext ctx) {
		return (null == ctx.INT()) ? IntrinsicTypeE.Float : IntrinsicTypeE.Int;
	}
}
