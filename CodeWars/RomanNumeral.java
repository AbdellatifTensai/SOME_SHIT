import java.util.Comparator;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

class RomanNumeral {

    static final Pair[] romans = {new Pair("M", 1000)
                                        ,new Pair("CM", 900)
                                        ,new Pair("D", 500)
                                        ,new Pair("CD", 400)
                                        ,new Pair("C", 100)
                                        ,new Pair("XC", 90)
                                        ,new Pair("L", 50)
                                        ,new Pair("XL", 40)
                                        ,new Pair("X", 10)
                                        ,new Pair("IX", 9)
                                        ,new Pair("V", 5)
                                        ,new Pair("IV", 4)
                                        ,new Pair("I", 1)};

    public static void main(String[] args) {
        RomanNumeral test = new RomanNumeral();
        System.out.println(test.fromRomans("MDCLXVI"));
        System.out.println(test.fromRomans("XLIV"));
        System.out.println(test.toRoman(1666));
    }

    private String toRoman(int n) {
        // String str = new StringBuilder().append(n).toString();
        // str.chars()
        //     //  .map(x -> x-'0')
        //     //  .map(x -> (int) Math.pow(10, str.indexOf(x)) * (x - '0'))
        //     //  .mapToObj(x -> repeat(str.indexOf(x)+1, encodeRomans(x - '0')))
        //     .mapToObj(x -> encode(x - '0'))
        //     .forEach(System.out::println);
        return encode(n);
    }

    private String repeat(int n, char c){
        return new String(new char[n]).replace('\0', c);
    }

    private int fromRomans(String romanNumeral) {
        int sum = 0;
        char[] chars = romanNumeral.toCharArray();
        for (int i = 0; i < romanNumeral.length(); i++) {
            decodeRomans(chars[i+1] < decodeRomans(chars[i])
            ? sum = decodeRomans(chars[i+1]) + decodeRomans(chars[i])
            : sum = decodeRomans(chars[i+1]) - decodeRomans(chars[i]);
        }

        return romanNumeral.chars()
                            .map(x -> decodeRomans(x))
                            .reduce((x, y) -> y <= x ? x + y : y - x)
                            .getAsInt();

    }

    private int decodeRomans(int c) {
        switch (c) {
        case 73: return 1;
        case 86: return 5;
        case 88: return 10;
        case 76: return 50;
        case 67: return 100;
        case 68: return 500;
        case 77: return 1000;
        default: return 0;
        }
    }

    private char encodeRomans(int i) {
        switch (i) {
            case 1: return 'I';
            // case 4: return 'IV';
            case 5: return 'V';
            // case 9: return 'IX';
            case 10: return 'X';
            // case 40: return 'XL';
            case 50: return 'L';
            // case 90: return 'XC';
            case 100: return 'C';
            // case 400: return 'CD';
            case 500: return 'D';
            // case 900: return 'XM';
            case 1000: return 'M';
            default: return ' ';
        }
    }

    private String encode(int i){
        String ans = "";
        while(i>0){
            for(Pair<String, Integer> x: romans){
                if(x.getValue() <= i){
                    ans += x.getKey();
                    i -= x.getValue();
                    break;
                }
            }
        }
        return ans;
    }

    private static class Pair<String,Integer>{
        private String key;
        private Integer value;

        private Pair(String key, Integer value){
            this.key = key;
            this.value = value;
        }
        public String getKey(){
            return this.key;
        }
        public Integer getValue(){
            return this.value;
        }
    }
}