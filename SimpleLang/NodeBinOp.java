public class NodeBinOp extends Node{

    public NodeBinOp(Node leftNode, Token opToken, Node rightNode) {
        this.leftNode = leftNode;
        this.token = opToken;
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "("+leftNode + " " + token.value + " " + rightNode + ")";
    }

    @Override
    public int apply() {
        int left = this.leftNode.apply();
        int right = this.rightNode.apply();
        int output = 0;
        switch(this.token.type){
            case TT_ADD: output = left + right; break; 
            case TT_MUL: output = left * right; break; 
            case TT_DIV: output = left / right; break; 
            case TT_SUB: output = left - right; break; 
            default: break;
        }
        return output;
    }

}
