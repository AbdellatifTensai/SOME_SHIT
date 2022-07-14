public class NodeUniOp extends Node{

    public NodeUniOp(Token opToken, Node rightNode) {
        this.token = opToken;
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "(" + token.value + "," + rightNode + ")";
    }

    @Override
    public int apply() {
        int right = this.rightNode.apply();
        int output = 0;
        switch(this.token.type){
            case TT_SUB: output = - right; break;
            case TT_ADD: output = right;   break;
            default: throw new RuntimeException("something is wrong here!");
        }
        return output;
    }

}