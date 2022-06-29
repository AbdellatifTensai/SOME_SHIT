import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class CreditCardNumber {

    public static void main(String[] args) {
        System.out.println(new CreditCardNumber().validate("2121"));
    }

    private boolean validate(String n) {
        return Stream.of(
                IntStream.range(0, n.length()).filter(x -> n.length() % 2 == 0 ? x % 2 != 0 : x % 2 == 0).mapToObj(
                        new ArrayList<Integer>(n.chars().boxed().collect(Collectors.toCollection(ArrayList::new)))::get)
                        .map(x -> x - '0'),
                IntStream.range(0, n.length()).filter(x -> n.length() % 2 == 0 ? x % 2 == 0 : x % 2 != 0).mapToObj(
                        new ArrayList<Integer>(n.chars().boxed().collect(Collectors.toCollection(ArrayList::new)))::get)
                        .map(x -> (x - '0') * 2 > 9 ? ((x - '0') * 2) - 9 : (x - '0') * 2))
                .flatMap(x -> x).reduce(Integer::sum).map(x -> x % 10).get() == 0 ? true : false;
        // one line, very proud
    }
}