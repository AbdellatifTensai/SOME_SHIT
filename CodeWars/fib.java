class fib {
    
    public static void main(String[] args) {
        System.out.println(
            new fib().fibi(5,0,1)
        );
    }

    int fibi(int i, int a, int b){
        return i>=0? fibi(i-1,b,a+b) :b; 
    }
}
