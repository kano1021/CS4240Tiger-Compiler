parser grammar TigerParser;

@header {
    package ANTLR;
}

options { tokenVocab=TigerLexer; }

// entry point
tiger_program 
	: MAIN LET declaration_segment IN BEGIN stat_seq END EOF
	; 

declaration_segment
	: type_declaration* var_declaration* function_declaration* 
	;
	
type_declaration
	: TYPE ID EQ type SEMICOLON
	;

type
	: type_id
	| ARRAY LBRACK INTLIT RBRACK OF type_id
	| ID
	;

type_id
	: INT
	| FLOAT
	;
	
var_declaration
	: VAR id_list COLON type optional_init? SEMICOLON
	;
	
id_list
	: ID (COMMA ID)*
	;

optional_init
	: ASSIGN const_literal
	;

function_declaration
	: FUNCTION ID LPAREN param_list RPAREN ret_type BEGIN stat_seq END SEMICOLON
	;

param_list
	: (param (COMMA param)*)?
	;
	
ret_type
	: COLON type
	;

param
	: ID COLON type
	;
	
stat_seq
	: stat*
	;

stat
	: lvalue ASSIGN expr SEMICOLON
	| ID EQ ID LPAREN (expr (COMMA expr)*)? RPAREN SEMICOLON
	| IF expr THEN stat_seq (ELSE stat_seq)? ENDIF SEMICOLON
	| WHILE expr DO stat_seq ENDDO SEMICOLON
	| FOR ID ASSIGN expr TO expr DO stat_seq ENDDO SEMICOLON
	| BREAK SEMICOLON
	| RETURN expr SEMICOLON
	| LET declaration_segment IN stat_seq END SEMICOLON
	;

expr	// = inclusive_or_expression
	: and_expression (OR and_expression)*
	;
	
const_literal
	: INTLIT
	| FLOATLIT
	;
	
and_expression
	: equality_expression (AND equality_expression)*
	;

equality_expression
	: relational_expression ((EQEQ | NEQ) relational_expression)?
	;
	
relational_expression
	: additive_expression ((LESSER | GREATER | GREATEREQ | LESSEREQ) additive_expression)?
	;
		
additive_expression
	: multiplicative_expression additive_expression_tail?
	;
	
additive_expression_tail
	: (PLUS | MINUS)  multiplicative_expression additive_expression_tail?
	;

multiplicative_expression
	: unary_expression multiplicative_expression_tail?
	;
	
multiplicative_expression_tail
	: (MULT | DIV | POWER)  unary_expression multiplicative_expression_tail?
	;
	
unary_expression
	: const_literal
	| lvalue
	| LPAREN expr RPAREN
	;
			
lvalue
	: ID (LBRACK expr RBRACK)?
	;
	