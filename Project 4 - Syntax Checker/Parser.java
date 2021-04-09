import java.util.List;
import java.util.ArrayList;

public class Parser
{
    public static final int ENDMARKER   =  0;
    public static final int LEXERROR    =  1;
    public static final int PRINT       = 11;
    public static final int FUNCTION    = 12;
    public static final int BOOL        = 13;
    public static final int INT         = 14;
    public static final int NEW         = 15;
    public static final int WHILE       = 16;
    public static final int IF          = 17;
    public static final int THEN        = 18;
    public static final int ELSE        = 19;
    public static final int PTR         = 20;
    public static final int BEGIN       = 21;
    public static final int END         = 22;
    public static final int VAR         = 23;
    public static final int LPAREN      = 24;
    public static final int RPAREN      = 25;
    public static final int LBRACKET    = 26;
    public static final int RBRACKET    = 27;
    public static final int ASSIGN      = 28;
    public static final int RELOP       = 29;
    public static final int EXPROP      = 30;
    public static final int TERMOP      = 31;
    public static final int SEMI        = 32;
    public static final int COMMA       = 33;
    public static final int TYPEOF      = 34;
    public static final int BOOL_LIT    = 35;
    public static final int INT_LIT     = 36;
    public static final int IDENT       = 37;
    public static final int COMMENT     = 38;
    public static final int NEWLINE     = 39;
    public static final int WHITESPACE  = 40;
    public static final int BLKCOMMENT  = 41;


    public class Token
    {
        public int       type;
        public ParserVal attr;
        public Token(int type, ParserVal attr) {
            this.type   = type;
            this.attr   = attr;
        }
    }

    public ParserVal yylval;
    Token _token;
    Lexer _lexer;
    public Parser(java.io.Reader r) throws Exception
    {
        _lexer = new Lexer(r, this);
        _token = null;
        Advance();
    }

    public void Advance() throws Exception
    {
        int token_type = _lexer.yylex();
        if(token_type ==  0)      _token = new Token(ENDMARKER , null  );
        else if(token_type == -1) _token = new Token(LEXERROR  , yylval);
        else _token = new Token(token_type, yylval);
        
    }

