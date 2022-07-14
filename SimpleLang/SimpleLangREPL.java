import java.util.List;
import java.util.Scanner;

class SimpleLangREPL {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);        

        while(true){
            System.out.print("> ");

            Lexer lexer = new Lexer(scanner.nextLine());
            List<Token> tokens = lexer.lexer();
            if(tokens.contains(Token.EXIT_TOKEN)){ scanner.close(); break; }
            tokens.forEach(System.out::print);

            Parser parser = new Parser(tokens);
            Node node = parser.parse();
            System.out.println("\n" + node);

            Interpreter compiler = new Interpreter(node);
            int result = compiler.interpret();
            System.out.println(result);
        }

        // while(true) System.out.println(new Interpreter(new Parser(new Lexer(scanner.nextLine()).lexer()).parse()).interpret());
        // of course a one liner, what do you expect from me :P
    }
}
