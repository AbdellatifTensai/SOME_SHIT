import java.util.Iterator;
import java.util.List;

public class Parser {

    Iterator<Token> tokens;
    Token currentToken;

    public Parser(List<Token> tokens){
        this.tokens = tokens.iterator();
        advance();
    }

    public Node parse(){
        return expr();
    }

    private Node factor(){
        if(isIntOrFloat(currentToken)){
            Token t = currentToken;
            advance();
            return new NodeNumber(t);
        }
        else if(is2PriorityOp(currentToken)){
            Token t = currentToken;
            advance();
            Node rightNode = factor();
            return new NodeUniOp(t, rightNode);
        }
        else if(currentToken.type.equals(InstType.TT_LPARN)){
            advance();
            Node node = expr();
            if(currentToken.type.equals(InstType.TT_RPARN)){
                advance();
                return node;
            }
        }
        return null;
    }

    private Node term(){
        Node leftNode = factor();
        while(is1PriorityOp(currentToken)){
            Token opToken = currentToken;
            advance();
            Node rightNode = factor();
            leftNode = new NodeBinOp(leftNode, opToken, rightNode);
        }
        return leftNode;
    }

    private Node expr(){
        Node leftNode = term();
        while(is2PriorityOp(currentToken)){
            Token opToken = currentToken;
            advance();
            Node rightNode = term();
            leftNode = new NodeBinOp(leftNode, opToken, rightNode);
        }
        return leftNode;
    }

    private void advance(){
        currentToken = tokens.hasNext()? tokens.next(): currentToken;
    }

    private boolean isIntOrFloat(Token token){
        return token.type.equals(InstType.TT_INT) || token.type.equals(InstType.TT_FLOAT); 
    }

    private boolean is1PriorityOp(Token token){
        return token.type.equals(InstType.TT_MUL) || token.type.equals(InstType.TT_DIV);
    }

    private boolean is2PriorityOp(Token token){
        return token.type.equals(InstType.TT_ADD) || token.type.equals(InstType.TT_SUB);
    }   
    
}
