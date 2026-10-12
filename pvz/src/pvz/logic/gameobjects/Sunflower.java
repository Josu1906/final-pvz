package pvz.logic.gameobjects;
import pvz.logic.Game;
import pvz.utils.Position;


public class Sunflower {
	
	private int cost = 20;
	private int strenght = 1;
	private int damage = 0;
	private	Position position;
	private Game game;
	private int cyclesAlive = 0;
	
	public Sunflower(int row, int col, Game gamee) {
		
		position = new Position(row, col);
		game = gamee;
	}
	
	public void update() {
		
		if((cyclesAlive % 3) == 0) {			
			game.generateCoins(10);
		}
		
		cyclesAlive++;
	}
	
	

	public static Object getDescription() {
		// TODO Auto-generated method stub
		return null;
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
		
		boolean isIn = false;
		
		if(position.row() == this.position.row() && position.col() == this.position.col()) {
			isIn = true;
		}
		
		
		return isIn;
	}
	
	public String getDescripcion() {
		return "[S]unflower: cost='20' suncoins, damage='0', endurance='1'";
	}

	public String getIcon() {
		return "S[0" + strenght + "]";
	}

}