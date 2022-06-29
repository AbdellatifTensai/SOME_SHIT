import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class SequencesAndSeries {

    long sum;

    public static void main(String[] args) {
        // IntStream.range(1, 100).forEach(x -> System.out.println(new
        // SequencesAndSeries().getScore(x)));
        System.out.println(new SequencesAndSeries().getScore(4));
    }

    // private long getScore(long n){
    // sum = 0;
    // return getScore(n, sum);
    // }

    private long getScore(long n) {
        // System.out.println(n + " " + sum);
        // sum += 50 *n;
        // return n==0? sum: getScore(n-1);
        // for (long x = n; x >= 0; x--) {
        //     System.out.println(x + " " +sum);
        //     sum += 50 * x;
        // }
        // return sum;
        return LongStream.range(0, n+1)
                    .peek(System.out::println)
                    .reduce((x,y) ->50*y).getAsLong();     
        //let's go baby it only took me 2 days...
    }

}
