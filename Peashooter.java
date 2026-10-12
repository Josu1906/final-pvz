package pvz.logic.gameobjects;
import pvz.logic.Game;
import pvz.utils.Position;


public class Peashooter {
	
	public static final int cost = 50;
	private static final int damage = 1;
	private static final int endurance = 3;

	private int strength;
	private	Position position;
	private Game game;
	
	
	public Peashooter(int row, int col, Game gamee) {
		
		position = new Position(row, col);
		game = gamee;
		strength = endurance;
	}
	
	/*public void update() {
		int i = position.col();
		boolean done = false;
		//game.generateCoins(1); peashooter ataca, no genera coins
		while(i < game.NUM_COLS && !done) {
			if(game.hasZombie(new Position(position.row(), i))) {				
				game.attackZombie(new Position(position.row(), i), 1);
				done = true;
			}
			
			i++;
		}

	}*/
	
	public void update() {

		game.attackZombie(position, damage);
	}
	
	public static String getDescription() {
		// TODO Auto-generated method stub
		 String desciption= "[P]eashooter: cost= " + cost + 
				" sun coins, damage= "+ damage +
				", endurance= "+ endurance;
		 return desciption;
	}
	
	public void receiveDamage(int damage) {
		this.strength -= damage;
	}
	
	public boolean isAlive() {
		boolean ishe = true;
		if(this.strength < 1) ishe = false;
		
		return ishe;
	}
	
	public boolean isInPosition(Position position) {
		
		boolean isIn = false;
		
		if(position.row() == this.position.row() && position.col() == this.position.col()) {
			isIn = true;
		}
		
		
		return isIn;
		//return this.position.equals(position);
	}

	public String getIcon() {
		return "P[0" + strength + "]";
	}
	
	public static final String shortName() {
		return "p";
	}
	
	public static final String longName() {
		return"peashooter";
	}

}