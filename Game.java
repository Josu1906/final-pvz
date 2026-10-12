package pvz.logic;
import java.util.Random;
import pvz.control.Level;
import pvz.utils.Position;
import pvz.view.Messages;
import pvz.logic.gameobjects.*;

public class Game {
	
	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;
	public static final int INITIAL_COINS=50;
	
	private SunflowerList sunflowers;
	private PeashooterList peashooters;
	private ZombiesManager zombies;
	private int cycles=0;
	private int coins= INITIAL_COINS;
	private Level level;
	private long seed;
	private Random rand;
	private boolean isThePlayerOut = false;

	public Game(long seed, Level level) {
		// TODO Auto-generated constructor stub
		rand = new Random(seed);
		this.level = level;
		zombies = new ZombiesManager(this, level, rand);
		sunflowers = new SunflowerList(this);
		peashooters= new PeashooterList(this);
	}

	public String positionToString(Position position) {
		// TODO Auto-generated method stub
		String ret = "";
		if(!zombies.isEmpty(position)) {
			ret = zombies.iconInPosition(position);
		}
		else if(!sunflowers.isEmpty(position)){
			ret = sunflowers.iconInPosition(position);
		}
		else if(!peashooters.isEmpty(position)) {
			ret = peashooters.iconInPosition(position);
		}
		return ret;
	}
	
	/*public boolean checkGameObject(String[] str) {
		
		boolean ret = false;
		String[] analysis_str = str;
		
		String command_string = analysis_str[0];
		if(analysis_str.length == 4) {
			if((command_string.equalsIgnoreCase("add") || command_string.equalsIgnoreCase("a")) && 
					(analysis_str[1].equalsIgnoreCase("peashooter") || 
					analysis_str[1].equalsIgnoreCase("p") || 
					analysis_str[1].equalsIgnoreCase("s") || 
					analysis_str[1].equalsIgnoreCase("sunflower") ) && 
					isInsideBoard( new Position(Integer.parseInt(analysis_str[3]), Integer.parseInt(analysis_str[2])))) {
				ret = true;
			}			
		}
		
		else {
			if(analysis_str[0].equalsIgnoreCase("reset") || 
					analysis_str[0].equalsIgnoreCase("r") || 
					analysis_str[0].equalsIgnoreCase("list") || 
					analysis_str[0].equalsIgnoreCase("l") || 
					analysis_str[0].equalsIgnoreCase("none") || 
					analysis_str[0].equalsIgnoreCase("n") || 
					analysis_str[0].equalsIgnoreCase("exit") || 
					analysis_str[0].equalsIgnoreCase("e") || 
					analysis_str[0].equalsIgnoreCase("help") || 
					analysis_str[0].equalsIgnoreCase("h")) {
				ret = true;
			}
		}
		
		return ret;
		
	}*/
	
	
	public boolean checkGameObject(String objectName) {
		return objectName.equalsIgnoreCase(Sunflower.shortName())||
				objectName.equalsIgnoreCase(Sunflower.longName()) ||
				objectName.equalsIgnoreCase(Peashooter.shortName())||
				objectName.equalsIgnoreCase(Peashooter.longName());
	}
	
	public void generateCoins(int amount) {
		coins += amount;
	}
	
	public int getCycles() {
		
		return cycles;
	}
	
	public int getCoins() {
		
		return coins;
	}
	
	public boolean isEmpty(Position p) {
		
		return sunflowers.isEmpty(p) && peashooters.isEmpty(p) && zombies.isEmpty(p);
		
		
	}
	
	public void attackPlant(Position p, int damage) {
		
		if(!sunflowers.isEmpty(p)) {
			sunflowers.receiveDamage(p, damage);
		}
		else {
			peashooters.receiveDamage(p, damage);
		}
		
	}
	
	public void attackZombie(Position position, int damage) {
		zombies.damageZombie(position, damage);			
	}
	
	public boolean hasZombie(Position p) {
		if(zombies.isEmpty(p)) {
			return false;
		}
		
		return true;
	}
	
	public Position newZombiePosition(int row) {
		return new Position(row, NUM_COLS-1);
	}
	
	public boolean isInsideBoard(Position p) {
		if(p.row() >= 0 && p.row() < NUM_ROWS && p. col() >= 0 && p. col() < NUM_COLS) {
			return true;
		}
		
		return false;
	}
	
	public boolean playerWins() {
		
		if(hasGameFinished() && zombies.allZombiesWereKilled()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void quit() {
		this.isThePlayerOut = true;
	}
	
	public boolean playerQuits() {
		return this.isThePlayerOut;
	}
	
	public void update() {
		cycles++;
	    zombies.addZombie();

	    sunflowers.update();
	    peashooters.update();
	    zombies.update();

	    sunflowers.removeDead();
	    peashooters.removeDead();
	    zombies.removeDead();
		
	}
	
//	public void getDescription() {
//		System.out.println(Messages.SUNFLOWER_DESCRIPTION);
//		System.out.println(Messages.PEASHOOTER_DESCRIPTION);
//		
//	}
	
	public int getRemainingZombies() {
		return zombies.getRemainingZombies();
	}
	
	public void reset() {
		zombies = new ZombiesManager(this, level, rand);
		sunflowers = new SunflowerList(this);
		peashooters = new PeashooterList(this);
		cycles=0;
		coins=INITIAL_COINS;
	}
	
	public boolean hasEnoughCoins(String plantType) {	
		boolean can=false;
			if( plantType.equalsIgnoreCase(Sunflower.shortName())||
					plantType.equalsIgnoreCase(Sunflower.longName()) ) {
				if(coins>=Sunflower.cost) {
					can=true;
				}
			}
			else if( plantType.equalsIgnoreCase(Peashooter.shortName())||
					plantType.equalsIgnoreCase(Peashooter.longName()) ) {
				if(coins>=Peashooter.cost) {
					can=true;
				}
			}
		return can;
	}
	
	public void addGameObject(String plantType, Position position) {	
		if( plantType.equalsIgnoreCase(Sunflower.shortName())||
				plantType.equalsIgnoreCase(Sunflower.longName()) ) {
			sunflowers.add(new Sunflower(position.row(), position.col(), this));
			coins-= Sunflower.cost;		
			
		}
		else if( plantType.equalsIgnoreCase(Peashooter.shortName())||
				plantType.equalsIgnoreCase(Peashooter.longName()) ) {
			peashooters.add(new Peashooter(position.row(), position.col(), this));
			coins-= Peashooter.cost;		
		}
	}
		
	
	
	/*public void addGameObject(String plantType, Position position) {
		
		if (checkGameObject(plantType) && isInsideBoard(position) &&
				isEmpty(position)) {
			
			if( plantType.equalsIgnoreCase(Sunflower.shortName())||
					plantType.equalsIgnoreCase(Sunflower.longName()) ) {
				if(coins>=Sunflower.cost) {
					Sunflower s= new Sunflower(position.row(), position.col(), this);
					sunflowers.add(s);
					coins-= Sunflower.cost;
				}
			}
			else if( plantType.equalsIgnoreCase(Peashooter.shortName())||
					plantType.equalsIgnoreCase(Peashooter.longName()) ) {
				if(coins>=Peashooter.cost) {
					Peashooter p= new Peashooter(position.row(), position.col(), this);
					peashooters.add(p);
					coins-= Peashooter.cost;
				}
		    }
			
		}
	}*/
	
	public boolean hasGameFinished() {
		return zombies.doZombiesReachedTheHouse() || zombies.allZombiesWereKilled(); 
	}

}