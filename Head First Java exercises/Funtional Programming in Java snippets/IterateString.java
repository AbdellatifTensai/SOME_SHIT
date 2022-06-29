class IterateString{

    public static void main(String[] args){
        new IterateString().go();
    }
    private static void printChar(int aChar){
        System.out.println((char) aChar);
    }
    private void go(){
        final String str = "hello";
        str.chars().forEach(IterateString::printChar);
    }
}