public class Program {
	public static void main(String[] args) throws Exception
    {
        //  java.io.Reader r = new java.io.StringReader
        //  ("function main()::int\n"
        //  +"begin\n"
        //  +"    a := 1;\n"
        //  +"    a := a + 2;\n"
        //  +"    b := 3 + a;\n"
        //  +"    c := a + b;\n"
        //  +"    3.14 [ ( <= it if ; %// nope\n"
        //  +"(*\n"
        //  +"    3.14 [ ( <= it if ; % nope\n"
        //  +"    *)print c;\n"
        //  +"end\n"
        //  );

        //  args = new String[] { "D:\\cmpsc470\\proj2-minc-lexer-startup\\test\\test1.minc" };
        if(args.length <= 0)
            return;

        java.io.Reader r = new java.io.FileReader(args[0]);
        Parser p = new Parser(r);
        p.yyparse();
	}
}