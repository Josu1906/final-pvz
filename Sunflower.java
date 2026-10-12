package pvz.logic.gameobjects;
import pvz.logic.Game;
import pvz.utils.Position;


public class Sunflower {
	
	public static final int cost = 20;
	private static final int damage = 0;
	private static final int endurance = 1;
	
	private int strenght;
	private	Position position;
	private Game game;
	private int cyclesAlive;
	
	public Sunflower(int row, int col, Game gamee) {
		
		this.position = new Position(row, col);
		game = gamee;
		this.strenght = endurance;
		this.cyclesAlive=0;
	}
	
	public void update() {
		cyclesAlive++;
		if((cyclesAlive % 3) == 0) {			
			game.generateCoins(10);
		}
	}
	
	public static String getDescription() {
		 String desciption= "[S]unflower: cost= " + cost + 
					" sun coins, damage= "+ damage +
					", endurance= "+ endurance;
		return desciption;
	}
	
	public void receiveDamage(int damage) {
		this.strenght -= damage;
	}
	
	public boolean isAlive() {
		boolean ishe = true;
		if(this.strenght < 1) ishe = false;
		
		return ishe;
	}
	
	public boolean isInPosition(Position position) {
		
//		boolean isIn = false;
//		
//		if(position.row() == this.position.row() && position.col() == this.position.col()) {
//			isIn = true;
//		}
//		
//		
//		return isIn;
		return this.position.equals(position);
	}

	public String getIcon() {
		return "S[0" + strenght + "]";
	}
	
	public static final String shortName() {
		return "s";
	}
	
	public static final String longName() {
		return"sunflower";
	}

}