    public String Match(int token_type) throws Exception
    {
        boolean match = (token_type == _token.type);
        String lexeme = "";

        if(match == false){
            if(token_type == BEGIN)
                throw new Exception("\"begin\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");

            else if(token_type == PRINT)
                throw new Exception("\"print\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
            
            else if(token_type == FUNCTION)
                throw new Exception("\"function\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");

            else if(token_type == BOOL)
                throw new Exception("\"bool\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
            
            else if(token_type == INT)
                throw new Exception("\"int\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
            
            else if(token_type == NEW)
                throw new Exception("\"new\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
            
            else if(token_type == WHILE)
                throw new Exception("\"while\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
    
            else if(token_type == IF)
                throw new Exception("\"if\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
            
            else if(token_type == THEN)
                throw new Exception("\"then\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");

            else if(token_type == ELSE)
                throw new Exception("\"else\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
    
            else if(token_type == PTR)
                throw new Exception("\"@\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
    
            else if(token_type == BEGIN)
                throw new Exception("\"begin\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");

            else if(token_type == END)
                throw new Exception("\"end\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
            
            else if(token_type == VAR)
                throw new Exception("\"var\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");

            else if(token_type == LPAREN)
                throw new Exception("\"(\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
            
            else if(token_type == RPAREN)
                throw new Exception("\")\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
            
            else if(token_type == LBRACKET)
                throw new Exception("\"[\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
            
            else if(token_type == RBRACKET)
                throw new Exception("\"]\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
    
            else if(token_type == ASSIGN)
                throw new Exception("\":=\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
             
            else if(token_type == RELOP)
                throw new Exception("\"RELOP\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
             
            else if(token_type == EXPROP || token_type == EXPROP || token_type == TERMOP || token_type == BOOL_LIT || token_type == INT_LIT)
                throw new Exception("Incorrect expression at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
            
            else if(token_type == SEMI)
                throw new Exception("\";\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");

            else if(token_type == COMMA)
                throw new Exception("\",\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
                
            else if(token_type == TYPEOF)
                throw new Exception("\"::\"" + " is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
                
            else if(token_type == IDENT)
            throw new Exception("An identifier is expected instead of " + "\"" + yylval.obj.toString() + "\" at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
        }
            

        if(_token.type != ENDMARKER){
            Advance();
            
        }
            

        return lexeme;
    }

    public int yyparse() throws Exception
    {
        try
        {
            parse();
            System.out.println("Success: no syntax error is found.");
        }
        catch(Exception e)
        {
            System.out.println("Error: There exist(s) syntax error(s).");
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public List<String> parse() throws Exception
    {
        return program();
    }


    // 1. program
    public List<String> program() throws Exception
    {
        switch(_token.type)
        {
            // program -> decl_list
            case FUNCTION:
                decl_list();
                return null;

            case ENDMARKER:
                decl_list();
                return null;

        }
        throw new Exception("Error in program()");
    }

    // 2. decl_list
    public List<String> decl_list() throws Exception
    {
        switch(_token.type)
        {
            // decl_list -> decl_list'
            case FUNCTION:
                decl_list_();
                return null;
            
            case ENDMARKER:
                Match(ENDMARKER);
                decl_list_();
                return null;

        }
        throw new Exception("Error in decl_list()");
    }

    // 3. decl_list_
    public List<String> decl_list_() throws Exception
    {
        switch(_token.type)
        {
            // decl_list' -> fun_decl decl_list' | ' '
            case FUNCTION:
                fun_decl();
                decl_list_();
                return null;

            case ENDMARKER:
                Match(ENDMARKER);
                return null;
        }
        throw new Exception("Error in decl_list()");
    }

    // 4. type_spec
    public String type_spec() throws Exception
    {
        switch(_token.type)
        {
            // type_spec	-> "int"
            case INT:
                Match(INT);
                type_spec_();
                return null;
            
            case BOOL:
                Match(BOOL);
                type_spec_();
                return null;
        }
        throw new Exception("Error in type_spec()");
    }


    //5. type_spec_
    public String type_spec_() throws Exception
    {
        switch(_token.type){

            // type_spec' -> epsilon
            case RPAREN:
                return null;
            
            case BEGIN:
                return null;
            
            case SEMI:
                return null;
            
            case LBRACKET:
                return null;

            case COMMA:
                return null;

            // type_spec' -> "@" type_spec'
            case PTR:
                Match(PTR);
                type_spec_();
                return null;
        }
            
        throw new Exception("Incorrect type specification at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
    }


    // 6. fun_decl
    public List<String> fun_decl() throws Exception
    {
        switch(_token.type)
        {
            //FUNCTION IDENT LPAREN params RPAREN TYPEOF type_spec BEGIN local_decls stmt_list END
            // fun_decl	-> type_spec IDENT "(" params ")" compound_stmt
            case FUNCTION:
                Match(FUNCTION);
                Match(IDENT);
                Match(LPAREN);
                params();
                Match(RPAREN);
                Match(TYPEOF);
                type_spec();
                Match(BEGIN);
                local_decls();
                stmt_list();
                Match(END);
                return null;
        }
        throw new Exception("Error in fun_decl()");
    }


    // 7. params
    public String params() throws Exception
    {
        switch(_token.type){
            // params -> param_list
            case IDENT:
                param_list();
                return null;

            // params -> epsilon
            case RPAREN:
                return null;

        }
        throw new Exception("Error in params()");
    }


    // 8. param_list
    public List<String> param_list() throws Exception
    {
        switch(_token.type){
            case IDENT:
                param();
                param_list_();
                return null;

            default:
                throw new Exception("Error in param_list()");
        
        }
    }

    // 9. param_list_
    public List<String> param_list_() throws Exception
    {
        switch(_token.type){
            // param_list' -> COMMA param param_list'
            case COMMA:
                Match(COMMA);
                param();
                param_list_();
                return null;

            // param_list' -> epsilon
            case RPAREN:
                return null;
    }
    throw new Exception("Error in param_list_()");
}

    // 10. param
    public List<String> param() throws Exception
    {
        switch(_token.type){
            // param_list' -> COMMA param param_list'
            case IDENT:
                Match(IDENT);
                Match(TYPEOF);
                type_spec();
                return null;
        }

        throw new Exception("Error in param()");
    }

    //11. stmt_list
    public List<String> stmt_list() throws Exception
    {
        switch(_token.type){
            // stmt_list -> stmt_list'
            case IDENT:
                stmt_list_();
                return null;
            
            case BEGIN:
                stmt_list_();
                return null;

            case END:
                stmt_list_();
                return null;

            case PRINT:
                stmt_list_();
                return null;

            case IF:
                stmt_list_();
                return null;

            case WHILE:
                stmt_list_();
                return null;

            case ELSE:
                stmt_list_();
                return null;
        }

        throw new Exception("Incorrect statement at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
    }

    // 12. stmt_list_
    public List<String> stmt_list_() throws Exception
    {
        switch(_token.type){
            // stmt_list' -> stmt stmt_list'
            case IDENT:
                stmt();
                stmt_list_();
                return null;
            
            case BEGIN:
                stmt();
                stmt_list_();
                return null;

            case IF:
                stmt();
                stmt_list_();
                return null;

            case PRINT:
                stmt();
                stmt_list_();
                return null;

            case WHILE:
                stmt();
                stmt_list_();
                return null;

            // stmt_list' -> epsilon
            case ELSE:
                return null;

            case END:
                return null;

        }

        throw new Exception("Incorrect statement at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
    }

    // 13. stmt
    public List<String> stmt() throws Exception
    {
        switch(_token.type){
            // stmt_list' -> stmt stmt_list'
            case IDENT:
                expr_stmt();
                return null;
            
            case BEGIN:
                compound_stmt();
                return null;

            case IF:
                if_stmt();
                return null;

            case PRINT:
                print_stmt();
                return null;

            case WHILE:
                while_stmt();
                return null;

        }

        throw new Exception("Error in stmt()");
    }

    // 14. expr_stmt
    public List<String> expr_stmt() throws Exception
    {
        switch(_token.type){
            // expr_stmt -> IDENT ASSIGN expr SEMI
            case IDENT:
                Match(IDENT);
                Match(ASSIGN);
                expr();
                Match(SEMI);
                return null;

        }

        throw new Exception("Error in expr_stmt()");
    }

    // 15. print_stmt
    public List<String> print_stmt() throws Exception
    {
        switch(_token.type){
            // print_stmt -> PRINT expr SEMI
            case PRINT:
                Match(PRINT);
                expr();
                Match(SEMI);
                return null;
        }

        throw new Exception("Error in print_stmt()");
    }


    // 16. if_stmt
    public List<String> if_stmt() throws Exception
    {
        switch(_token.type){
            // if_stmt -> IF expr THEN local_decls stmt_list ELSE local_decls stmt_list END
            case IF:
                Match(IF);
                expr();
                Match(THEN);
                local_decls();
                stmt_list();
                Match(ELSE);
                local_decls();
                stmt_list();
                Match(END);
                return null;
        }

        throw new Exception("Error in if_stmt()");
    }

    // 17. while_stmt
    public List<String> while_stmt() throws Exception
    {
        switch(_token.type){
            // while_stmt -> WHILE expr compound_stmt
            case WHILE:
                Match(WHILE);
                expr();
                compound_stmt();
                return null;
        }

        throw new Exception("Error in while_stmt()");
    }

    // 18. compound_stmt
    public List<String> compound_stmt() throws Exception
    {
        switch(_token.type){
            // compound_stmt -> BEGIN local_decls stmt_list END
            case BEGIN:
                Match(BEGIN);
                local_decls();
                stmt_list();
                Match(END);
                return null;
        }

        throw new Exception("Error in compound_stmt()");
    }

    // 19. local_decls
    public List<String> local_decls() throws Exception
    {
        switch(_token.type){
            //local_decls -> local_decls'
            case FUNCTION:
                return null;

            case IDENT:
                return null;

            case BEGIN:
                return null;

            case END:
                return null;
            
            case PRINT:
                return null;

            case IF:

                return null;

            case ELSE:
                return null;

            case WHILE:
                return null;

            case VAR:
                local_decls_();
                return null;

        }

        throw new Exception("Incorrect declaration of a local variable at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
    }

    // 20. local_decls_
    public List<String> local_decls_() throws Exception
    {
        switch(_token.type){
            // local_decls' -> local_decl local_decls'
            case VAR:
                local_decl();
                local_decls_();
                return null;
            
            // local_decls' -> epsilon
            case FUNCTION:
                return null;

            case IDENT:
                return null;

            case BEGIN:
                return null;

            case END:
                return null;

            case PRINT:
                return null;

            case IF:
                return null;

            case ELSE:
                return null;

            case ENDMARKER:
                return null;

            case WHILE:
                return null;
        }

        throw new Exception("Error in local_decls_()");
    }

    // 21. local_decl
    public List<String> local_decl() throws Exception
    {
        switch(_token.type){
            // local_decl -> VAR IDENT TYPEOF type_spec SEMI
            case VAR:
                Match(VAR);
                Match(IDENT);
                Match(TYPEOF);
                type_spec();
                Match(SEMI);
                return null;
        }

        throw new Exception("Error in local_decl()");
    }
    

    // 22. args
    public List<String> args() throws Exception
    {
        switch(_token.type){
            // args -> arg_list
            case IDENT:
                arg_list();
                return null;

            case LPAREN:
                arg_list();
                return null;

            case INT_LIT:
                arg_list();
                return null;

            case BOOL_LIT:
                arg_list();
                return null;

            case NEW:
                arg_list();
                return null;

            // args -> epsilon
            case RPAREN:
                return null;
        }

        throw new Exception("Error in args()");
    }

    // 23. arg_list
    public List<String> arg_list() throws Exception
    {
        switch(_token.type){
            // arg_list -> expr arg_list'
            case IDENT:
                expr();
                arg_list_();
                return null;

            case LPAREN:
                expr();
                arg_list_();
                return null;

            case INT_LIT:
                expr();
                arg_list_();
                return null;

            case BOOL_LIT:
                expr();
                arg_list_();
                return null;

            case NEW:
                expr();
                arg_list_();
                return null;

        }

        throw new Exception("Error in arg_list()");
    }

    // 24. arg_list_
    public List<String> arg_list_() throws Exception
    {
        switch(_token.type){
            // arg_list' -> COMMA expr arg_list'
            case COMMA:
                Match(COMMA);
                expr();
                arg_list_();
                return null;

            // arg_list' -> epsilon
            case RPAREN:
                return null;

        }

        throw new Exception("Incorrect argument format at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
    }

    // 24. expr
    public List<String> expr() throws Exception
    {
        switch(_token.type){
            // expr -> term expr'
            case IDENT:
                term();
                expr_();
                return null;

            case LPAREN:
                term();
                expr_();
                return null;

            case INT_LIT:
                term();
                expr_();
                return null;

            case BOOL_LIT:
                term();
                expr_();
                return null;

            case NEW:
                term();
                expr_();
                return null;
        }

        throw new Exception("Incorrect expression at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
    }

    // 24. expr
    public List<String> expr_() throws Exception
    {
        switch(_token.type){
            // expr' -> EXPROP term expr'
            case EXPROP:
                Match(EXPROP);
                term();
                expr_();
                return null;

            // expr' -> RELOP term expr'
            case RELOP:
                Match(RELOP);
                term();
                expr_();
                return null;

            // expr' -> epsilon
            case RPAREN:
                return null;

            case BEGIN:
                return null;

            case COMMA:
                return null;

            case SEMI:
                return null;

            case THEN:
                return null;

            case RBRACKET:
                return null;
        }

        throw new Exception("Error in expr_()");
    }

    // 25. term
    public List<String> term() throws Exception
    {
    switch(_token.type){
        // term -> factor term'
        case IDENT:
            factor();
            term_();
            return null;
        
        case LPAREN:
            factor();
            term_();
            return null;
        
        case INT_LIT:
            factor();
            term_();
            return null;

        case BOOL_LIT:
            factor();
            term_();
            return null;

        case NEW:
            factor();
            term_();
            return null;
    }

    throw new Exception("Incorrect expression at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
}


    // 26. term_
    public List<String> term_() throws Exception
    {
    switch(_token.type){
        // term' -> TERMOP factor term'
        case TERMOP:
            Match(TERMOP);
            factor();
            term_();
            return null;
        
        // term' -> epsilon
        case RPAREN:
            return null;

        case BEGIN:
            return null;

        case COMMA:
            return null;

        case SEMI:
            return null;

        case THEN:
            return null;

        case EXPROP:
            return null;

        case RELOP:
            return null;

        case RBRACKET:
            return null;
    }

    throw new Exception("Incorrect expression at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
}

    // 27. factor
    public List<String> factor() throws Exception
    {
    switch(_token.type){
        // factor -> IDENT factor'
        case IDENT:
            Match(IDENT);
            factor_();
            return null;
        
        // factor -> LPAREN expr RPAREN
        case LPAREN:
            Match(LPAREN);
            expr();
            Match(RPAREN);
            return null;

        // factor -> INT_LIT
        case INT_LIT:
            Match(INT_LIT);
            return null;

        // factor -> BOOL_LIT
        case BOOL_LIT:
            Match(BOOL_LIT);
            return null;

        // factor -> NEW type_spec LBRACKET expr RBRACKET
        case NEW:
            Match(NEW);
            type_spec();
            Match(LBRACKET);
            expr();
            Match(RBRACKET);
            return null;
    }

    throw new Exception("Incorrect expression at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
}

    // 27. factor_
    public List<String> factor_() throws Exception
    {
    switch(_token.type){
        // factor' -> LBRACKET expr RBRACKET
        case LBRACKET:
            Match(LBRACKET);
            expr();
            Match(RBRACKET);
            return null;
        
        // factor' -> LPAREN args RPAREN
        case LPAREN:
            Match(LPAREN);
            args();
            Match(RPAREN);
            return null;

        // factor' -> epsilon
        case RPAREN:
            return null;

        case BEGIN:
            return null;

        case COMMA:
            return null;

        case SEMI:
            return null;

        case THEN:
            return null;

        case EXPROP:
            return null;

        case RELOP:
            return null;

        case TERMOP:
            return null;

        case RBRACKET:
            return null;

    }

    throw new Exception("Incorrect expression at " + _lexer.line + ":" + (_lexer.column - yylval.obj.toString().length()) + ".");
}
    
}
