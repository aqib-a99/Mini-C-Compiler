public class Parser
{
    public static final int OP         = 10;    // "+", "-", "*", "/"
    public static final int RELOP      = 11;    // "<", ">", "=", "<>", "<=", ">="
    public static final int TYPEOF     = 12;    // "::"
    public static final int ASSIGN     = 13;    // ":="
    public static final int LPAREN     = 14;    // "("
    public static final int RPAREN     = 15;    // ")"
    public static final int SEMI       = 16;    // ";"
    public static final int COMMA      = 17;    // ","
    public static final int NUM        = 18;    // number
    public static final int ID         = 19;    // identifier
    public static final int BEGIN      = 20;    // "begin"
    public static final int END        = 21;    // "end"
    public static final int INT        = 22;    // "int"
    public static final int PRINT      = 23;    // "print"
    public static final int FUNCTION   = 24;    // "function"
    public static final int IF         = 25;    // "if"
    public static final int THEN       = 26;    // "then"
    public static final int ELSE       = 27;    // "else"
    public static final int WHILE      = 28;    // "while"

    Compiler         compiler;
    Lexer            lexer;     // lexer.yylex() returns token-name
    public ParserVal yylval;    // yylval contains token-attribute

    public Parser(java.io.Reader r, Compiler compiler) throws Exception
    {
        this.compiler = compiler;
        this.lexer    = new Lexer(r, this);
    }

    public int yyparse() throws Exception
    {
        int x = 1;
        while ( true )
        {
            int token = lexer.yylex();  // get next token-name
            Object attr = yylval.obj;   // get      token-attribute
            String tokenname = "PLACEHOLDER";

            switch(token){
                case 10:
                    tokenname = "OP";
                    break;
                case 11:
                    tokenname = "RELOP";
                    break;
                case 12:
                    tokenname = "TYPEOF";
                    break;
                case 13:
                    tokenname = "ASSIGN";
                    break;
                case 14:
                    tokenname = "LPAREN";
                    break;
                case 15:
                    tokenname = "RPAREN";
                    break;
                case 16:
                    tokenname = "SEMI";
                    break;
                case 17:
                    tokenname = "COMMA";
                    break;
                case 18:
                    tokenname = "NUM";
                    break;
                case 19:
                    tokenname = "ID";
                    break;
                case 20:
                    tokenname = "BEGIN";
                    break;
                case 21:
                    tokenname = "END";
                    break;
                case 22:
                    tokenname = "INT";
                    break;
                case 23:
                    tokenname = "PRINT";
                    break;
                case 24:
                    tokenname = "FUNCTION";
                    break;
                case 25:
                    tokenname = "IF";
                    break;
                case 26:
                    tokenname = "THEN";
                    break;
                case 27:
                    tokenname = "ELSE";
                    break;
                case 28:
                    tokenname = "WHILE";
                    break;
            }

            if(token == 0)
            {
                // EOF is reached
                System.out.println("Success!");
                return 0;
            }
            if(token == -1)
            {
                // error
                System.out.println("Error! There is a lexical error at line " + lexer.lineno + " and column " + lexer.column + ".");
                return -1;
            }

            System.out.println("<" + tokenname + ", token-attr:\"" + attr + "\", lineno:" + lexer.lineno + ", column:" + lexer.column + ">");
        }
    }
}
