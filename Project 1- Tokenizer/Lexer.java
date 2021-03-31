import java.util.Arrays;

public class Lexer
{
    private static final char EOF        =  0;
    private Parser         yyparser; // parent parser object
    private java.io.Reader reader;   // input stream
    public int             lineno;   // line number
    public int             column;   // column
    public char[] first_buffer = new char[10];
    public char[] second_buffer = new char[10];
    public int current_buffer = 0; //0: first_buffer, 1: second_buffer
    public int forward;
    public int lBegin;
    public String[] keywords = new String[]{"int", "print", "function", "if", "then", "else", "begin", "end", "while"};



    public Lexer(java.io.Reader reader, Parser yyparser) throws Exception
    {
        this.reader   = reader;
        this.yyparser = yyparser;
        lineno = 1;
        column = 1;
        forward = -1;
        current_buffer = 0;
        for(int i = 0; i < 9; i++){
            first_buffer[i] = NextChar();
        }
        first_buffer[9] = EOF;

    }

    public char NextChar() throws Exception
    {
        // http://tutorials.jenkov.com/java-io/readers-writers.html
        int data = reader.read();
        if(data == -1)
        {
            return EOF;
        }
        return (char)data;
    }
    public int Fail()
{
    return -1;
}

    public void reloadBuffer() throws Exception {
        if(current_buffer == 0){
            for(int i = 0; i < 9; i++){
                second_buffer[i] = NextChar();
            }
            second_buffer[9] = EOF;
            current_buffer = 1;
        }
        else if(current_buffer == 1){
            for(int i = 0; i < 9; i++){
                first_buffer[i] = NextChar();
            }
            first_buffer[9] = EOF;
            current_buffer = 0;
        }

        forward = -1;
    }

    public char readChar() throws Exception {
        forward++;
        char c = EOF;
        if (current_buffer == 0) {
            if(first_buffer[forward] == EOF && forward == 9){
                reloadBuffer();
                forward++;
                c = second_buffer[forward];
            }
            else{
                c = first_buffer[forward];
            }

        }
        else if(current_buffer == 1){
            if(second_buffer[forward] == EOF && forward == 9){
                reloadBuffer();
                forward++;
                c = first_buffer[forward];
            }
            else{
                c = second_buffer[forward];
            }
        }
        return c;
    }

    public void retract(){
        forward--;
    }


