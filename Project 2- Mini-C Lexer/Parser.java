import java.util.*;


public class Parser
{
    public static final int PRINT       = 10;
    public static final int FUNCTION    = 11;
    public static final int INT         = 12;
    public static final int BEGIN       = 13;
    public static final int END         = 14;
    public static final int LPAREN      = 15;
    public static final int RPAREN      = 16;
    public static final int SEMI        = 17;
    public static final int ASSIGN      = 18;
    public static final int OP          = 19;
    public static final int RELOP       = 20;
    public static final int INT_LIT     = 21;
    public static final int IDENT       = 22;
	public static final int BOOL		= 23;
	public static final int FLOAT		= 24;
	public static final int STRUCT		= 25;
	public static final int SIZE		= 26;
	public static final int NEW			= 27;
	public static final int IF			= 28;
	public static final int THEN		= 29;
	public static final int ELSE		= 30;
	public static final int WHILE		= 31;
	public static final int RETURN		= 32;
	public static final int BREAK		= 33;
	public static final int CONTINUE	= 34;
	public static final int TRUE		= 35;
	public static final int FALSE		= 36;
	public static final int COMMA		= 37;
	public static final int LBRACKET	= 38;
	public static final int RBRACKET	= 39;
	public static final int DOT			= 40;
	public static final int AND			= 41;
	public static final int TYPEOF		= 42;
	public static final int FLOAT_VALUE = 43;
	public static final int NEWLINE 	= 44;
	public static final int WHITESPACE  = 45;
	public static final int COMMENT		= 46;
	public static final int BOOL_VALUE	= 47;
	
	public static int line;
	public static int column;
	public static int lexemeLength;
	
	public static HashMap<String, Integer> symbol_table; //Creating new symbol table for identifiers
	public static int symbol_table_index;
	
    Lexer   lexer;
    public ParserVal yylval;
	
    public Parser(java.io.Reader r) throws java.io.IOException
    {
        this.lexer = new Lexer(r, this);
		this.line = 1;
		this.column = 1;
		this.lexemeLength = 0;
		this.symbol_table = new HashMap<String, Integer>();
		symbol_table_index = 0;
    }

    

    public int yyparse() throws java.io.IOException
    {
        while ( true )
        {
            int token = lexer.yylex();
            if(token == 0)
            {
                // EOF is reached
                return 0;
            }
            if(token == -1)
            {
                // error
                return -1;
            }
			
			switch(token){
				case PRINT:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<PRINT :"+ line + ":" + column + ">");
					break;
				case FUNCTION:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<FUNCTION :"+ line + ":" + column + ">");
					break;
				case INT:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<INT :"+ line + ":" + column + ">");
					break;
				case BEGIN:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<BEGIN :"+ line + ":" + column + ">");
					break;
				case END:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<END :"+ line + ":" + column + ">");
					break;		
				case LPAREN:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<LPAREN :"+ line + ":" + column + ">");
					break;
				case RPAREN:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<RPAREN :"+ line + ":" + column + ">");
					break;
				case SEMI:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<SEMI :"+ line + ":" + column + ">");
					break;
				case ASSIGN:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<ASSIGN :"+ line + ":" + column + ">");
					break;
				case OP:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<OP, " + lexer.yytext() + " :"+ line + ":" + column + ">");
					break;
				case RELOP:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<RELOP, " + lexer.yytext() + " :"+ line + ":" + column + ">");
					break;
				case IDENT:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					
					//Check if the given lexeme exists in symbol_table as a value. If so, return the key. Otherwise, add this lexeme 
					if(symbol_table.containsKey(lexer.yytext())){
						//print in this format: <ID, _key_ line:column>
						System.out.print("<ID, " + symbol_table.get(lexer.yytext()) + " :" + line + ":" + column + ">");
					}
					else{
						symbol_table.put(lexer.yytext(), symbol_table_index);
						System.out.print("<<symbol table entity ["+ symbol_table_index + ", \"" + lexer.yytext() + "\"]>><ID, " + symbol_table_index + " :" + line + ":" + column +">");
						symbol_table_index++;
					}
					break;	
				case BOOL:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<BOOL :"+ line + ":" + column + ">");
					break;
				case FLOAT:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<FLOAT :"+ line + ":" + column + ">");
					break;
				case STRUCT:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<STRUCT :"+ line + ":" + column + ">");
					break;	
				case SIZE:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<SIZE :"+ line + ":" + column + ">");
					break;	
				case NEW:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<NEW :"+ line + ":" + column + ">");
					break;
				case IF:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<IF :"+ line + ":" + column + ">");
					break;
				case THEN:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<THEN :"+ line + ":" + column + ">");
					break;	
				case ELSE:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<ELSE :"+ line + ":" + column + ">");
					break;
				case WHILE:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<WHILE :"+ line + ":" + column + ">");
					break;		
				case RETURN:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<RETURN :"+ line + ":" + column + ">");
					break;	
				case BREAK:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<BREAK :"+ line + ":" + column + ">");
					break;	
				case CONTINUE:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<CONTINUE :"+ line + ":" + column + ">");
					break;		
				case TRUE:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<TRUE :"+ line + ":" + column + ">");
					break;		
				case FALSE:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<FALSE :"+ line + ":" + column + ">");
					break;	
				case COMMA:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<COMMA :"+ line + ":" + column + ">");
					break;	
				case LBRACKET:	
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<LBRACKET :"+ line + ":" + column + ">");
					break;		
				case RBRACKET:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<RBRACKET :"+ line + ":" + column + ">");
					break;		
				case DOT:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<DOT :"+ line + ":" + column + ">");
					break;		
				case AND:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<AND :"+ line + ":" + column + ">");
					break;		
				case TYPEOF:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<TYPEOF :"+ line + ":" + column + ">");
					break;		
				case FLOAT_VALUE:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<FLOAT_VALUE, " + lexer.yytext() + " :"+ line + ":" + column + ">");
					break;		
				case INT_LIT:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<INT_VALUE, " + lexer.yytext() + " :"+ line + ":" + column + ">");
					break;		
				case NEWLINE:
					column = 1;
					line += 1;
					lexemeLength = 0;
					System.out.print("\n");
					break;	
				case WHITESPACE:
					column += lexemeLength + lexer.yytext().length();
					lexemeLength = 0;
					break;
				case COMMENT:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					break;
				case BOOL_VALUE:
					column += lexemeLength;
					lexemeLength = lexer.yytext().length();
					System.out.print("<BOOL_VALUE, " + lexer.yytext() + " :"+ line + ":" + column + ">");
					break;			
			}
        }
    }
}
