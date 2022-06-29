import java.io.*;

class TestObject implements Serializable{

    private int width = 10;
    private int height = 20;

    public void setWidth(int a){
        width = a;
    }
    public void setHeight(int a){
        height = a;
    }

    public static void main(String[] args){
        TestObject test = new TestObject();
        test.setWidth(50);
        test.setHeight(100);
        try{
            FileOutputStream fs = new FileOutputStream("foo.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(test);
            os.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    
    }
}