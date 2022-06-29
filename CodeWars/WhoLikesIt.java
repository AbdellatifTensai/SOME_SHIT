import java.util.Arrays;

class WhoLikesIt{

    public static void main(String[] args){
        System.out.println(new WhoLikesIt().whoLikes("Peter"));
    }

    private String whoLikes(String... names){
        Arrays.stream(names).forEach(System.out::println);
        return names.length == 0? "no one like this"
            : names.length == 1? names[0] + " likes this"
            : names.length == 2? names[0] + " and " + names[1] + "like this"
            : names.length == 3? names[0] + ", " + names[1] + " and " + names[2] + "like this"
            : names.length >= 4? String.format("%s, %s and %d others like this", names[0] , names[1] , names.length - 2)
            : "";
            // it works i don't care
    }
}