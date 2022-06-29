import java.util.*;

class DotComBust{
	
	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
	private int numOfGuesses;
	
	private void setUpGame(){
		DotCom go2 = new DotCom();
		DotCom eToys = new DotCom();
		DotCom pets = new DotCom();
		
		go2.setName("go2.com");
		eToys.setName("eToys.com");
		pets.setName("pets.com");
		
		System.out.println("game started i guess");
		
		for(DotCom set : dotComsList){
			ArrayList<String> newLocation = helper.placeDotCom(3);
			set.setLocationCells(newLocation);
		}
		
		if(!dotComsList.isEmpty()){
			System.out.println("everything is fine");
		}else{
			System.out.println("everything is not fine");
		}
		
		
	}
	
	private void startPlaying(){
		while(!dotComsList.isEmpty()){
			String userGuess = helper.getUserInput("Enter a guess");
			checkUserGuess(userGuess);
		}
		
		finishGame();
	}
	
	private void checkUserGuess(String userGuess){
		numOfGuesses++;
		String result = "miss";
		for(DotCom test : dotComsList){
			result = test.checkYourself(userGuess);
			if(result.equals("hit")){
				break;
			}
			if(result.equals("kill")){
				dotComsList.remove(test);
				break;
			}
		}
		System.out.println(result);
	}
	
	private void finishGame(){
		System.out.println("Win! it took you " + numOfGuesses);
	}
	
	public static void main(String[] args){
		DotComBust game = new DotComBust();
		game.setUpGame();
		game.startPlaying();
	}
}