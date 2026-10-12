package pvz.logic;

import java.util.Random;

import pvz.control.Level;
import pvz.logic.gameobjects.Zombie;
import pvz.logic.gameobjects.ZombieList;
import pvz.utils.Position;

/**
 * Manages the full lifecycle of zombies for a game session.
 *
 * <p>Responsibilities: deciding each cycle whether to spawn a new zombie
 * (probabilistically, subject to the remaining quota from {@link Level}),
 * delegating per-cycle updates and dead-removal to the underlying
 * {@link ZombieList}, and answering win/loss queries
 *
 */
public class ZombiesManager {

	private Game game;

	private Level level;

	private Random rand;

	private int remainingZombies;

	private ZombieList zombies;

	public ZombiesManager(Game game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombies = new ZombieList();
	}

	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 * 
	 * @return <code>true</code> if a zombie should be added to the game.
	 */
	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();
	}
	
	/**
	 * Return a random row within the board limits.
	 * 
	 * @return a random row.
	 */
	private int randomZombieRow() {
		return rand.nextInt(Game.NUM_ROWS);
	}
	
	public boolean addZombie() {
		int row = randomZombieRow();
		return addZombie(row);
	}

	public boolean addZombie(int row) {
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie()
				&& isPositionEmpty(Game.NUM_COLS, row);

		if(canAdd) {
			// TODO fill your code
			Position p= game.newZombiePosition(row); //nose si es NUM_COLS-1 o dejarlo asi
			zombies.add(new Zombie(p,game));
			remainingZombies--;
		}
		return canAdd;
	}

	private boolean isPositionEmpty(int numCols, int row) {
		// TODO Auto-generated method stub
		Position p=new Position(row, numCols);
		return game.isEmpty(p);
	}

	public int getRemainingZombies() {
		// TODO Auto-generated method stub
		return remainingZombies;
	}

	// TODO fill your code
	
	public boolean doZombiesReachedTheHouse() {
		return zombies.anyInColumn(-1); 
	}
	
	public void damageZombie(Position p, int damage) {
		zombies.damage(p,damage);
	}
	
	public String iconInPosition(Position p) {
		return zombies.iconInPosition(p);
	}
	
	public boolean isEmpty(Position p) {
		return zombies.isEmpty(p);
	}
	
	public void update() {
		removeDead();
		zombies.update();
		addZombie();
	}
	
	public boolean allZombiesWereKilled() {
		return (getRemainingZombies()==0)&&(zombies.size()==0);
	}
	
	public void removeDead() {
		zombies.removeDead();
	}
}