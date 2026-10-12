package pvz.logic.gameobjects;
import pvz.logic.Game;
import pvz.logic.gameobjects.Sunflower;
import pvz.utils.Position;

public class SunflowerList {
	
	private Sunflower[] sunflower_list;
	private int cont = 0;
	private Game game;
	public SunflowerList(Game game){
		this.game = game;
		this.sunflower_list = new Sunflower[32];
	}
	
	
	public boolean isEmpty(Position position) {
		int i = 0;
		boolean empty = true;
		
		while(i < cont && empty) {
			if(sunflower_list[i].isInPosition(position)) {
				empty = false;
			}
			
			i++;
		}
		
		return empty;
	}
	
	public void removeDead() {
		for(int i = 0; i < cont; i++) {
			
			if(!sunflower_list[i].isAlive()) {
				
				for(int j = i; j < cont - 1; j++) {
					sunflower_list[j] = sunflower_list[j + 1];
				}
				
				sunflower_list[cont - 1] = null;
				cont--;
				
			}
			
		}
		
		
	}
	
	public void update() {
		
		removeDead();
		
		for(int j = 0; j < cont ; j++) {
			sunflower_list[j].update();
		}
	}
	
	public void add(Sunflower sunflower) {
		sunflower_list[cont] = sunflower;
		cont++;
		
	}
	public String iconInPosition(Position position) {
			
			boolean encontrado = false;
			int i = 0;
			String ret = null;
			
			while(i < cont && !sunflower_list[i].isInPosition(position)) i++;
			
			if(sunflower_list[i].isInPosition(position)){
				ret = sunflower_list[i].getIcon();
			}
			
			return ret;
			
	}

	public void receiveDamage(Position p, int damage) {
		int i = 0;
		while(i < cont && !sunflower_list[i].isInPosition(p)) {
			i++;
		}
		
		if(sunflower_list[i].isInPosition(p)) {
			sunflower_list[i].receiveDamage(damage);
		}
		
	}

}