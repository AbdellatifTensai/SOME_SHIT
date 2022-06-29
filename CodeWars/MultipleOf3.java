class MultipleOf3 {

    public static void main(String[] args) {
        System.out.println(new MultipleOf3().multipleOf(10));
    }

    private int multipleOf(int num) {
        int sum = 0;
        for (int x = num-1; x >= 0; x--) {
            if (x % 3 == 0 || x % 5 == 0) {
                sum += x;
            }
        }
        return sum;

        // return IntStream.range(0, num).filter(x -> x%3==0 || x%5==0).sum();
    }
}