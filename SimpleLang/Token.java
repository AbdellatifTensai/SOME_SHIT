public class Token {

    InstType type;        
    String value;

    public static final Token NOOP_TOKEN = new Token(InstType.TT_NOOP);
    public static final Token EXIT_TOKEN = new Token(InstType.TT_EXIT);
    
    public Token(InstType type, String value) { this.type = type; this.value = value; }
    public Token(InstType type){ this.type = type; this.value = "";}

    public static String[] opInsts(){
        return new String[]{"(=TT_LPARN" ,
                            ")=TT_RPARN" ,
                            "+=TT_ADD",
                            "-=TT_SUB",
                            "*=TT_MUL",
                            "/=TT_DIV"};
    }

    @Override
    public String toString() { return "[" + type + "," + (value.isEmpty()?"no value":value) + "]"; }

}
