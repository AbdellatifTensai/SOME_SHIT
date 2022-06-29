import java.util.Arrays;

class TortoiseRacing {

    public static void main(String[] args) {
        Arrays.stream(new TortoiseRacing().racing(498 , 558 , 63)).forEach(System.out::println);
    }

    private int[] racing(int v1, int v2, int d) {
        if(v1>=v2 || d<0){return null;}
        int hrs = (int)(d / (float)(v2 - v1));
        int min = (int)(((d / (float)(v2 - v1)) - hrs) * 60);
        int sec = (int)(((((d / (float)(v2 - v1)) - hrs) * 60) - min) * 60);
        return new int[] { hrs, min, sec };
    }

    private int round(float a){
        return a - (int) a >= 0.9F ? (int) (a+1): (int)a;
    }
}