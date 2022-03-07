grammar VScript;

@header {
package me.maxih.vscript.gen;
}

program : (funcDef | stat)* EOF ;

funcDef : 'func' name=ID '(' (params+=ID (',' params+=ID)*)? ')' block ;

block : '{' stat* '}' ;

stat : varDecl
     | assignment
     | arrayAssignment
     | ifStat
     | whileStat
     | returnStat
     | breakStat
     | (expr SEMI)
     | SEMI
     ;

varDecl : 'var' ID '=' expr SEMI ;

assignment : ID '=' expr SEMI ;

arrayAssignment : ID ('[' index=expr ']')+ '=' value=expr SEMI;

ifStat : 'if' '(' expr ')' thenBlock=block ('else' (elseBlock=block | elseIf=ifStat))? ;

whileStat : 'while' '(' expr ')' block ;

returnStat : 'return' expr SEMI ;

breakStat : 'break' ;

expr : expr op='[' expr ']'  // Array access
     | unaryOp=('!'|'-') expr
     | expr op=('*' | '/' | '%') expr
     | expr op=('+' | '-') expr
     | expr op=('<' | '>' | '<=' | '>=') expr
     | expr op=('==' | '!=') expr
     | expr op='&&' expr
     | expr op='||' expr
     | atom
     ;

atom : (INT | FLOAT)    #numberAtom
	 | (TRUE | FALSE)   #booleanAtom
	 | STRING           #stringAtom
	 | NULL             #nullAtom
	 | ID               #idAtom
	 | call             #callAtom
	 | array            #arrayAtom
	 | '(' expr ')'     #parensExpr
     ;

call : ID '(' (args+=expr (',' args+=expr)*)? ')' ;

array : '[' (expr (',' expr)*)? ']' ;


SEMI : ';' ;

INT : '-'? [0-9]+ ;

FLOAT : INT '.' INT ;

TRUE : 'true' ;

FALSE : 'false' ;

STRING : '"' (ESC|.)*? '"' ;

fragment
ESC : '\\"' | '\\\\' ;

NULL : 'null' ;

ID : [a-zA-Z] [a-zA-Z0-9]* ;

WS : [ \t\r\n]+ -> channel(HIDDEN) ;

LINE_COMMENT : '//' .*? '\r'? '\n' -> channel(HIDDEN) ;
COMMENT : '/*' .*? '*/' -> channel(HIDDEN) ;
