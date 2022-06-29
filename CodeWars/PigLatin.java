import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class PigLatin {

    public static void main(String[] args) {
        new PigLatin().pigIt("blabla bloblo");
    }

    private void pigIt(String str) {
        Stream.of(str.split(" ")).map(x -> {
            char[] c = x.toCharArray();
            for (int i = 0; i < x.length()-1; i++) {
                c[i] = c[i+1 % x.length()];
                System.out.println(c[i] + " "+ x.length() + " " + i + " " +(i+1 % x.length()));
            }
            return c;})
            .forEach(System.out::println);
    }
}