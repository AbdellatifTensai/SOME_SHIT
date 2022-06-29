import java.lang.*;

public class GuessGame{
	Player p1 = new Player();
	Player p2 = new Player();
	Player p3 = new Player();
	String msg = "";
	public void startGame(){
		int guessp1 = 0;
		int guessp2 = 0;
		int guessp3 = 0;
		int targetNumber = (int)(Math.random() * 10);
		System.out.println("target number is: " + targetNumber);
		while(true){
			p1.guess();
			p2.guess();
			p3.guess();
			guessp1 = p1.number;
			guessp2 = p2.number;
			guessp3 = p3.number;
			System.out.println("player one guessed: " + guessp1);
			System.out.println("player two guessed: " + guessp2);
			System.out.println("player three guessed: " + guessp3);
			if(guessp1 == targetNumber){
				p1.isCorrect = true;
			    msg = "p1 is the winner!";
			}
			if(guessp2 == targetNumber){
				p2.isCorrect = true;
				msg = "p2 is the winner!";
			}
			if(guessp3 == targetNumber){
				p3.isCorrect = true;
				msg = "p3 is the winner!";
			}
			
			if(p1.isCorrect == true || p2.isCorrect == true || p3.isCorrect == true ){
				System.out.println(msg);
				break;
			}
			else{
				System.out.println("try again!");
			}
		}
	}
		
}