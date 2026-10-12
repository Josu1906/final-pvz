package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.utils.Position;

public class Zombie {
  private int resistance=5;
	private int damage=1;
	private int cycle=1;
	
	private Position position;
	private Game game;

	public Zombie(Position position, Game game){
		this.position=position;
		this.game=game;
	}
	
	public String getIcon() {
		String ret = "Z[" + resistance + "]";
		return ret;
	}
	
	public boolean isInPosition(Position position) {
		return this.position.equals(position);
	}
	
	public boolean isHorizontallyAligned(Position position) {
		return this.position.isHorizontallyAligned(position);
	}
	
	public boolean isVerticallyAligned(Position position) {
		return this.position.isVerticallyAligned(position);
	}
	
	public void receiveAttack(int damage) {
		this.resistance-=damage;
	}
	
	public void update() {
		
		if(cycle % 2 == 0) {
			Position new_position = new Position(position.row(), position.col() - 1);
			if(game.isEmpty(position)) {
				this.position = new_position;
			}
			else {
				game.attackPlant(new_position, 1);
			}
		}
		
		cycle++;
	}
	
	public boolean isAlive() {
		return resistance>0;
	}
}