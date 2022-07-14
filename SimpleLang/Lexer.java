import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Lexer{

    String input;
    
    public Lexer(String input){ 
        this.input = trimStringSpaces(input);
    }

    public List<Token> lexer(){
        if(input.equals("exit")) return Arrays.asList(Token.EXIT_TOKEN);
        

        List<Token> tmp = new ArrayList<>();

        for(int x=0; x<input.length(); x++){
            char ch = input.charAt(x);
            String ch_str = String.valueOf(ch);

            if(isOp(ch_str)){
                tmp.add(makeOpToken(ch_str));
            }
            else if(isIntOrDot(ch)){
                Token temp = makeIntToken(ch_str, input, x);
                tmp.add(temp);
                x += temp.value.length()-1;
            }
            else{
                tmp.add(Token.NOOP_TOKEN);
            }
        }
        return tmp;
    } 

    private String trimStringSpaces(String str){
        return str.chars().filter(x->x!=' ').mapToObj(x-> String.valueOf((char)x)).collect(Collectors.joining());
    }

    private boolean isIntOrDot(char ch){ return (ch>='0' && ch<='9') || ch == '.';}

    private Token makeIntToken(String ch_str, String str, int index){
        String buffer = ch_str; 
        boolean isDot = false;
        while(++index < str.length() && isIntOrDot(str.charAt(index))){
            if(str.charAt(index) == '.')
                isDot = true;
            buffer += str.charAt(index); 
        }
            
        return isDot? new Token(InstType.TT_FLOAT, buffer): new Token(InstType.TT_INT, buffer);
    }

    private boolean isOp(String ch){
        return Arrays.stream(Token.opInsts())
                    .map(str -> str.split("=")[0])
                    .anyMatch(op -> ch.equals(op));
    }

    private Token makeOpToken(String ch){
        return Arrays.stream(Token.opInsts())
                    .map(str -> str.split("="))
                    .filter(arr -> arr[0].equals(ch))
                    .map(arr -> new Token(InstType.valueOf(arr[1]), arr[0]))
                    .findFirst()
                    .orElse(new Token(InstType.TT_NOOP));
    }

}