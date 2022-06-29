import java.lang.*;

public class Player{
	int number = 0;
	boolean isCorrect = false;
	
	public void guess(){
		number = (int)(Math.random() * 10);
	}
}