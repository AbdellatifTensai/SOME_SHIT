import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class FoldArray {

    public static void main(String[] args) {
        new FoldArray().fold(new int[] { 1, 2, 3, 4, 5, 6 }, 1);
    }

    private int[] fold(int[] array, int runs) {
        Arrays.stream(array).
        return null;
    }
    
    public static class Test implements IntStream{
        public static Stream<Integer> addone(){
            return this.findFirst().getAsInt() + 1;
        }
    }
}