import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ReverseOrRotate {

    public static void main(String[] args) {
        Arrays.stream(new ReverseOrRotate().reverseOrRotate("123456")).forEach(System.out::println);
        // System.out.println(new ReverseOrRotate().reverseOrRotate("123456"));
    }

    private int[] reverseOrRotate(String sz){
        int[] nums = sz.chars().map(x -> x-48).toArray();
        System.arraycopy(nums, 0, nums, 1, nums.length  -1 );
        return nums;
    }
}