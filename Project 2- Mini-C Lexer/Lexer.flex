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
  public int      lineno;
  public int      column;

  public Lexer(java.io.Reader r, Parser parser) {
    this(r);
    this.parser = parser;
    this.lineno = 1;
    this.column = 1;
  }
%}

int         = [0-9]+
identifier  = [a-z][a-z0-9]*
newline     = \n
whitespace  = [ \t\r]+
linecomment = "%".*

%%

"print"                             { parser.yylval = new ParserVal(null            ); return Parser.PRINT   ; }
"function"                          { parser.yylval = new ParserVal(null            ); return Parser.FUNCTION; }
"int"                               { parser.yylval = new ParserVal(null            ); return Parser.INT     ; }
"begin"                             { parser.yylval = new ParserVal(null            ); return Parser.BEGIN   ; }
"end"                               { parser.yylval = new ParserVal(null            ); return Parser.END     ; }
"("                                 { parser.yylval = new ParserVal(null            ); return Parser.LPAREN  ; }
")"                                 { parser.yylval = new ParserVal(null            ); return Parser.RPAREN  ; }
";"                                 { parser.yylval = new ParserVal(null            ); return Parser.SEMI    ; }
":="                                { parser.yylval = new ParserVal(null            ); return Parser.ASSIGN  ; }
"+"                                 { parser.yylval = new ParserVal(null            ); return Parser.OP      ; }
"<"                                 { parser.yylval = new ParserVal(null            ); return Parser.RELOP   ; }
{int}                               { parser.yylval = new ParserVal((Object)yytext()); return Parser.INT_LIT ; }
{identifier}                        { parser.yylval = new ParserVal((Object)yytext()); return Parser.IDENT   ; }
{linecomment}                       { System.out.println("line comment: \""   +yytext()+"\""); /* skip */ }
{newline}                           { System.out.println("newline"                          ); /* skip */ }
{whitespace}                        { System.out.println("whitespace: \""+yytext()+"\""     ); /* skip */ }
"(*"(.|\n)*"*)"                     { System.out.println("block comment begin\""            );
                                      System.out.println(yytext()                           );
                                      System.out.println("block comment end+\""             ); /* skip */ }


\b     { System.err.println("Sorry, backspace doesn't work"); }

/* error fallback */
[^]    { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }
