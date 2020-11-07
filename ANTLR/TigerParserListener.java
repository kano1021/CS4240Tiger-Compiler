// Generated from TigerParser.g4 by ANTLR 4.7.2

    package ANTLR;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TigerParser}.
 */
public interface TigerParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TigerParser#tiger_program}.
	 * @param ctx the parse tree
	 */
	void enterTiger_program(TigerParser.Tiger_programContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#tiger_program}.
	 * @param ctx the parse tree
	 */
	void exitTiger_program(TigerParser.Tiger_programContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#declaration_segment}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration_segment(TigerParser.Declaration_segmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#declaration_segment}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration_segment(TigerParser.Declaration_segmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#type_declaration}.
	 * @param ctx the parse tree
	 */
	void enterType_declaration(TigerParser.Type_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#type_declaration}.
	 * @param ctx the parse tree
	 */
	void exitType_declaration(TigerParser.Type_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(TigerParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(TigerParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#type_id}.
	 * @param ctx the parse tree
	 */
	void enterType_id(TigerParser.Type_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#type_id}.
	 * @param ctx the parse tree
	 */
	void exitType_id(TigerParser.Type_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVar_declaration(TigerParser.Var_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVar_declaration(TigerParser.Var_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#id_list}.
	 * @param ctx the parse tree
	 */
	void enterId_list(TigerParser.Id_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#id_list}.
	 * @param ctx the parse tree
	 */
	void exitId_list(TigerParser.Id_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#optional_init}.
	 * @param ctx the parse tree
	 */
	void enterOptional_init(TigerParser.Optional_initContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#optional_init}.
	 * @param ctx the parse tree
	 */
	void exitOptional_init(TigerParser.Optional_initContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration(TigerParser.Function_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration(TigerParser.Function_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#param_list}.
	 * @param ctx the parse tree
	 */
	void enterParam_list(TigerParser.Param_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#param_list}.
	 * @param ctx the parse tree
	 */
	void exitParam_list(TigerParser.Param_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#ret_type}.
	 * @param ctx the parse tree
	 */
	void enterRet_type(TigerParser.Ret_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#ret_type}.
	 * @param ctx the parse tree
	 */
	void exitRet_type(TigerParser.Ret_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(TigerParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(TigerParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat_seq}.
	 * @param ctx the parse tree
	 */
	void enterStat_seq(TigerParser.Stat_seqContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat_seq}.
	 * @param ctx the parse tree
	 */
	void exitStat_seq(TigerParser.Stat_seqContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(TigerParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(TigerParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(TigerParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(TigerParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#const_literal}.
	 * @param ctx the parse tree
	 */
	void enterConst_literal(TigerParser.Const_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#const_literal}.
	 * @param ctx the parse tree
	 */
	void exitConst_literal(TigerParser.Const_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#and_expression}.
	 * @param ctx the parse tree
	 */
	void enterAnd_expression(TigerParser.And_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#and_expression}.
	 * @param ctx the parse tree
	 */
	void exitAnd_expression(TigerParser.And_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#equality_expression}.
	 * @param ctx the parse tree
	 */
	void enterEquality_expression(TigerParser.Equality_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#equality_expression}.
	 * @param ctx the parse tree
	 */
	void exitEquality_expression(TigerParser.Equality_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#relational_expression}.
	 * @param ctx the parse tree
	 */
	void enterRelational_expression(TigerParser.Relational_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#relational_expression}.
	 * @param ctx the parse tree
	 */
	void exitRelational_expression(TigerParser.Relational_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#additive_expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditive_expression(TigerParser.Additive_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#additive_expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditive_expression(TigerParser.Additive_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#additive_expression_tail}.
	 * @param ctx the parse tree
	 */
	void enterAdditive_expression_tail(TigerParser.Additive_expression_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#additive_expression_tail}.
	 * @param ctx the parse tree
	 */
	void exitAdditive_expression_tail(TigerParser.Additive_expression_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#multiplicative_expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative_expression(TigerParser.Multiplicative_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#multiplicative_expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative_expression(TigerParser.Multiplicative_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#multiplicative_expression_tail}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative_expression_tail(TigerParser.Multiplicative_expression_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#multiplicative_expression_tail}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative_expression_tail(TigerParser.Multiplicative_expression_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#unary_expression}.
	 * @param ctx the parse tree
	 */
	void enterUnary_expression(TigerParser.Unary_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#unary_expression}.
	 * @param ctx the parse tree
	 */
	void exitUnary_expression(TigerParser.Unary_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterLvalue(TigerParser.LvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitLvalue(TigerParser.LvalueContext ctx);
}