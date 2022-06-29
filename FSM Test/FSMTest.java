import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FSMTest{

    public static void main(String[] args)  {
        String filter = "...";
        String input = "abc";
        int[][] fsm = generateFSM(filter);
        printFSM(fsm);

        System.out.println(
            matchInput(input, fsm)
        );
    }

    private static int[][] generateFSM(String filter){
        int[] filters = filter.chars().toArray();
        int[][] grid = new int[128][filters.length]; 

        for(int row=0;row<128;row++){
            for(int column=0;column<filters.length;column++){
                if(row == filters[column]){
                    grid[row][column] = column+1;
                }
                else if(filters[column] == '.' && row>='!' && row<='~'){
                    grid[row][column] = column +1;
                }
                else if(filters[column] == '*'){
                    //panic!
                }
                else{
                    grid[row][column] = 0;
                }
            }
        }
        return grid;
   }
   
   //functional and OOP... ewww
    private static int[][] generateFSM2(String filter){
        ArrayList<int[]> nums = new ArrayList<>();
        List<IOperation> operations = Arrays.asList(new CommonOperation(), new DotOperation());
        //i guess you can just add new operations and not touch the main class again, but still..
        IntStream.range(0, 128)
                .mapToObj(x -> new int[filter.length()])
                .collect(Collectors.toCollection(() -> nums))
                .forEach(row ->{
                    for(int column=0;column<row.length;column++)
                        for(IOperation op: operations) // haven't use for:each in a while lol
                            op.apply(row, nums.indexOf(row),column, (int)filter.charAt(column));
                            // forEach needs the values inside the lambda thing to be final :< 
                });
        return nums.toArray(new int[128][]);
   }

    // why the hell am'i doing this? why not just use a hashmap 
    // or better just use the other function with the array and stick to elseif elseif just like a real man
    // or maybe remove OOP from existence and keep procedual programming
    // or how about migrating from this fucked up language that's making me question my existence. RUST IS COMING..

    private static boolean matchInput(String input, int[][] fsm){
        int state = 0;
        int[] inputs = input.chars().toArray();

        for(int column=0;column<inputs.length;column++){
            System.out.printf("%d : %d %d\n",column,inputs[column],fsm[inputs[column]][state]);
            state = fsm[inputs[column]][state];
            if(state == 0) break;
        }
        return state == inputs.length;
    }

    private static void printFSM(int[][] fsm){
        for(int row=0;row<fsm.length;row++){
            System.out.print((char)row+" :");
            for(int column=0;column<fsm[0].length;column++){
                System.out.print(" "+fsm[row][column]);
            }
            System.out.println();
        }
    }
    
}

@FunctionalInterface
interface IOperation{
    public void apply(int[] row, int indexOfrow, int column,int indexOfColumn);
}

class CommonOperation implements IOperation{
    @Override
    public void apply(int[] row, int indexOfrow, int column,int indexOfColumn){
        if(indexOfrow == indexOfColumn) row[column] = column +1;
    }
}

class DotOperation implements IOperation{
    @Override
    public void apply(int[] row, int indexOfrow, int column,int indexOfColumn){
        if(indexOfColumn == '.') row[column] = column+1;   
        
    }
}


