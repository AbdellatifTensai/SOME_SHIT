import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class TestSimplexer{
    public static void main(String[] args) {
        Simplexer s = new Simplexer("s13f5g\"bla\"g+d-returns  \ng   if else");
        Simplexer v = new Simplexer("for");
        s.forEach(System.out::println);
        v.forEach(System.out::println);
    }
}

class Simplexer implements Iterator<Token>, Iterable<Token>{

    List<Token> tokens = new ArrayList<>();
    final List<String> KEYWORDS = Arrays.asList("if","return", "else", "break", "func", "for", "while");
    final List<String> BOOLEANS = Arrays.asList("true", "false");
    int index = 0;
    
    Simplexer(String buffer) {
        if(buffer != null) lexer(buffer);
    }

    private void lexer(String buffer){
        for(int x=0;x<buffer.length();++x){
            StringBuilder sb = new StringBuilder();
            char ch = buffer.charAt(x);
            String word; 

            if(isNum(ch)){
                sb.appendCodePoint(ch);
                while(++x < buffer.length() && isNum(buffer.charAt(x))) sb.appendCodePoint(buffer.charAt(x));
                tokens.add(new Token(sb.toString(), "integer"));
                x--;
            }
            else if((word = keyword(BOOLEANS, ch, buffer, x)) != ""){
                tokens.add(new Token(word, "boolean"));
                x += word.length()-1;
            }
            else if((word = keyword(KEYWORDS,ch, buffer, x)) != ""){
                tokens.add(new Token(word, "keyword"));
                x += word.length()-1;
            }            
            else if(ch == '"'){
                sb.append(ch);
                while(++x < buffer.length() && buffer.charAt(x) != '"') sb.append(buffer.charAt(x));
                tokens.add(new Token(sb.append('"').toString(), "string"));
            }
            else if(isOperator(ch)){
                tokens.add(new Token(String.valueOf(ch), "operator"));
            }
            else if(isWhitespace(ch)){
                sb.append(ch);
                while(++x < buffer.length() && isWhitespace(buffer.charAt(x))) sb.append(buffer.charAt(x));
                tokens.add(new Token(sb.toString(), "whitespace"));
                x--;
            }
            else{
                tokens.add(new Token(String.valueOf((char) ch), "identifier"));
            }
        }
    }

    private boolean isNum(char ch) { return (ch >= '0' && ch<='9'); }

    private boolean isWhitespace(char ch) { return (ch >= 1 && ch<= 40); }

    private boolean isOperator(char ch) { return ch == '+'||ch == '-'||ch == '*'||ch == '/'||ch == '%'||ch == '('||ch == ')'||ch == '='; }

    private String keyword(List<String> list,char ch, String source, int index){
        for(String word : list) if(ch == word.charAt(0)) if(checkUpcomingWord(source, word, index)) return word;
        return "";
    }

    private boolean checkUpcomingWord(String source, String word, int index){
        boolean isTrue;
        try{ isTrue = source.substring(index, index + word.length()).equals(word); }
        catch(Exception e){ isTrue = false; }
        return isTrue;
    }

    @Override
    public boolean hasNext() {
        if(tokens.isEmpty()) return false;
        return index < tokens.size();
    }

    @Override
    public Token next() {
        Token t;
        try{ t = tokens.get(index); }
        catch(IndexOutOfBoundsException e){ throw new NoSuchElementException(); }
        ++index;
        return t;
    }

    @Override
    public Iterator<Token> iterator() { return this; }
    
}

class Token{
    String value;
    String type;
    Token(String value, String type){this.value = value; this.type = type;}
    @Override
    public String toString(){ return "Token: " + value + ", " + type; }
}  