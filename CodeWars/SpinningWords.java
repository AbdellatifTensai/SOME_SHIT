import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

class SpinningWords{

    public static void main(String[] args){
        new SpinningWords().spinning("Hello There My Name Is Something");
    }

    private void spinning(String string){
        String[] tokens = string.split(" ");
        Arrays.stream(tokens).filter(x -> x.length() >= 5).par;
    }
}