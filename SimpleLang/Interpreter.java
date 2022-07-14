public class Interpreter {

    Node node;

    public Interpreter(Node node) {
        this.node = node;
    }

    public int interpret(){
        return node.apply();
    }

}
