class Mountain{
    private int height;
    private String name;

    public Mountain(int a,String s){
        height = a;
        name = s;
    }
    public String getName(){
        return name;
    }
    public int getHeight(){
        return height;
    }
    public String toString(){
        return name +" "+ height;
    }
}