    public int yylex() throws Exception
    {
        int state = 0;
        int acc = 0;
        lBegin = forward + 1;
        StringBuilder sb = new StringBuilder();

        while(true)
        {
            char c = readChar();
            if(c == '\n'){

            }

            boolean digitOccurrence = (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9');
            boolean lowerCaseOccurrence = (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f' || c == 'g' || c == 'h' || c == 'i' || c == 'j' || c == 'k' || c == 'l' || c == 'm' || c == 'n' || c == 'o' || c == 'p' || c == 'q' || c == 'r' || c == 's' || c == 't' || c == 'u' || c == 'v' || c == 'w' || c == 'x' || c == 'y' || c == 'z');
            boolean upperCaseOccurrence = (c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F' || c == 'G' || c == 'H' || c == 'I' || c == 'J' || c == 'K' || c == 'L' || c == 'M' || c == 'N' || c == 'O' || c == 'P' || c == 'Q' || c == 'R' || c == 'S' || c == 'T' || c == 'U' || c == 'V' || c == 'W' || c == 'X' || c == 'Y' || c == 'Z');
            boolean underscoreOccurrence = (c == '_');
            switch(state)
            {
                case 0:
                    if(c == '+'){
                        yyparser.yylval = new ParserVal((Object)"+");
                        column++;
                        return Parser.OP;
                    }
                    else if(c == '-'){
                        yyparser.yylval = new ParserVal((Object)"-");
                        column++;
                        return Parser.OP;
                    }
                    else if(c == '*'){
                        yyparser.yylval = new ParserVal((Object)"*");
                        column++;
                        return Parser.OP;
                    }
                    else if(c == '/'){
                        yyparser.yylval = new ParserVal((Object)"/");
                        column++;
                        return Parser.OP;
                    }

                    else if(c == '>'){
                        sb.append(c);
                        state = 1;
                        column++;
                        continue;

                    }

                    else if(c == '<'){
                        sb.append(c);
                        state = 2;
                        column++;
                        continue;
                    }

                    else if(c == '='){
                        yyparser.yylval = new ParserVal((Object)"=");
                        column++;
                        return Parser.OP;
                    }

                    else if(c == ':'){
                        sb.append(c);
                        state = 3;
                        column++;
                        continue;
                    }

                    else if(c == '('){
                        yyparser.yylval = new ParserVal((Object)"(");
                        column++;
                        return Parser.LPAREN;
                    }

                    else if(c == ')'){
                        yyparser.yylval = new ParserVal((Object)")");
                        column++;
                        return Parser.RPAREN;
                    }

                    else if(c == ';'){
                        yyparser.yylval = new ParserVal((Object)";");
                        column++;
                        return Parser.SEMI;
                    }

                    else if(c == ','){
                        yyparser.yylval = new ParserVal((Object)",");
                        column++;
                        return Parser.COMMA;
                    }

                    else if(digitOccurrence){
                        //A digit encountered:
                        sb.append(c);
                        state = 4;
                        column++;
                        continue;
                    }

                    //Split identifiers into two cases: one starting with underscore and the second starting with alphabet(lowercase or uppercase)
                    //Case where first character is an underscore
                    else if(underscoreOccurrence){
                        sb.append(c);
                        state = 6;
                        column++;
                        continue;
                    }

                    //Case where first character is an uppercase alphabet (cannot be a keyword, since they are all lowercase)
                    else if(upperCaseOccurrence){
                        sb.append(c);
                        state = 6;
                        column++;
                        continue;
                    }

                    //Case where first character is a lowercase alphabet (can potentially be a keyword)
                    else if(lowerCaseOccurrence){
                        sb.append(c);
                        state = 7;
                        column++;
                        continue;
                    }

                    else if(c == '.'){
                        return Fail();
                    }

                    else if(c == ' '){
                        state = 0;
                        column++;
                        continue;
                    }

                    else if(c == '\n'){
                        state = 0;
                        column = 1;
                        lineno++;
                        continue;
                    }

                    else if(c == '\t'){
                        state = 0;
                        column += 4;
                        continue;
                    }

                    if(c == EOF) { return 0;}
                    // return Fail();

                //State 1: When processed '>'
                case 1:
                    if(c == '='){
                        //Found '>='
                        sb.append(c);
                        acc++;
                    }

                    else{
                        retract();
                        column += acc;
                    }
                    yyparser.yylval= new ParserVal((Object)sb.toString());
                    return Parser.RELOP;

                //State 2: When processed '<'
                case 2:
                    if(c == '>') {
                        sb.append(c);
                        acc++;
                    }

                    else if(c == '='){
                        sb.append(c);
                        acc++;
                    }


                    else{
                        retract();
                        column += acc;
                    }

                    yyparser.yylval = new ParserVal((Object)sb.toString());
                    return Parser.RELOP;

                //State 3: When processed ':'
                case 3:
                    if(c == ':'){
                        sb.append(c);
                        acc++;
                        yyparser.yylval = new ParserVal((Object)sb.toString());
                        return Parser.TYPEOF;
                    }

                    else if(c == '='){
                        sb.append(c);
                        acc++;
                        yyparser.yylval = new ParserVal((Object)"=");
                        return Parser.ASSIGN;
                    }

                    else {
                        retract();
                        column += acc;
                        return Fail();
                    }

                //State 4: When processed a digit
                case 4:
                    if(digitOccurrence){
                        sb.append(c);
                        acc++;
                        state = 4;
                        continue;
                    }

                    else if(c == '.'){
                        sb.append(c);
                        state = 5;
                        acc++;
                        continue;
                    }

                    else {
                        retract();
                        column += acc;
                        yyparser.yylval = new ParserVal((Object) sb.toString());
                        return Parser.NUM;
                    }



                //State 5: When processed a digit followed by a period
                case 5:
                    if(c == '.'){
                        retract();
                        column += acc;
                        return Fail();
                    }

                    else if(digitOccurrence){
                        sb.append(c);
                        state = 5;
                        acc++;
                        continue;
                    }

                    else{
                        retract();
                        column += acc;
                        yyparser.yylval = new ParserVal((Object) sb.toString());
                        return Parser.NUM;
                    }

                //State 6: When processed an underscore in the first position of an identifier
                case 6:
                    if(digitOccurrence || lowerCaseOccurrence || upperCaseOccurrence){
                        sb.append(c);
                        state = 6;
                        acc++;
                        continue;
                    }

                    else{
                        retract();
                        column += acc;
                        yyparser.yylval = new ParserVal((Object)sb.toString());
                        return Parser.ID;
                    }

                //State 7: When processed a lowercase alphabet in first position
                case 7:
                    if(digitOccurrence || upperCaseOccurrence || c == '_'){
                        sb.append(c);
                        state = 6;
                        acc++;
                        continue;
                    }
                    //Since we are looking for non-numeric keywords.
                    else if(lowerCaseOccurrence){
                        sb.append(c);
                        state = 7;
                        acc++;
                        //Check if keyword encountered:
                        continue;
                    }

                    else{
                        retract();
                        column += acc;
                        yyparser.yylval = new ParserVal((Object)sb.toString());
                        if(Arrays.stream(keywords).anyMatch(sb.toString()::equals)){
                            int index = -1;
                            for (int i = 0; i < keywords.length; i++) {
                                if(keywords[i].equals(sb.toString())){
                                    index = i;
                                    break;
                                }
                            }

                            switch(index){
                                case -1:
                                    return Parser.ID;

                                case 0:
                                    return Parser.INT;

                                case 1:
                                    return Parser.PRINT;

                                case 2:
                                    return Parser.FUNCTION;

                                case 3:
                                    return Parser.IF;

                                case 4:
                                    return Parser.THEN;

                                case 5:
                                    return Parser.ELSE;

                                case 6:
                                    return Parser.BEGIN;

                                case 7:
                                    return Parser.END;

                                case 8:
                                    return Parser.WHILE;
                            }
                        }
                        else
                            return Parser.ID;
                    }

                case 9999:
                    return EOF;                                     // return end-of-file symbol
            }
        }
    }
}
