import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {
    
    enum State{
        PUSH,
        DUMP,
        PLUS,
        NOOP
    }

    static Stream<String> input; 
    
    static List<Pair<State,Integer>> program;
    
    static List<Integer> stack = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException{
        input = Files.lines(Paths.get(args[0]));
        program = lexer(input);
        
        program.forEach(x -> {
            switch (x.key) {
                case PUSH: stack.add(x.value); log("Pushed: ", x.value.toString()); break;
                case DUMP: log("Dumped: ",content(stack)); stack.clear(); break;
                case PLUS: sum(); log("Sum ",content(stack)); break;
                case NOOP: log("NOOP");break;
                default: log(content(stack)); break;
            }
        });
    }

    private static List<Pair<State,Integer>> lexer(Stream<String> in ){
        List list = new ArrayList<Pair<State,Integer>>();
        in.forEach(i-> Arrays.stream(i.split(" ")).forEach(x -> {
            log(x);
            switch (x) {
                case ".": list.add(pair(State.DUMP, 0)); break;
                case "+": list.add(pair(State.PLUS,0)); break;
                default: list.add(pair(State.PUSH, Integer.valueOf(x))); break;
            }
        }));
        return list;
    }

    private static Pair<State,Integer> pair(State op, int n){
        return Pair.pair(op, n);
    }
    private static void sum(){
        int dummy = stack.stream().mapToInt(Integer::intValue).sum();
        stack.clear();
        stack.add(dummy);
    }

    private static String content(List<Integer> list){
        return list.stream().map(String::valueOf).collect(Collectors.joining());
    }
    
    private static void log(String... x){
        Arrays.stream(x).forEach(System.out::print);System.out.print("\n");
    }

    public static class Pair<K,V>{
        private K key;
        private V value;

        public Pair(K key, V value){
            this.key = key;
            this.value = value;
        }
        public static <K,V>Pair<K,V> pair(K key, V value){
            return new Pair<>(key, value);
        } 
    }
}
