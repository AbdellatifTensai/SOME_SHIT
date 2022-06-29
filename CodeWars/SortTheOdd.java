import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SortTheOdd {

    public static void main(String[] args) {
        int[] numms = { 5, 8, 6, 3, 4 };
        new SortTheOdd().sort(numms);
    }

    private void sort(int[] nums) {
        ArrayList<Integer> odds = Arrays.stream(nums).filter(x -> x%2!=0).sorted().boxed().collect(Collectors.toList());
        Arrays.stream(nums).flatMap(x -> x%2!=0? odds.iterator().next(): x).boxed().collect(Collectors.toList()).forEach(System.out::println);
    }
}