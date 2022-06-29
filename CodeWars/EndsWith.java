class EndsWith{

    public static void main(String[] args){
        String test = "hello";
        String o = "o";
        System.out.println(new EndsWith().string_ends_with(test, o));
    }
    
    private boolean string_ends_with(String str, String ch) {
        return str.endsWith(ch);       
    }
}