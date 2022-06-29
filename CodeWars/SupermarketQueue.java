import java.util.Arrays;
import java.util.stream.IntStream;

class SupermarketQueue {

    public static void main(String[] args) {
        int[] arr = { 10, 2, 3 };
        System.out.println("this is the answer: " + queueTime(arr, 2));
    }

    private static int queueTime(int[] costumers, int till) {
        int[] tills = new int[till];
        for (int x = 0; x < costumers.length; x++) {
            //stupid, but it works...
            int index = IntStream.range(0, tills.length).filter(i -> Arrays.stream(tills).min().getAsInt() == tills[i])
                    .findFirst().getAsInt();
            tills[index] += costumers[x];
            Arrays.stream(tills).forEach(i -> System.out.println(i));
        }
        return Arrays.stream(tills).max().getAsInt();
    }
}