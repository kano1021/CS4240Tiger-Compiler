// Generated from TigerParser.g4 by ANTLR 4.7.2

    package ANTLR;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TigerParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TigerParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TigerParser#tiger_program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTiger_program(TigerParser.Tiger_programContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#declaration_segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration_segment(TigerParser.Declaration_segmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#type_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_declaration(TigerParser.Type_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(TigerParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#type_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_id(TigerParser.Type_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#var_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_declaration(TigerParser.Var_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#id_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_list(TigerParser.Id_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#optional_init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptional_init(TigerParser.Optional_initContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#function_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declaration(TigerParser.Function_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#param_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_list(TigerParser.Param_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#ret_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRet_type(TigerParser.Ret_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(TigerParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#stat_seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_seq(TigerParser.Stat_seqContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(TigerParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(TigerParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#const_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst_literal(TigerParser.Const_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#and_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_expression(TigerParser.And_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#equality_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality_expression(TigerParser.Equality_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#relational_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational_expression(TigerParser.Relational_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#additive_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive_expression(TigerParser.Additive_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#additive_expression_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive_expression_tail(TigerParser.Additive_expression_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#multiplicative_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicative_expression(TigerParser.Multiplicative_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#multiplicative_expression_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicative_expression_tail(TigerParser.Multiplicative_expression_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#unary_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_expression(TigerParser.Unary_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLvalue(TigerParser.LvalueContext ctx);
}