public class NodeNumber extends Node{

    public NodeNumber(Token numToken){
        if(!numToken.type.equals(InstType.TT_INT)) throw new IllegalAccessError("NodeNumber can't have a non number token!");
        this.token = numToken;
    }

    @Override
    public String toString() {
        return token.value;
    }

    @Override
    public int apply() {
        return Integer.valueOf(this.token.value);
    }
    
}
