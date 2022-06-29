import java.util.Arrays;
import java.util.function.IntPredicate;

class Troll{

    public static void main(String[] args){
        System.out.println(new Troll().disvowel("This website is for losers LOL!"));
    }

    private String disvowel(String str){
        //char[] vowels = {'a','e','y','u','i','o','A','E','Y','U','I','O'};
        //String vowels = "aeyuioAEYUIO";
        IntPredicate filter = x -> x != 'a'&& x!='e'&& x!='y'&& x!='u'&& x!='i'&& x!='o'&& x!='A'&& x!='E'&& x!='Y'&& x!='U'&& x!='I'&& x!='O';
        return str.chars().filter(filter).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString(); 
    }
}