import java.util.*;

class SortMountains{

    LinkedList<Mountain> mtn = new LinkedList<Mountain>();

    public static void main(String[] args){
        new SortMountains().go();
    }

    private class CompareName implements Comparator<Mountain>{
        public int compare(Mountain first, Mountain second){
            return first.getName().compareTo(second.getName());
        }
    }

    private class CompareHeight implements Comparator<Mountain>{
        public int compare(Mountain first, Mountain second){
            return (first.getHeight() - second.getHeight());
        }
    }

    public void go(){
        mtn.add(new Mountain(7890 , "coco"));
        mtn.add(new Mountain(5687 , "blabla"));
        mtn.add(new Mountain(1234 , "bloblo"));
        System.out.println("as entered:\n"+ mtn);
        CompareName cn = new CompareName();
        Collections.sort(mtn, cn);
        System.out.println("by name:\n"+ mtn);
        CompareHeight ch = new CompareHeight();
        Collections.sort(mtn, ch);
        System.out.println("by height:\n"+ mtn);
    }
}