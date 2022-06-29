import java.util.Arrays;

class TwoSum {

    int[] answer = new int[2];
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        Arrays.stream(new TwoSum().twoSum(arr, 4)).forEach(System.out::println);;
    }

    private int[] twoSum(int[] numbers, int target) {
        for (int x = 0; x < numbers.length; x++) {
            for (int y = 1; y < numbers.length; y++) {
                if(numbers[x] + numbers[y] == target){
                    return new int[]{x,y};
                }
            }
        }
        return null;
    }
}