import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

class Dichotomie {

    static StringBuilder sb = new StringBuilder();
    static PrintWriter pw;
    static Function<Double, Double> fn;
    static double[] intervalle1 = { 1F/Math.E, 1F/2F };
    double[] new_intervalle = new double[2];

    public static void main(String[] args) throws IOException {
        pw = new PrintWriter("dichotomie.csv");
        fn = x -> x- Math.pow(Math.log(x), 2);
   
        Arrays.stream(new Dichotomie().dichotomie(intervalle1, 1F / 0xFFFFFF, fn)).forEach(System.out::println);
        pw.write("centre,image de centre,intervalle1,intervalle2,amplitude\n");
        pw.write(sb.toString());
        pw.close();
    }

    private double[] dichotomie(double[] intervalle, double amp, Function<Double, Double> function) {
        double center = (intervalle[0] + intervalle[1]) / 2;
        double i0_image = function.apply(intervalle[0]);
        double i1_image = function.apply(intervalle[1]);
        double center_image = function.apply(center);

        if ((intervalle[1] - intervalle[0]) > amp) {
            if (i0_image * center_image < 0) {

                new_intervalle[0] = intervalle[0];
                new_intervalle[1] = center;

            } else if (i1_image * center_image < 0) {

                new_intervalle[0] = center;
                new_intervalle[1] = intervalle[1];

            }
            sb.append(
                    center + "," + center_image + "," + new_intervalle[0] + "," + new_intervalle[1] + "," + (intervalle[1] - intervalle[0]) + "\n");
            dichotomie(new_intervalle, amp, function);
        }
        return new_intervalle;
    }
}