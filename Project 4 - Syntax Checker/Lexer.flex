/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2000 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%%

%class Lexer
%byaccj

%{

  public Parser   parser;
  public int line;
  public int column;

  public Lexer(java.io.Reader r, Parser parser) {
    this(r);
    this.parser = parser;
    this.line = 1;
    this.column = 1;
  }
%}


TYPEOF          =           "::"
PRINT           =           "print"
FUNCTION        =           "function"
BOOL            =           "bool"
INT             =           "int"
NEW             =           "new"
WHILE           =           "while"
IF              =           "if"
THEN            =           "then"
ELSE            =           "else"
PTR             =           "@"
BEGIN           =           "begin"
END             =           "end"
VAR             =           "var"
LPAREN          =           "("
RPAREN          =           ")"
LBRACKET        =           "["
RBRACKET        =           "]"
ASSIGN          =           ":="
RELOP           =           "<"|">"|"<="|">="|"="|"<>"
EXPROP          =           "+"|"-"|"or"
TERMOP          =           "*"|"/"|"and"
SEMI            =           ";"
COMMA           =           ","
BOOL_LIT        =           "true"|"false"
INT_LIT         =           [0-9]+
IDENT           =           [a-zA-Z_][a-zA-Z0-9_]*
COMMENT         =           "%".*
NEWLINE         =           \n
WHITESPACE      =           [ \t\r]+
BLKCOMMENT      =           "(*"([^]|\n)*"*)"
%%

{TYPEOF}                              {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.TYPEOF           ; }
{PRINT}                               {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.PRINT            ; }
{FUNCTION}                            {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.FUNCTION         ; }
{BOOL}                                {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.BOOL             ; }
{INT}                                 {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.INT              ; }
{NEW}                                 {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.NEW              ; } 
{WHILE}                               {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.WHILE            ; }
{IF}                                  {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.IF               ; }
{THEN}                                {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.THEN             ; }                                
{ELSE}                                {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.ELSE             ; }                            
{PTR}                                 {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.PTR              ; }
{BEGIN}                               {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.BEGIN            ; }
{END}                                 {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.END              ; }
{VAR}                                 {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.VAR              ; }
{LPAREN}                              {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.LPAREN           ; }
{RPAREN}                              {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.RPAREN           ; }
{LBRACKET}                            {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.LBRACKET         ; }
{RBRACKET}                            {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.RBRACKET         ; }
{ASSIGN}                              {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.ASSIGN           ; }
{RELOP}                               {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.RELOP            ; }
{EXPROP}                              {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.EXPROP           ; }
{TERMOP}                              {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.TERMOP           ; }
{SEMI}                                {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.SEMI             ; }
{COMMA}                               {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.COMMA            ; }
{BOOL_LIT}                            {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.BOOL_LIT         ; }
{INT_LIT}                             {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.INT_LIT          ; }
{IDENT}                               {         parser.yylval = new ParserVal((Object)yytext()); this.column += yytext().toString().length(); return Parser.IDENT            ; }
{COMMENT}                             {         this.column += yytext().toString().length();                                                                          }
{NEWLINE}                             {         this.line++; this.column = 1;                                                                         }
{WHITESPACE}                          {         this.column += yytext().toString().length();                                                                               }
{BLKCOMMENT}                          {         this.column += yytext().toString().length();                                                                              } 
\b                                    {         System.err.println("Sorry, backspace doesn't work")                             ; }

/* error fallback */
[^]                                   {         System.err.println("Error: unexpected character '"+yytext()+"'"); return -1     ; }
