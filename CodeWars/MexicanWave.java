import java.util.Arrays;
import java.util.stream.Stream;

class MexicanWave {
    public static void main(String[] args) {
        // Arrays.stream(new MexicanWave().wave("blabla")).forEach(System.out::println);
        new MexicanWave().wave("blabla");
    }

    private void wave(String str) {
        char[] result = new char[str.length()];
        for (int x = 0; x < str.length(); x++) {
            result[x] = str.toUpperCase().charAt(x);
            System.out.println(result[x]);
        }
    }
}