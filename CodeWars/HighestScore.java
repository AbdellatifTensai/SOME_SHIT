import java.util.stream.Stream;

class HighestScore {

    public static void main(String[] args) {
        System.out.println(new HighestScore().score("man i need a taxi up to ubud"));
    }

    private String score(String s) {
        return Stream.of(s.split(" "))
                .reduce((x, y) -> x.chars().map(i -> i - 96).sum() > y.chars().map(i -> i - 96)
                .sum() ? x : y)
                .get();
    }
}