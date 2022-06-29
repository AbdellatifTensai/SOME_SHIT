class BouncingBall{
    int count;
    public static void main(String[] args) {
        System.out.println(new BouncingBall().bouncing(30.0D, 66D, 1.5D));
    }

    private int bouncing(double h, double bounce, double window){
        // h*bounce > window? {count++; bouncing(h*bounce, bounce, window);}: return count * 2 +1; 
        count =0;
        if (h*bounce > window) {
            bouncing(h*bounce, bounce, window);
            count++;
        } else if(h<=0 || bounce>=1 || bounce <=0 || window>= h){
            return -1;
        }
        return count * 2 +1;
        // return  ((h * bounce) / window) >= 1.0D ? ((int)((h * bounce) / window))*2 + 1 : 1;
    }
}