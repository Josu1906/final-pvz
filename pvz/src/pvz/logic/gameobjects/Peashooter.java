package pvz.logic.gameobjects;
import pvz.logic.Game;
import pvz.utils.Position;


public class Peashooter {
	
	private int cost = 50;
	private int strenght = 3;
	private int damage = 1;
	private	Position position;
	private Game game;
	
	public Peashooter(int row, int col, Game gamee) {
		
		position = new Position(row, col);
		game = gamee;
	}
	
	public void update() {
		int i = position.col();
		boolean done = false;
		game.generateCoins(1);
		while(i < game.NUM_COLS && !done) {
			if(game.hasZombie(new Position(position.row(), i))) {				
				game.attackZombie(new Position(position.row(), i), 1);
				done = true;
			}
			
			i++;
		}

	}
	
	
	public static Object getDescription() {
		// TODO Auto-generated method stub
		return "[P]eashooter: cost='50' suncoins, damage='1', endurance='3' \n";
	}
	
	public void receiveDamage(int damage) {
		this.damage -= damage;
	}
	
	public boolean isAlive() {
		boolean ishe = true;
		if(this.damage < 1) ishe = false;
		
		return ishe;
	}
	
	public boolean isInPosition(Position position) {
		
		boolean isIn = false;
		
		if(position.row() == this.position.row() && position.col() == this.position.col()) {
			isIn = true;
		}
		
		
		return isIn;
	}

	public String getIcon() {
		return "P[0" + strenght + "]";
	}

}