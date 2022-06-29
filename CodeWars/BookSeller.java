import java.util.ArrayList;
import java.util.stream.Collectors;

class BookSeller {

    ArrayList<String> pairs;
    int sum;
    public static void main(String[] args) {
        String[] LL = { "ABART 20", "BKWRK 25", "CDXEF 50", "BTSQZ 89","ASFGB 0", "DRTYM 60" };
        String[] MM = { "A", "B", "C", "W" };
        System.out.println(new BookSeller().books(LL, MM));
    }

    private String books(String[] L, String[] M) {
        pairs = new ArrayList<String>();
        if(L.length ==0 || M.length == 0) return "";
        for (String m : M) {
            for (String l : L) if (l.startsWith(m)) sum += Integer.parseInt(l.split(" ")[1]);
            pairs.add("(" + m + " : " + sum + ")");
            System.out.println(m + sum);
            sum = 0;
        }
        return pairs.stream().collect(Collectors.joining(" - "));
    }

}