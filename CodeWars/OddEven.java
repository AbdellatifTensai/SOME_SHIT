import java.util.Arrays;

class OddEven{

    public static void main(String[] args){
        int[] numm = {1,2,4,5,6,7,5};
        System.out.println(new OddEven().msg(numm));
    }
    
    private String msg(int[] nums){
        return Arrays.stream(nums).sum() % 2 ==0? "Even": "Odd";
    }
}