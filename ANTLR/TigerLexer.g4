lexer grammar TigerLexer;

@lexer::header {
    package ANTLR;
}

WHITESPACE : (' ' | '\t') -> skip ;
NEWLINE    : ('\r\n' | '\r' | '\n') -> skip ;

MAIN       : 'main' ;
COMMA      : ',' ;
SEMICOLON  : ';' ;
LPAREN     : '(' ;
RPAREN     : ')' ;
LBRACK     : '[' ;
RBRACK     : ']' ;
LBRACE     : '{' ;
RBRACE     : '}' ;
PERIOD     : '.' ;
PLUS       : '+' ;
MINUS      : '-' ;
POWER      : '**';
MULT       : '*' ;
DIV        : '/' ;
EQEQ       : '==';
NEQ        : '!=' ;
LESSER     : '<' ;
GREATER    : '>' ;
LESSEREQ   : '<=' ;
GREATEREQ  : '>=' ;
AND        : '&' ;
OR         : '|' ;
ASSIGN     : ':=' ;
COLON      : ':' ;
EQ         : '=' ;

ARRAY      : 'arr' ;
RECORD     : 'record' ;
BREAK      : 'break' ;
DO         : 'do' ;
ELSE       : 'else' ;
END        : 'end' ;
FLOAT      : 'float' ;
FOR        : 'for' ;
FUNCTION   : 'function' ;
IF         : 'if' ;
INT        : 'int' ;
IN         : 'in' ;
LET        : 'let' ;
OF         : 'of' ;
THEN       : 'then' ;
TO         : 'to' ;
TYPE       : 'type' ;
VAR        : 'var' ;
WHILE      : 'while' ;
ENDIF      : 'endif' ;
BEGIN      : 'begin' ;
ENDDO      : 'enddo' ;
RETURN     : 'return' ;

//PARAMS     : (INTLIT | FLOATLIT | ID)+ ;
//assign     : ID ASSIGN (INTLIT | FLOATLIT ) ;
//add        : INTLIT PLUS INTLIT ;
//sub        : INTLIT MINUS INTLIT ;
//mul        : INTLIT MULT INTLIT ;
//div        : INTLIT DIV INTLIT ;
//and        : INTLIT AND INTLIT ;
//or         : INTLIT OR INTLIT ;
////goto       : BREAK ;
//breq       : IF LPAREN ID NEQ ID RPAREN THEN ;
//brneq      : IF LPAREN ID '==' ID RPAREN THEN ;
//brlt       : IF LPAREN ID LESSEREQ ID RPAREN THEN ;
//brgt       : IF LPAREN ID GREATEREQ ID RPAREN THEN ;
//brgeq      : IF LPAREN ID LESSER ID RPAREN THEN ;
//brleq      : IF LPAREN ID GREATER ID RPAREN THEN ;
////return     : RETURN ID SEMICOLON ;
//call       : ID LPAREN PARAMS RPAREN SEMICOLON ;
//rcall      : ID ASSIGN ID LPAREN PARAMS RPAREN SEMICOLON ;
//store      : ID LBRACK ID RBRACK ASSIGN ID ;
//load       : ID ASSIGN ID LBRACK ID RBRACK ;
//assignarr  : VAR ID COLON ID ASSIGN ID SEMICOLON ;

ID         : ([a-z] | [A-Z])+ ;
INTLIT     : [0-9]+ ;
FLOATLIT   : [0-9]+ ('.' [0-9]+)?;
